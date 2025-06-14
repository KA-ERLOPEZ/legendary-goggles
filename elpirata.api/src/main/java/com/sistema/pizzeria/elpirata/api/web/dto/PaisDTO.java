package com.sistema.pizzeria.elpirata.api.web.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor
public class PaisDTO {

	private long paisId;

	@NotBlank(message = "El Nombre Pais no debe estar vacio")
	private String paisNombre;

	
	private List<CiudadDTO> ciudades;

	private EstadoDTO estado;

	public long getPaisId() {
		return paisId;
	}

	public void setPaisId(long paisId) {
		this.paisId = paisId;
	}

	public String getPaisNombre() {
		return paisNombre;
	}

	public void setPaisNombre(String paisNombre) {
		this.paisNombre = paisNombre;
	}

	public List<CiudadDTO> getCiudades() {
		return ciudades;
	}

	public void setCiudades(List<CiudadDTO> ciudades) {
		this.ciudades = ciudades;
	}

	public EstadoDTO getEstado() {
		return estado;
	}

	public void setEstado(EstadoDTO estado) {
		this.estado = estado;
	}
	
	
}
