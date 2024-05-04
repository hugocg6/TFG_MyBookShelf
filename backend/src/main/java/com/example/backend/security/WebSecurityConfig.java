package com.example.backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(new AntPathRequestMatcher("/home")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/collection/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/profile/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/LogIn/**")).permitAll()
                )
                .formLogin((form) -> form
                        .loginPage("/LogIn").permitAll()
                )
                .logout(LogoutConfigurer::permitAll);
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web
                .ignoring()
                .requestMatchers(new AntPathRequestMatcher("/css/**"),
                new AntPathRequestMatcher("/misc/**"),
                new AntPathRequestMatcher("/js/**"),
                new AntPathRequestMatcher("/fontawesome/**"),
                new AntPathRequestMatcher("/fonts/**"));
    }
}