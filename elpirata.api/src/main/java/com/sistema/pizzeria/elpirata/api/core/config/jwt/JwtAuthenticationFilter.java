package com.sistema.pizzeria.elpirata.api.core.config.jwt;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.sistema.pizzeria.elpirata.api.core.config.security.CustomUserDetailsService;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JWTProvider jwtProvider;

    @Autowired
    private CustomUserDetailsService detailsService;

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {
            String token = getTokenByRequest(request);

            if (token != null && jwtProvider.validateToken(token, TokenType.ACCESS)) {

                Authentication existingAuth = SecurityContextHolder.getContext().getAuthentication();

                if (existingAuth == null || !existingAuth.isAuthenticated()) {
                    String username = jwtProvider.getUserFromAccessToken(token);
                    logger.info("Autenticando usuario: {}", username);

                    UserDetails userDetails = detailsService.loadUserByUsername(username);

                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }

        } catch (JwtException | UsernameNotFoundException ex) {
        	 logger.error("Error de autenticación JWT: {}", ex.getMessage());
        	    // Dispara el entry point de Spring Security automáticamente
        	    throw new BadCredentialsException("Token inválido o usuario no encontrado", ex);
        }

        filterChain.doFilter(request, response);
    }

    private String getTokenByRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.substring(BEARER_PREFIX.length());
        }
        return null;
    }
}

