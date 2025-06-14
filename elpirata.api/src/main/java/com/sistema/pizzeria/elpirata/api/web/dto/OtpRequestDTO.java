package com.sistema.pizzeria.elpirata.api.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class OtpRequestDTO {
	
	@NotNull(message = "Email es requerido")
	@NotBlank(message = "Email no puede estar vacio")
	@Email(message = "Email no es valido")
	@Size(max = 50, message = "Email no puede tener mas de 50 caracteres")
	private String email;
	
	@NotNull(message = "Username es requerido")
	@NotBlank(message = "Username no puede estar vacio")
	@Size(max = 50, message = "Username no puede tener mas de 50 caracteres")
	@Size(min = 4, message = "Username no puede tener menos de 4 caracteres")
	private String username;
	
	@NotNull(message = "F2A Secret es requerido")
	@NotBlank(message = "F2A Secret no puede estar vacio")
	@Size(min = 16, message = "F2A Secret no puede tener menos de 16 caracteres")
	private String f2aSecret;
	
	public OtpRequestDTO() {
		super();
	}

	public OtpRequestDTO(String email, String username, String f2aSecret) {
		super();
		this.email = email;
		this.username = username;
		this.f2aSecret = f2aSecret;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getF2aSecret() {
		return f2aSecret;
	}
	
	
	
	
	
}
