package com.pro06.config;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Log4j2
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                // stateless한 rest api를 개발할 것이므로 csrf 공격에 대한 옵션은 꺼둔다.
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                // 특정 URL에 대한 권한 설정.
                .authorizeHttpRequests((authorizeRequests) -> {
                    authorizeRequests
                            .requestMatchers("/course/**").permitAll() // 모두 접근 가능
                            .requestMatchers("/user/**", "/mycourse/**", "/video/**", "/myvideo/**").authenticated() // 인증된, 로그인 한 사람만 접근 가능
                            .requestMatchers("/admin/**").hasAuthority("ADMIN") // admin만 접근 가능
                            .requestMatchers("/css/**", "/js/**", "/upload/**", "/cleditor/**", "/scss/**",
                                    "/vendors/**", "/ckeditor/**", "/webfonts/**", "/resource/**", "/shop/**")
                            .permitAll() // 모두 접근 가능
                            .anyRequest().permitAll();
                })
                // 로그인
                .formLogin((formLogin) -> {
                    /* 권한이 필요한 요청은 해당 url로 리다이렉트 */
                    formLogin
                            .loginPage("/login")
                            .defaultSuccessUrl("/")
                            .loginProcessingUrl("/auth")    // POST 요청 (login 창에 입력한 데이터를 처리)
                            .usernameParameter("id")  // login에 필요한 id 값을 email로 설정 (default는 username)
                            .passwordParameter("pw")  // login에 필요한 password 값을 password(default)로 설정
                            .failureUrl("/login?error=Login Fail"); // 로그인 실패 msg 전송
                })
                // 로그아웃
                .logout((logout) -> {
                    logout
                            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                            .invalidateHttpSession(true)
                            .logoutSuccessUrl("/");
                })
                // 인증 관련 예외, 사용하면 에러페이지 발생해서 주석처리
                /*.exceptionHandling((exceptionHandling) -> {
                    exceptionHandling.authenticationEntryPoint(new CustomAuthenticationEntryPoint());
                })*/
                // 중복 로그인 방지
                .sessionManagement((sessionManagement) -> {
                    sessionManagement.sessionFixation().changeSessionId()
                            .maximumSessions(1)
                            .maxSessionsPreventsLogin(false)
                            .sessionRegistry(sessionRegistry());
                })
                .build();
    }
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}