package com.sistema.pizzeria.elpirata.api.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class TamanoDTO {

	private long tamId;
	
	@NotNull(message = "El campo descripcion es obligatorio")
	@NotBlank(message = "El campo descripcion no puede estar vacío")
	@Size(min = 1, max = 50, message = "El campo descripcion debe tener entre 1 y 50 caracteres")
	private String tamDescripcion;
	
	@NotNull(message = "El campo estadoId es obligatorio")
	@NotBlank(message = "El campo estadoId no puede estar vacío")
	private long estadoId;
	
	private String estadoNombre;
	
	public TamanoDTO() {
		super();
	}
	
	public TamanoDTO(long tamId, String tamDescripcion, long estadoId, String estadoNombre) {
		super();
		this.tamId = tamId;
		this.tamDescripcion = tamDescripcion;
		this.estadoId = estadoId;
		this.estadoNombre = estadoNombre;
	}
	
	public long getTamId() {
		return tamId;
	}
	
	public void setTamId(long tamId) {
		this.tamId = tamId;
	}
	
	public String getTamDescripcion() {
		return tamDescripcion;
	}
	
	public void setTamDescripcion(String tamDescripcion) {
		this.tamDescripcion = tamDescripcion;
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
