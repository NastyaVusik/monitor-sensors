package com.example.monitorsensors.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
        MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector).servletPath("/monitor-sensors");

        http
                .authorizeHttpRequests(authz -> authz
                .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.GET, "/sensors/**")).hasAnyRole("ADMINISTRATOR", "VIEWER")
                .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.POST, "/sensors/**")).hasRole("ADMINISTRATOR")
                .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.PUT, "/sensors/**")).hasRole("ADMINISTRATOR")
                .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.DELETE, "/sensors/**")).hasRole("ADMINISTRATOR")
                .requestMatchers(mvcMatcherBuilder.pattern("/h2-console/**")).hasRole("ADMINISTRATOR")
                .anyRequest().authenticated()
                )
                .httpBasic(withDefaults())
                .csrf(csrf -> csrf.disable())
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin())); // Allow H2 console to be displayed in a frame

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMINISTRATOR")
                .build());
        manager.createUser(User.builder()
                .username("user")
                .password(passwordEncoder().encode("user"))
                .roles("VIEWER")
                .build());
        return manager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
