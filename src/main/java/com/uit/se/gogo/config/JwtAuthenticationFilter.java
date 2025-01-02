package com.uit.se.gogo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uit.se.gogo.exception_handler.error.ApiError;
import com.uit.se.gogo.service.impl.JwtService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    final UserDetailsService userDetailsService;
    private HandlerExceptionResolver handlerExceptionResolver;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            System.out.print("DO FILTER -- ");
            final String authHeader = request.getHeader("Authorization");
            final String jwt;
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                System.out.println("NULL OR NOT START WITH BEARER");
                filterChain.doFilter(request, response);
                return;
            }
            jwt = authHeader.substring(7);
            System.out.println("TOKEN: " + jwt + "--");
            final String userEmail = jwtService.extractUserEmail(jwt);
            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
                if (jwtService.isTokenValid(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    authToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
            filterChain.doFilter(request, response);
        } catch (MalformedJwtException ex) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            ApiError apiError = new ApiError(UNAUTHORIZED);
            apiError.setMessage("Malformed token");
            apiError.setDebugMessage(ex.getMessage());
            apiError.setErrorCode("MALFORMED_TOKEN");
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(apiError);
            response.getWriter().write(json);
        } catch (ExpiredJwtException ex) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            ApiError apiError = new ApiError(UNAUTHORIZED);
            apiError.setMessage("Expired token");
            apiError.setDebugMessage(ex.getMessage());
            apiError.setErrorCode("EXPIRED_TOKEN");
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(apiError);
            response.getWriter().write(json);
        } catch (JwtException ex) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            ApiError apiError = new ApiError(UNAUTHORIZED);
            apiError.setMessage("Invalid token");
            apiError.setDebugMessage(ex.getMessage());
            apiError.setErrorCode("INVALID_TOKEN");
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(apiError);
            response.getWriter().write(json);
        }
    }
}
