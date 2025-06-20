package com.sistema.pizzeria.elpirata.api.Enums;

public enum EstadoConcepto {
	PENDIENTE("Pendiente"),
	APROBADO("Aprobado"),
	RECHAZADO("Rechazado"),
	LIQUIDADO("Liquidado"),
	CANCELADO("Cancelado");
	
	private final String descripcion;
	
	private EstadoConcepto(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
}
