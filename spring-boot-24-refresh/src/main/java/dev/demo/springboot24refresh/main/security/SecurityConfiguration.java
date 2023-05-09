package dev.demo.springboot24refresh.main.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Allow everything, to allow actuator end points etc, zero security for demo
        http.csrf().disable();
        http.authorizeRequests()
                .anyRequest().permitAll();
        return http.build();
    }

}
