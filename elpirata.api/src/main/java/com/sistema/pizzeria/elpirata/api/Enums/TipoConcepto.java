package com.sistema.pizzeria.elpirata.api.Enums;

public enum TipoConcepto {
	ASIGNACION("Asignación"),
    DEDUCCION("Deducción"),
    HORAS_EXTRA("Horas Extra"),
    BONIFICACION("Bonificación"),
    AJUSTE_MANUAL("Ajuste Manual"),
    OTRO("Otro");
	
	private final String descripcion;

	private TipoConcepto(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}
}
