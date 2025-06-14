package com.sistema.pizzeria.elpirata.api.core.entities;

import java.io.Serializable;
import java.time.LocalDate;

import com.sistema.pizzeria.elpirata.api.core.commons.Auditable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "asignaciones_turnos", uniqueConstraints = {
		@UniqueConstraint(columnNames = {"cont_id", "tur_id", "at_fecha_inicio"}, 
				name = "uk_cont_id_tur_id_at_fecha_inicio")})
public class AsignacionTurno extends Auditable implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "at_id")
	private long id;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "cont_id", referencedColumnName = "cont_id")
	private Contrato contrato;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "tur_id", referencedColumnName = "tur_id")
	private Turno turno;
	
	
	@Temporal(TemporalType.DATE)
	@Column(name = "at_fecha_inicio", nullable = false)
	private LocalDate fechaInicio;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "at_fecha_fin")
	private LocalDate fechaFin;
	
	@Column(name = "at_observacin")
	private String observacion;
	
	@Column(name = "at_activo", nullable = false)
	private boolean activo;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
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
