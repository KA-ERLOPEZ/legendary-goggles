package com.sistema.pizzeria.elpirata.api.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class MarcaDTO {
	
	private long marId;
	
	@NotNull(message = "El nombre de la marca es obligatorio")
	@NotBlank(message = "El nombre de la marca es obligatorio")
	@Size(min = 3, max = 50, message = "El nombre de la marca debe tener entre 3 y 50 caracteres")
	private String marNombre;
	
	@NotNull(message = "El id del estado es obligatorio")
	@NotBlank(message = "El id del estado es obligatorio")
	private long estadoId;
	
	private String estadoNombre;
	
	public MarcaDTO() {
		super();
	}

	public MarcaDTO(long marId, String marNombre, long estadoId, String estadoNombre) {
		super();
		this.marId = marId;
		this.marNombre = marNombre;
		this.estadoId = estadoId;
		this.estadoNombre = estadoNombre;
	}
	
	public long getMarId() {
		return marId;
	}
	
	public void setMarId(long marId) {
		this.marId = marId;
	}
	
	public String getMarNombre() {
		return marNombre;
	}
	
	public void setMarNombre(String marNombre) {
		this.marNombre = marNombre;
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
