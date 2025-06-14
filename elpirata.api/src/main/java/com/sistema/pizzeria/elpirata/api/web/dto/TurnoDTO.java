package com.sistema.pizzeria.elpirata.api.web.dto;

import java.time.LocalTime;

import com.sistema.pizzeria.elpirata.api.core.validations.anotations.TurnoValidation;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@TurnoValidation
public class TurnoDTO {
	
	private Long id;
	
	@NotNull(message = "El nombre no puede ser nulo")
	@Size(min = 1, max = 50, message = "El nombre debe tener entre 1 y 50 caracteres")
	@Pattern(regexp = "^[A-Za-z0-9 ]+$", message = "El nombre solo puede contener letras, números y espacios")
	private String nombre;
	
	@NotNull(message = "La hora de inicio no puede ser nula")
	private LocalTime horaInicio;
	
	@NotNull(message = "La hora de fin no puede ser nula")
	private LocalTime horaFin;
	
	private boolean activo = true;
	
	@Size(max = 255, message = "La observación no puede tener más de 255 caracteres")
	@Pattern(regexp = "^[A-Za-z0-9.,;:!? ]*$", message = "La observación solo puede contener letras, números y signos de puntuación")
	private String observacion;
	
	// Constructor por defecto
	public TurnoDTO() {
	}
	
	// Constructor con parámetros
	public TurnoDTO(Long id, String nombre, LocalTime horaInicio, LocalTime horaFin, boolean activo,
			String observacion) {
		this.id = id;
		this.nombre = nombre;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.activo = activo;
		this.observacion = observacion;
	}

	public Long getId() {
		return id;
	}
	

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	public LocalTime getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(LocalTime horaFin) {
		this.horaFin = horaFin;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
}
