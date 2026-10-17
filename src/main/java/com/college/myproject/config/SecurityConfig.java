package com.college.myproject.config;

import com.college.myproject.security.JwtFilter;
import com.college.myproject.security.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;

@Configuration
public class SecurityConfig {

    @Bean
    public JwtFilter jwtFilter(JwtUtil jwtUtil) {
        return new JwtFilter(jwtUtil);
    }

    @Bean
    public FilterRegistrationBean<JwtFilter> jwtFilterRegistration(JwtFilter jwtFilter) {

        FilterRegistrationBean<JwtFilter> registration =
                new FilterRegistrationBean<>();

        registration.setFilter(jwtFilter);
        registration.addUrlPatterns("/api/profile/*");

        return registration;
    }
}