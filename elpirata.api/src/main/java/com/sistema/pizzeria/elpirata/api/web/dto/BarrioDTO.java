package com.sistema.pizzeria.elpirata.api.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BarrioDTO {

	private long barId;

	@NotBlank(message = "El nombre del barrio no puede estar vacío")
	private String barNombre;

	@NotNull(message = "El ID de la ciudad no puede ser nulo")
	private long ciudadId;

	private String ciudadNombre;

	@NotNull(message = "El ID del estado no puede ser nulo")
	private long estadoId;

	private String estadoNombre;

	public BarrioDTO(long barId, String barNombre, long ciudadId, String ciudadNombre, long estadoId,
			String estadoNombre) {
		this.barId = barId;
		this.barNombre = barNombre;
		this.ciudadId = ciudadId;
		this.ciudadNombre = ciudadNombre;
		this.estadoId = estadoId;
		this.estadoNombre = estadoNombre;
	}

	public BarrioDTO() {

	}

	public long getCiudadId() {
		return ciudadId;
	}

	public void setCiudadId(long ciudadId) {
		this.ciudadId = ciudadId;
	}

	public String getCiudadNombre() {
		return ciudadNombre;
	}

	public void setCiudadNombre(String ciudadNombre) {
		this.ciudadNombre = ciudadNombre;
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
	
	

	public long getBarId() {
		return barId;
	}

	public void setBarId(long barId) {
		this.barId = barId;
	}

	public String getBarNombre() {
		return barNombre;
	}

	public void setBarNombre(String barNombre) {
		this.barNombre = barNombre;
	}



}
