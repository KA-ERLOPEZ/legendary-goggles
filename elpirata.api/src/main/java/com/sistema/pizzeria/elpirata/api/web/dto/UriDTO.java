package com.sistema.pizzeria.elpirata.api.web.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UriDTO {

	private long uriId;

	@NotBlank(message = "La descripción de la URI no puede estar vacía")
	@Size(max = 255, message = "La descripción de la URI no puede superar los 255 caracteres")
	private String uriDescription;

	@NotBlank(message = "La ruta de la URI no puede estar vacía")
	@Pattern(regexp = "^/.*", message = "La URI debe comenzar con una barra diagonal (/)")
	private String uriPath;
	
	@NotBlank(message = "El ID del estado no puede estar vacío")
	@Pattern(regexp = "^\\d+$", message = "El ID del estado debe ser un número")
	private long estadoId;
	
	@NotBlank(message = "La descripción del estado no puede estar vacía")
	@Size(max = 255, message = "La descripción del estado no puede superar los 255 caracteres")
	private String estadoNombre;

	private List<RoleUriDTO> roleUris;

	public long getUriId() {
		return uriId;
	}

	public void setUriId(long uriId) {
		this.uriId = uriId;
	}

	public String getUriDescription() {
		return uriDescription;
	}

	public void setUriDescription(String uriDescription) {
		this.uriDescription = uriDescription;
	}

	public String getUriPath() {
		return uriPath;
	}

	public void setUriPath(String uriPath) {
		this.uriPath = uriPath;
	}

	public List<RoleUriDTO> getRoleUris() {
		return roleUris;
	}

	public void setRoleUris(List<RoleUriDTO> roleUris) {
		this.roleUris = roleUris;
	}
	
	public String getEstadoNombre() {
		return estadoNombre;
	}

	public void setEstadoNombre(String estadoNombre) {
		this.estadoNombre = estadoNombre;
	}

	public long getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(long estadoId) {
		this.estadoId = estadoId;
	}


}
