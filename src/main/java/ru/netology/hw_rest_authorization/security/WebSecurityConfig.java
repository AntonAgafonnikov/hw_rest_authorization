package ru.netology.hw_rest_authorization.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(jsr250Enabled = true)
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/home").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(Customizer.withDefaults()
                )
                .logout((logout) -> logout.permitAll());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();

        inMemoryUserDetailsManager.createUser(
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("password")
                        .roles("WRITE", "READ", "DELETE")
                        .build());

        inMemoryUserDetailsManager.createUser(
                User.withDefaultPasswordEncoder()
                        .username("ben")
                        .password("1234")
                        .roles("WRITE")
                        .build());

        inMemoryUserDetailsManager.createUser(
                User.withDefaultPasswordEncoder()
                        .username("sam")
                        .password("qwerty")
                        .roles("READ")
                        .build());

        inMemoryUserDetailsManager.createUser(
                User.withDefaultPasswordEncoder()
                        .username("ron")
                        .password("dondon")
                        .build());

        return inMemoryUserDetailsManager;
    }
}