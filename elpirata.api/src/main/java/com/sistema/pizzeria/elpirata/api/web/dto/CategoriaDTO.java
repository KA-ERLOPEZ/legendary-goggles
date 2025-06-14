package com.sistema.pizzeria.elpirata.api.web.dto;

public class CategoriaDTO {
	
	private long catId;
	
	private String catNombre;
	
	private long estadoId;
	
	private String estadoNombre;
	
	public CategoriaDTO() {
		super();
	}	
	
	public CategoriaDTO(long catId, String catNombre, long estadoId, String estadoNombre) {
		super();
		this.catId = catId;
		this.catNombre = catNombre;
		this.estadoId = estadoId;
		this.estadoNombre = estadoNombre;
	}

	public long getCatId() {
		return catId;
	}

	public void setCatId(long catId) {
		this.catId = catId;
	}

	public String getCatNombre() {
		return catNombre;
	}

	public void setCatNombre(String catNombre) {
		this.catNombre = catNombre;
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
