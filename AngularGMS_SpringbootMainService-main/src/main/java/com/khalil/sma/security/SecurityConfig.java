package com.khalil.sma.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

                .cors().configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest
                                                                          request) {
                        CorsConfiguration config = new CorsConfiguration();

                        config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                        config.setAllowedMethods(Collections.singletonList("*"));
                        config.setAllowCredentials(true);
                        config.setAllowedHeaders(Collections.singletonList("*"));
                        config.setExposedHeaders(Arrays.asList("Authorization"));
                        config.setMaxAge(3600L);
                        return config;
                    }
                }).and()
                .authorizeHttpRequests()
                //consulter tous les games
                .requestMatchers("/api/all/**").permitAll()
                .requestMatchers("/error/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/getbyid/**")
                .hasAnyAuthority("ADMIN", "USER")

                .requestMatchers(HttpMethod.POST, "/api/addgame/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/leg/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/addleg/**").permitAll()
//modifier un produit
                .requestMatchers(HttpMethod.PUT, "/api/updategame/**").permitAll()
//supprimer un produit
                .requestMatchers(HttpMethod.DELETE, "/api/deletegame/**").permitAll()
                .anyRequest().authenticated().and()
                .addFilterBefore(new
                        JWTAuthorizationFilter(), BasicAuthenticationFilter.class);
        return http.build();
    }
}

