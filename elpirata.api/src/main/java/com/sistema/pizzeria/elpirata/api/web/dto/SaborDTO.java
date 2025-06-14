package com.sistema.pizzeria.elpirata.api.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SaborDTO {

	private long sabId;
	
	@NotBlank(message = "El campo nombre es obligatorio")
	@NotNull(message = "El campo nombre es obligatorio")
	@Size(min = 3, max = 50, message = "El campo nombre debe tener entre 3 y 50 caracteres")
	private String sabNombre;
	
	@NotNull(message = "El campo estado es obligatorio")
	@NotBlank(message = "El campo estado es obligatorio")
	private long estadoId;
	
	private String estadoNombre;
	
	public SaborDTO() {
	}
	
	public SaborDTO(long sabId, String sabNombre, long estadoId) {
		this.sabId = sabId;
		this.sabNombre = sabNombre;
		this.estadoId = estadoId;
	}
	
	public long getSabId() {
		return sabId;
	}
	
	public void setSabId(long sabId) {
		this.sabId = sabId;
	}
	
	public String getSabNombre() {
		return sabNombre;
	}
	
	public void setSabNombre(String sabNombre) {
		this.sabNombre = sabNombre;
	}
	
	public long getEstadoId() {
		return estadoId;
	}
	
	
	public void setEstadoId(long estadoId) {
		this.estadoId = estadoId;
	}
	
	public String getEstadoNombre() {
		return estadoNombre;
	}

	public void setEstadoNombre(String estadoNombre) {
		this.estadoNombre = estadoNombre;
	}
}
