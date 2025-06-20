package com.sistema.pizzeria.elpirata.api.Enums;

public enum VentaTipo {
	
	CONTADO("Contado"),
	CREDITO("Crédito");
	
	private final String descripcion;
	
	VentaTipo(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
}