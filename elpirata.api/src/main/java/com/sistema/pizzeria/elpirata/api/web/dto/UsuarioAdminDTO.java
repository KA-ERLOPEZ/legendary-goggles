package com.sistema.pizzeria.elpirata.api.web.dto;

import java.sql.Date;
import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

public class UsuarioAdminDTO {

	private long usuId;

	@NotNull(message = "El estado no puede ser nulo")
	private EstadoDTO estado;

	@NotNull(message = "La persona no puede ser nula")
	private PersonaDTO persona;

	@NotNull(message = "El número de intentos fallidos no puede ser nulo")
	private long usuFailedAttempts;

	@NotNull(message = "La fecha de actualización no puede ser nula")
	@NotBlank(message = "La fecha de actualización no puede estar vacía")
	@PastOrPresent(message = "La fecha de actualización debe ser en el pasado o presente")
	private Date usuFecActualizacion;

	@NotNull(message = "La fecha de creación no puede ser nula")
	@NotBlank(message = "La fecha de creación no puede estar vacía")
	@PastOrPresent(message = "La fecha de creación debe ser en el pasado o presente")
	private Date usuFecCreacion;

	private Date usuLastLogin;

	@NotNull(message = "El nombre de usuario no puede ser nulo")
	@NotBlank(message = "El nombre de usuario no puede estar vacío")
	@Size(min = 3, max = 50, message = "El nombre de usuario debe tener entre 3 y 50 caracteres")
	private String usuUsername;

	@NotNull(message = "La contraseña no puede ser nula")
	@NotBlank(message = "La contraseña no puede estar vacía")
	@Size(min = 8, max = 100, message = "La contraseña debe tener entre 8 y 100 caracteres")
	private String usuPassword;


	private boolean usuTwoFactorEnabled;

	private Set<RoleDTO> roles; // Incluye los detalles de los roles

	public UsuarioAdminDTO() {

	}

	public UsuarioAdminDTO(long usuId, EstadoDTO estado, PersonaDTO persona, long usuFailedAttempts,
			Date usuFecActualizacion, Date usuFecCreacion, Date usuLastLogin, String usuUsername, String usuPassword,
			boolean usuTwoFactorEnabled, Set<RoleDTO> roles) {
		this.usuId = usuId;
		this.estado = estado;
		this.persona = persona;
		this.usuFailedAttempts = usuFailedAttempts;
		this.usuFecActualizacion = usuFecActualizacion;
		this.usuFecCreacion = usuFecCreacion;
		this.usuLastLogin = usuLastLogin;
		this.usuUsername = usuUsername;
		this.usuPassword = usuPassword;
		this.usuTwoFactorEnabled = usuTwoFactorEnabled;
		this.roles = roles;
	}

	public long getUsuId() {
		return usuId;
	}

	public void setUsuId(long usuId) {
		this.usuId = usuId;
	}

	public EstadoDTO getEstado() {
		return estado;
	}

	public void setEstado(EstadoDTO estado) {
		this.estado = estado;
	}

	public PersonaDTO getPersona() {
		return persona;
	}

	public void setPersona(PersonaDTO persona) {
		this.persona = persona;
	}

	public long getUsuFailedAttempts() {
		return usuFailedAttempts;
	}

	public void setUsuFailedAttempts(long usuFailedAttempts) {
		this.usuFailedAttempts = usuFailedAttempts;
	}

	public Date getUsuFecActualizacion() {
		return usuFecActualizacion;
	}

	public void setUsuFecActualizacion(Date usuFecActualizacion) {
		this.usuFecActualizacion = usuFecActualizacion;
	}

	public Date getUsuFecCreacion() {
		return usuFecCreacion;
	}

	public void setUsuFecCreacion(Date usuFecCreacion) {
		this.usuFecCreacion = usuFecCreacion;
	}

	public Date getUsuLastLogin() {
		return usuLastLogin;
	}

	public void setUsuLastLogin(Date usuLastLogin) {
		this.usuLastLogin = usuLastLogin;
	}

	public String getUsuUsername() {
		return usuUsername;
	}

	public void setUsuUsername(String usuUsername) {
		this.usuUsername = usuUsername;
	}

	public boolean isUsuTwoFactorEnabled() {
		return usuTwoFactorEnabled;
	}

	public void setUsuTwoFactorEnabled(boolean usuTwoFactorEnabled) {
		this.usuTwoFactorEnabled = usuTwoFactorEnabled;
	}

	public Set<RoleDTO> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleDTO> roles) {
		this.roles = roles;
	}

	public String getUsuPassword() {
		return usuPassword;
	}

	public void setUsuPassword(String usuPassword) {
		this.usuPassword = usuPassword;
	}

}
