package com.sistema.pizzeria.elpirata.api.core.entities;

import java.io.Serializable;
import java.time.LocalTime;

import com.sistema.pizzeria.elpirata.api.core.commons.Auditable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;


@Entity
@Table(name = "turno", 
	uniqueConstraints = { @UniqueConstraint(columnNames = { "tur_nombre" })}, indexes = {
			@Index(columnList = "tur_nombre", name = "idx_turno_turno_nombre")
	})
public class Turno extends Auditable implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tur_id")
    private Long id;

    @Column(name = "tur_nombre", nullable = false, length = 50, unique = true)
    private String nombre; // Ej: "Turno Mañana", "Noche", etc.
    
    @Temporal(TemporalType.TIME)
    @Column(name = "tur_hora_inicio", nullable = false)
    private LocalTime horaInicio;

    @Temporal(TemporalType.TIME)
    @Column(name = "tur_hora_fin")
    private LocalTime horaFin;

    @Column(name = "tur_activo", nullable = false)
    private boolean activo;

    @Column(name = "tur_observacion")
    private String observacion;

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

    
}
