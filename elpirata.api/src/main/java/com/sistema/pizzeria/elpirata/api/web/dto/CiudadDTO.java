package com.sistema.pizzeria.elpirata.api.web.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CiudadDTO {

	private long ciuId;

	@NotBlank(message = "El nombre de la ciudad no puede estar vacío")
	private String ciuNombre;

	@NotNull(message = "El estado ID no puede ser nulo")
	private long estadoId;
	
	private String estadoNombre;

	@NotNull(message = "El país ID no puede ser nulo")
	private long paisId;
	
	private String paisNombre;

	private List<BarrioDTO> barrios; // Opcional, si deseas incluir información de los barrios

	

	public CiudadDTO(long ciuId, @NotBlank(message = "El nombre de la ciudad no puede estar vacío") String ciuNombre,
			@NotNull(message = "El estado ID no puede ser nulo") long estadoId, String estadoNombre,
			@NotNull(message = "El país ID no puede ser nulo") long paisId, String paisNombre, List<BarrioDTO> barrios) {
		super();
		this.ciuId = ciuId;
		this.ciuNombre = ciuNombre;
		this.estadoId = estadoId;
		this.estadoNombre = estadoNombre;
		this.paisId = paisId;
		this.paisNombre = paisNombre;
		this.barrios = barrios;
	}

	public CiudadDTO(long ciuId, @NotBlank(message = "El nombre de la ciudad no puede estar vacío") String ciuNombre) {
		super();
		this.ciuId = ciuId;
		this.ciuNombre = ciuNombre;
	}

	public CiudadDTO() {
	}

	public long getCiuId() {
		return ciuId;
	}

	public void setCiuId(long ciuId) {
		this.ciuId = ciuId;
	}

	public String getCiuNombre() {
		return ciuNombre;
	}

	public void setCiuNombre(String ciuNombre) {
		this.ciuNombre = ciuNombre;
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
	
	public long getPaisId() {
		return paisId;
	}
	
	public void setPaisNombre(String paisNombre) {
		this.paisNombre = paisNombre;
	}

	public void setPaisId(long paisId) {
		this.paisId = paisId;
	}
	
	public String getPaisNombre() {
		return paisNombre;
	}
	
	

	public List<BarrioDTO> getBarrios() {
		return barrios;
	}

	public void setBarrios(List<BarrioDTO> barrios) {
		this.barrios = barrios;
	}
	
	

}
