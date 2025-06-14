package com.sistema.pizzeria.elpirata.api.core.config.security;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.sistema.pizzeria.elpirata.api.core.config.jwt.JwtAuthenticationEntryPoint;
import com.sistema.pizzeria.elpirata.api.core.config.jwt.JwtAuthenticationFilter;
import com.sistema.pizzeria.elpirata.api.core.services.RoleUriService;
import com.sistema.pizzeria.elpirata.api.web.dto.RoleUriDTO;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	private JwtAuthenticationFilter authenticationFilter;

	@Autowired
	private JwtAuthenticationEntryPoint authenticationEntryPoint;

	@Autowired
	private RoleUriService service;
	
	@Autowired
	private RateLimitingFilter limitingFilter;

	@Bean
	PasswordEncoder passwordEncoder() {
		String idForEncode = "bcrypt";
		Map<String, PasswordEncoder> encoders = new HashMap<>();
		encoders.put(idForEncode, new BCryptPasswordEncoder());
		encoders.put("pbkdf2@SpringSecurity_v5_8", Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8());
		return new DelegatingPasswordEncoder(idForEncode, encoders);
	}

	@Bean
	CorsConfigurationSource configurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		// Dominios permitidos (ajusta según tu entorno)
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200", "http://localhost:3200"));
		// Métodos HTTP permitidos
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE"));
		// Headers permitidos
		configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
		// Permitir envío de credenciales (cookies, tokens)
		configuration.setAllowCredentials(true);
		// Tiempo en segundos que los navegadores pueden cachear esta configuración
		configuration.setMaxAge(3600L);

		// Registrar la configuración para todas las rutas
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	@Bean
    AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
		AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
		builder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		return builder.build();
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

	    // Mapa: URI -> Map<HTTP_METHOD, Set<ROLES>>
	    Map<String, Map<String, Set<String>>> uriMethodRoleMapping = service.findAll().stream()
	        .collect(Collectors.groupingBy(
	            r -> r.getUri().getUriPath(),
	            Collectors.groupingBy(
	                RoleUriDTO::getHttpMethod,
	                Collectors.mapping(r -> r.getRole().getRoleDescripcion(), Collectors.toSet())
	            )
	        ));

	    http
	        .csrf(AbstractHttpConfigurer::disable)
	        .cors(withDefaults())
	        .authorizeHttpRequests(auth -> {
	            // Rutas públicas
	            auth.requestMatchers("/api/auth/**").permitAll();
	            auth.requestMatchers(HttpMethod.GET, "/api/public/**").permitAll();
	            auth.requestMatchers(
	                "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html",
	                "/swagger-resources/**", "/webjars/**"
	            ).permitAll();

	            // Rutas dinámicas según método HTTP
	            uriMethodRoleMapping.forEach((uri, methodRoleMap) -> {
	                methodRoleMap.forEach((httpMethod, roles) -> {
	                    HttpMethod method = HttpMethod.valueOf(httpMethod.toUpperCase());
	                    if (method != null) {
	                        auth.requestMatchers(method, uri)
	                            .hasAnyAuthority(roles.toArray(new String[0]));
	                    } else {
	                        // Si no se reconoce el método, se ignora o podrías lanzar una excepción.
	                        throw new IllegalArgumentException("HTTP method inválido: " + httpMethod);
	                    }
	                });
	            });

	            // Cualquier otra ruta requiere autenticación
	            auth.anyRequest().authenticated();
	        })
	        .addFilterBefore(limitingFilter, UsernamePasswordAuthenticationFilter.class)
	        .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
	        .exceptionHandling(ex -> ex.authenticationEntryPoint(authenticationEntryPoint))
	        .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

	    return http.build();
	}


}
