
package com.sistema.pizzeria.elpirata.api.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
@Profile("dev")// Swagger se habilitará solo cuando el perfil sea "dev"
public class SwaggerConfig {

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().addSecurityItem(new SecurityRequirement().addList("BearerAuth"))
				.components(new io.swagger.v3.oas.models.Components().addSecuritySchemes("BearerAuth",
						new SecurityScheme().name("Authorization").type(SecurityScheme.Type.HTTP).scheme("bearer")
								.bearerFormat("JWT")))
				.info(new Info().title("API de Pizzería El Pirata").version("1.0.0")
						.description("Documentación de la API para la pizzería El Pirata"));
	}
}