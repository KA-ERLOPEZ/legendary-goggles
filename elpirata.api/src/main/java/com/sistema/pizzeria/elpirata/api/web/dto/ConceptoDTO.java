package com.sistema.pizzeria.elpirata.api.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class ConceptoDTO {
	
	private long id;
	
	@NotNull(message = "El nombre es requerido")
	@NotBlank(message = "El nombre no puede estar vacío")
    @Pattern(regexp = "^[a-zA-Z0-9_ ]*$", message = "El nombre solo puede contener letras, números y guiones bajos")
	private String nombre;
	
	@NotNull(message = "La descripción es requerida")
	@NotBlank(message = "La descripción no puede estar vacía")
	@Pattern(regexp = "^[a-zA-Z0-9_ ]*$", message = "La descripción solo puede contener letras, números y guiones bajos")
	private String descripcion;
	
	private boolean activo = true;
	
	public ConceptoDTO() {

	}

	public ConceptoDTO(long id, String nombre, String descripcion, boolean activo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.activo = activo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
}
