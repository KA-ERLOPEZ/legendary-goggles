package com.sistema.pizzeria.elpirata.api.core.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "persona_fisica")
@PrimaryKeyJoinColumn(name = "perId") 
public class PersonaFisica extends Persona {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "El nombre no puede estar vacío")
	@Column(name = "per_nombre", nullable = false, length = 150)
	private String perNombre;

	@NotBlank(message = "El número de documento no puede estar vacío")
	@Column(name = "per_nro_cedula", unique = true, nullable = false, length = 60)
	private String perNroCedula;

	@NotBlank(message = "El apellido no puede estar vacío")
	@Column(name = "per_apellido", nullable = false, length = 150)
	private String perApellido;
	
	
	
	public String getPerNombre() {
		return perNombre;
	}
	
	public void setPerNombre(String perNombre) {
		this.perNombre = perNombre;
	}
	
	public String getPerNroCedula() {
		return perNroCedula;
	}
	
	public void setPerNroCedula(String perNroCedula) {
		this.perNroCedula = perNroCedula;
	}
	
	public String getPerApellido() {
		return perApellido;
	}
	
	public void setPerApellido(String perApellido) {
		this.perApellido = perApellido;
	}
	
	
}
