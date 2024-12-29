package com.uit.se.gogo.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.RequiredArgsConstructor;

import static com.uit.se.gogo.enums.UserType.STAY_MANAGER;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authProvider;

    private static final String[] SWAGGER_ENDPOINTS = {
        "/swagger-ui/**", "/v3/api-docs/**"
    };

    private static final String[] WHITELIST_APIS = {
            "/api/v1/auth/**"
    };

    private static final String[] SECURED_APIS = {
            "/api/v1/favorites/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(WHITELIST_APIS).permitAll()
                        .requestMatchers(HttpMethod.GET, SWAGGER_ENDPOINTS).permitAll()
                        .requestMatchers(HttpMethod.PUT).authenticated()
                        .requestMatchers(HttpMethod.PATCH).authenticated()
                        .requestMatchers(HttpMethod.DELETE).authenticated()
                        .requestMatchers(SECURED_APIS).authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/v1/rooms/admin/**").hasAnyRole("ADMIN", "STAY_MANAGER")
                        .requestMatchers(HttpMethod.POST).authenticated()
                        .anyRequest().permitAll()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
//                .logout(logout ->
//                        logout.logoutUrl("/api/v1/auth/logout")
//                                .addLogoutHandler(logoutHandler)
//                                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext());

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        //Make the below setting as * to allow connection from any hos
        // corsConfiguration.setAllowedOrigins(List.of("http://localhost:8000"));
        corsConfiguration.setAllowedOrigins(List.of("*"));
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        // corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedHeaders(List.of("*"));
        corsConfiguration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }
}
