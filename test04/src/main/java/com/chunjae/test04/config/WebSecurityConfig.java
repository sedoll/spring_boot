package com.chunjae.test04.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    
    // pw 암호화 빈 주입
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    // 보안 설정
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // 공통 접근, permitAll()
        // WEB-INF 내부에 있는 것들을 사용하게 해줌
        http.csrf().disable().cors().disable()
                .authorizeHttpRequests()
                .requestMatchers("/", "/user/loginForm.do", "/user/insertForm.do").permitAll() // root, login, join 접근
                .requestMatchers("/css/**", "/js/**", "/img/**").permitAll() // css, js, img 디렉토리 접근
                .requestMatchers("/user/**").permitAll()
                .requestMatchers("/admin/**").hasAnyRole("ADMIN") // admin만 admin 디렉토리 접근 가능
                .requestMatchers("/emp/**").hasAnyRole("ADMIN", "EMP") // emp, admin만 emp 디렉토리 접근 가능
                .anyRequest().authenticated();

        // 로그인 설정
        http.formLogin()
                .loginPage("/user/loginForm.do")
                .loginProcessingUrl("/user/login.do")
                .usernameParameter("id")
                .passwordParameter("pw")
                .defaultSuccessUrl("/") // 로그인에 성공하는 경우 root로 이동
                .failureForwardUrl("/user/loginForm.do"); // 실패시 다시 로그인 화면으로 이동
        
        //로그아웃 설정
        http.logout()
                .logoutUrl("/user/logout.do")
                .logoutSuccessUrl("/");

        http.cors().and().csrf().disable();
        return http.build();
    }
    
    // 404 에러
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
}
