package com.example.spring31.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // spring security 6.1 이상부터 이런식으로 쓰는 걸로 바뀜
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .headers((headers) -> {
                    headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin);
                })
                // stateless한 rest api를 개발할 것이므로 csrf 공격에 대한 옵션은 꺼둔다.
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)

                // 특정 URL에 대한 권한 설정.
                .authorizeHttpRequests((authorizeRequests) -> {
                    authorizeRequests
                            .requestMatchers("/video/**", "/test/**", "/free/**", "/product/**").permitAll()
                            .requestMatchers("/user/**").authenticated() // 인증된, 로그인 한 사람만 접근 가능
                            .requestMatchers("/admin/**").hasAuthority("ADMIN") // admin만 접근 가능
                            .requestMatchers("/css/**", "/js/**", "/upload/**", "/cleditor/**", "/scss/**",
                                    "/vendors/**", "/ckeditor/**", "/webfonts/**", "/resource/**",
                                    "/smarteditor/**")
                                    .permitAll() // 모두 접근 가능
                            .anyRequest().permitAll();
                })
                
                // 로그인
                .formLogin((formLogin) -> {
                    /* 권한이 필요한 요청은 해당 url로 리다이렉트 */
                    formLogin
                            .loginPage("/common/login")
                            .defaultSuccessUrl("/")
                            .loginProcessingUrl("/auth")    // POST 요청 (login 창에 입력한 데이터를 처리)
                            .usernameParameter("name")	// login에 필요한 id 값을 email로 설정 (default는 username)
                            .passwordParameter("password")	// login에 필요한 password 값을 password(default)로 설정
                            .failureUrl("/error");
                })
                
                // 로그아웃
                .logout((logout) -> {
                    logout
                            .logoutUrl("/logout")
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
                            .expiredSessionStrategy(new CustomSessionExpiredStrategy())
                            .maxSessionsPreventsLogin(false)
                            .sessionRegistry(sessionRegistry());
                })
                .build();
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