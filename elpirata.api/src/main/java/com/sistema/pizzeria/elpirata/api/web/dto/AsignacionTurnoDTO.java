package com.sistema.pizzeria.elpirata.api.web.dto;

import java.time.LocalDate;

import com.sistema.pizzeria.elpirata.api.core.validations.anotations.AsignacionTurnoValidation;

import jakarta.validation.constraints.NotNull;
@AsignacionTurnoValidation
public class AsignacionTurnoDTO {
	
	private long id;
	
	@NotNull(message = "El contrato no debe ser nulo")
	private ContratoDTO contrato;
	
	@NotNull(message = "El tuno no debe ser nulo")
	private TurnoDTO turno;
	
	@NotNull(message = "La fecha inicio es requerido")
	private LocalDate fechaInicio;
	
	private LocalDate fechaFin;
	
	private String observacion;
	
	private boolean activo = true;
	
	public AsignacionTurnoDTO() {
		
	}
	
	

	public AsignacionTurnoDTO(long id, @NotNull(message = "El contrato no debe ser nulo") ContratoDTO contrato,
			@NotNull(message = "El tuno no debe ser nulo") TurnoDTO turno,
			@NotNull(message = "La fecha inicio es requerido") LocalDate fechaInicio, LocalDate fechaFin,
			String observacion, boolean activo) {
		super();
		this.id = id;
		this.contrato = contrato;
		this.turno = turno;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.observacion = observacion;
		this.activo = activo;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ContratoDTO getContrato() {
		return contrato;
	}

	public void setContrato(ContratoDTO contrato) {
		this.contrato = contrato;
	}

	public TurnoDTO getTurno() {
		return turno;
	}

	public void setTurno(TurnoDTO turno) {
		this.turno = turno;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
		
	
	
}
