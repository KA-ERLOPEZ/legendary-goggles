package com.sistema.pizzeria.elpirata.api.core.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

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
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "descuento", uniqueConstraints = {
@UniqueConstraint(columnNames = { "des_fecha", "cont_id", "con_id" })
})
public class Descuento extends Auditable implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "des_id")
	private long id;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "cont_id", nullable = false, referencedColumnName = "cont_id")
	private Contrato contrato;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "des_fecha", nullable = false)
	private LocalDate fecha;
	
	@Column(name = "des_monto", nullable = false, precision = 10, scale = 2)
	private BigDecimal monto;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "con_id", nullable = false, referencedColumnName = "con_id")
	private Concepto concepto;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "des_estado", nullable = false)
	private EstadoConcepto estado;
	
	
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
	
	public BigDecimal getMonto() {
		return monto;
	}
	
	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}
	
	public Concepto getConcepto() {
		return concepto;
	}
	
	public void setConcepto(Concepto concepto) {
		this.concepto = concepto;
	}
	
	public EstadoConcepto getEstado() {
		return estado;
	}
	
	public void setEstado(EstadoConcepto estado) {
		this.estado = estado;
	}
	
	

	
}
