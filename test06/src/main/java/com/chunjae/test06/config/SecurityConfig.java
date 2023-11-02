package com.chunjae.test06.config;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

//
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    // pw 암호화 빈 주입
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    // 보안 설정
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // 권한에 따라 허용하는 url 설정
        // .permitAll() 모두가 접근 가능한 페이지
        // 폼 이동, 실행 코드도 넣어줘야 제대로 실행됨
        // 자원의 경로는 mvcMatchers 로
        http.authorizeRequests()
            .antMatchers("/").permitAll()
            .antMatchers("/user/login", "/user/join", "/user/join.do", "/user/idCheck", "/user/emailCheck",
                    "/user/update", "/user/update.do").permitAll()
            .mvcMatchers("/","/resource/**","/css/**", "/js/**", "/images/**").permitAll()
            .anyRequest().authenticated();

        // login 설정
        http.formLogin()
            .loginPage("/user/login")    // GET 요청 (login form을 보여줌)
            .loginProcessingUrl("/auth")    // POST 요청 (login 창에 입력한 데이터를 처리)
            .usernameParameter("id")  // login에 필요한 id 값을 email로 설정 (default는 username)
            .passwordParameter("password")  // login에 필요한 password 값을 password(default)로 설정
            .defaultSuccessUrl("/");    // login에 성공하면 /로 redirect

        // logout 설정
        http.logout()
            .logoutUrl("/logout")
            .invalidateHttpSession(true)
            .logoutSuccessUrl("/"); // logout에 성공하면 /로 redirect

        //cors 방지 해제
        http.csrf().disable().cors().disable();

        //중복 로그인 방지
        http.sessionManagement()
                .sessionFixation().changeSessionId()
                .maximumSessions(1) // 최대 로그인 가능 수
                .expiredSessionStrategy(new CustomSessionExpiredStrategy())
                .maxSessionsPreventsLogin(false)
                .sessionRegistry(sessionRegistry());

        return http.build();
    }

    // js로 들어오는 방지 기능 해제
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
    public static ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
        return new ServletListenerRegistrationBean<>(new HttpSessionEventPublisher());
    }
}
