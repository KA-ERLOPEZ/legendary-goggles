package com.sistema.pizzeria.elpirata.api.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class EstadoDTO {

	private long estadoId;
	
	private boolean estadoEnabled = true;
	
	@NotNull(message = "El nombre del estado es obligatorio")
	@NotBlank(message = "El nombre del estado no puede estar vacío")
	@Size(min = 3, max = 50, message = "El nombre del estado debe tener entre 3 y 50 caracteres")
	private String estadoNombre;
	
	@NotNull(message = "El id del dominio es obligatorio")
	@NotBlank(message = "El id del dominio no puede estar vacío")
	private int dominioId; // Added this field
	
	private String dominioNombre; // Added this field

	
	public EstadoDTO(int estadoId, String estadoNombre, int dominioId, String dominioNombre) {
		super();
		this.estadoId = estadoId;
		this.estadoNombre = estadoNombre;
		this.dominioId = dominioId;
		this.dominioNombre = dominioNombre;
	}
	
	

	public EstadoDTO(int estadoId, String estadoNombre) {
		super();
		this.estadoId = estadoId;
		this.estadoNombre = estadoNombre;
	}



	public EstadoDTO() {
		super();
	}



	public long getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(long estadoId) {
		this.estadoId = estadoId;
	}

	public boolean isEstadoEnabled() {
		return estadoEnabled;
	}

	public void setEstadoEnabled(boolean estadoEnabled) {
		this.estadoEnabled = estadoEnabled;
	}

	public String getEstadoNombre() {
		return estadoNombre;
	}

	public void setEstadoNombre(String estadoNombre) {
		this.estadoNombre = estadoNombre;
	}

	public int getDominioId() {
		return dominioId;
	}
	
	public void setDominioId(int dominioId) {
		this.dominioId = dominioId;
	}
	
	public String getDominioNombre() {
		return dominioNombre;
	}
	
	public void setDominioNombre(String dominioNombre) {
		this.dominioNombre = dominioNombre;
	}
	

}
