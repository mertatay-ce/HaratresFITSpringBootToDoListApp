package com.haratres_fit.springboot_todolistapp.config;

import com.haratres_fit.springboot_todolistapp.security.ToDoListAuthenticationProvider;
import com.haratres_fit.springboot_todolistapp.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final UserServiceImpl userService;
    private final ToDoListAuthenticationProvider toDoListAuthenticationProvider;

    @Autowired
    public SecurityConfig(UserServiceImpl userService, ToDoListAuthenticationProvider toDoListAuthenticationProvider) {
        this.userService = userService;
        this.toDoListAuthenticationProvider = toDoListAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(toDoListAuthenticationProvider);

        return authenticationManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request.anyRequest()
                        .authenticated())
                .httpBasic(Customizer.withDefaults())
                .build();
    }



}
