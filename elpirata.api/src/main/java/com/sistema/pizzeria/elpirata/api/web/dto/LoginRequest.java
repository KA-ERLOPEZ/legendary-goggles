package com.sistema.pizzeria.elpirata.api.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
	
	@NotNull(message = "El campo username no puede ser nulo")
	@NotNull(message = "El campo password no puede ser nulo")
	@Size(min = 3, max = 20, message = "El campo username debe tener entre 3 y 20 caracteres")
	private String username;
	
	@Size(min = 3, max = 20, message = "El campo password debe tener entre 3 y 20 caracteres")
	@NotNull(message = "El campo password no puede ser nulo")	
	@NotBlank(message = "El campo password no puede estar vacío")
	private String password;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
