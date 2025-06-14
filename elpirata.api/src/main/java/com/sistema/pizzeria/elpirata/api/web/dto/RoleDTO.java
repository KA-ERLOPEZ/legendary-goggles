package com.sistema.pizzeria.elpirata.api.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@AllArgsConstructor @NoArgsConstructor
public class RoleDTO {

	private long roleId;

	@NotBlank(message = "La descripción del rol no puede estar vacía.")
	@NotNull(message = "La descripción del rol no puede ser nula.")
	@Size(min = 3, max = 50, message = "La descripción del rol debe tener entre 3 y 50 caracteres.")
	private String roleDescripcion;

	@NotNull(message = "El estado del rol no puede ser nulo.")
	@NotBlank(message = "El estado del rol no puede estar vacío.")
	@Size(min = 1, max = 1, message = "El estado del rol debe ser verdadero o falso.")
	private boolean roleEnabled;

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public String getRoleDescripcion() {
		return roleDescripcion;
	}

	public void setRoleDescripcion(String roleDescripcion) {
		this.roleDescripcion = roleDescripcion;
	}

	public boolean isRoleEnabled() {
		return roleEnabled;
	}

	public void setRoleEnabled(boolean roleEnabled) {
		this.roleEnabled = roleEnabled;
	}
	
	
	
	
}
