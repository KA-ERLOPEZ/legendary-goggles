package com.sistema.pizzeria.elpirata.api.Enums;

public enum TipoEmpresa {

	INDIVIDUAL("Individual"), SOCIEDAD_ANONIMA("Sociedad Anónima"), SOCIEDAD_LIMITADA("Sociedad Limitada"),
	COOPERATIVA("Cooperativa"), ASOCIACION("Asociación"), FUNDACION("Fundación"), OTRA("Otra");

	private final String descripcion;

	TipoEmpresa(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}
}
