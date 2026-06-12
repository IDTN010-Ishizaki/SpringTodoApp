package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.filter.AuthFilter;

@Configuration
public class AuthConfig {
    @Bean
    AuthFilter authFilter() {
        return new AuthFilter("/auth/login",
                "/auth/*",
                "/css/*",
                "h2-console/*");
    }
}
