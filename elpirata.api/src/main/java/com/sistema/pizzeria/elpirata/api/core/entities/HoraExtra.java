package com.sistema.pizzeria.elpirata.api.core.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import com.sistema.pizzeria.elpirata.api.Enums.EstadoConcepto;
import com.sistema.pizzeria.elpirata.api.core.commons.Auditable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "horas_extras")
public class HoraExtra extends Auditable implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "hor_id")
	private long id;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "cont_id", nullable = false, referencedColumnName = "cont_id")
	private Contrato contrato;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "hor_fecha", nullable = false)
	private LocalDate fecha;
	
	@Temporal(TemporalType.TIME)
	@Column(name = "hor_hora_inicio", nullable = false)
	private LocalTime horaInicio;
	
	@Temporal(TemporalType.TIME)
	@Column(name = "hor_hora_fin", nullable = false)
	private LocalTime horaFin;
	
	@Column(name = "hor_porcentaje", nullable = false, precision = 10, scale = 2)
	private BigDecimal procentajePago;
	
	@Enumerated(value = EnumType.STRING)
	@Column(name = "hor_estado" )
	private EstadoConcepto estado;
	
	@Column(name = "hor_observacion")
	private String observacion;
	
	
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
	
	public LocalDate getFecha() {
		return fecha;
	}
	
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
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
	
	public BigDecimal getProcentajePago() {
		return procentajePago;
	}
	
	public void setProcentajePago(BigDecimal procentajePago) {
		this.procentajePago = procentajePago;
	}
	
	public EstadoConcepto getEstado() {
		return estado;
	}
	
	public void setEstado(EstadoConcepto estado) {
		this.estado = estado;
	}
	
	public String getObservacion() {
		return observacion;
	}
	
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	
	
}
