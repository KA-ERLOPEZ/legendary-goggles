package com.sistema.pizzeria.elpirata.api.core.config.jwt;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		// Log detallado del error de autenticación en la ruta solicitada

		String message = authException.getMessage() != null ? authException.getMessage()
				: "No se pudo autenticar la solicitud";
		logger.error("Error de autenticación en la ruta: {} - Mensaje: {}", request.getRequestURI(), message);

		// Respuesta en formato JSON con el mensaje de error y el estado HTTP 401

		response.setContentType("application/json");
		
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, message);
        //response.getWriter().write(new ObjectMapper().writeValueAsString(new ShowException(LocalDate.now(), message, "No autorizado")));
//		ShowException errorResponse = new ShowException(LocalDate.now(), message,"No autorizado");
//		ObjectMapper objectMapper = new ObjectMapper();
//		response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
	}

}


