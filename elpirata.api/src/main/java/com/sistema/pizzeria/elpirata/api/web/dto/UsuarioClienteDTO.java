package com.sistema.pizzeria.elpirata.api.web.dto;

import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UsuarioClienteDTO {

	private long usuId;

	@NotNull(message = "El estado no puede ser nulo")
	@NotBlank(message = "El estado no puede estar vacío")
	@Size(min = 3, max = 50, message = "El estado debe tener entre 3 y 50 caracteres")
	private String usuUsername;

	@NotNull(message = "La contraseña no puede ser nula")
	@NotBlank(message = "La contraseña no puede estar vacía")
	@Size(min = 8, max = 100, message = "La contraseña debe tener entre 8 y 100 caracteres")
	private String usuPassword;

	@NotNull(message = "La persona no puede ser nula")
	private PersonaDTO persona;

	@NotNull(message = "El número de intentos fallidos no puede ser nulo")
	@NotBlank(message = "El número de intentos fallidos no puede estar vacío")
	@Pattern(regexp = "\\d+", message = "El número de intentos fallidos debe ser un número")
	private int usuFailedAttempts;

	private boolean usuTwoFactorEnabled;

	@Size(min = 16, max = 32, message = "El secreto de dos factores debe tener entre 16 y 32 caracteres")
	private String usuTwoFactorSecret;

	@NotNull(message = "El estado no puede ser nulo")
	private long estadoId;

	private String estadoNombre;

	public UsuarioClienteDTO() {

	}

	public UsuarioClienteDTO(long usuId, String usuUsername, String usuPassword, PersonaDTO persona,
			int usuFailedAttempts, boolean usuTwoFactorEnabled, String usuTwoFactorSecret, long estadoId,
			String estadoNombre) {
		this.usuId = usuId;
		this.usuUsername = usuUsername;
		this.usuPassword = usuPassword;
		this.persona = persona;
		this.usuFailedAttempts = usuFailedAttempts;
		this.usuTwoFactorEnabled = usuTwoFactorEnabled;
		this.usuTwoFactorSecret = usuTwoFactorSecret;
		this.estadoId = estadoId;
		this.estadoNombre = estadoNombre;
	}

	private Set<RoleDTO> roles = new HashSet<>();

	public long getUsuId() {
		return usuId;
	}

	public void setUsuId(long usuId) {
		this.usuId = usuId;
	}

	public String getUsuUsername() {
		return usuUsername;
	}

	public void setUsuUsername(String usuUsername) {
		this.usuUsername = usuUsername;
	}

	public String getUsuPassword() {
		return usuPassword;
	}

	public void setUsuPassword(String usuPassword) {
		this.usuPassword = usuPassword;
	}

	public PersonaDTO getPersona() {
		return persona;
	}

	public void setPersona(PersonaDTO persona) {
		this.persona = persona;
	}

	public int getUsuFailedAttempts() {
		return usuFailedAttempts;
	}

	public void setUsuFailedAttempts(int usuFailedAttempts) {
		this.usuFailedAttempts = usuFailedAttempts;
	}

	public boolean isUsuTwoFactorEnabled() {
		return usuTwoFactorEnabled;
	}

	public String getUsuTwoFactorSecret() {
		return usuTwoFactorSecret;
	}

	public void setUsuTwoFactorSecret(String usuTwoFactorSecret) {
		this.usuTwoFactorSecret = usuTwoFactorSecret;
	}

	public void setUsuTwoFactorEnabled(boolean usuTwoFactorEnabled) {
		this.usuTwoFactorEnabled = usuTwoFactorEnabled;
	}

	public long getEstadoId() {
		return estadoId;
	}

	public String getEstadoNombre() {
		return estadoNombre;
	}

	public void setEstadoNombre(String estadoNombre) {
		this.estadoNombre = estadoNombre;
	}

	public void setEstadoId(long estadoId) {
		this.estadoId = estadoId;
	}

	public Set<RoleDTO> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleDTO> roles) {
		this.roles = roles;
	}

}
