package com.sistema.pizzeria.elpirata.api.core.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.sistema.pizzeria.elpirata.api.core.commons.Auditable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "cuentas_por_cobrar")
@NamedQuery(name = "CuentasPorCobrar.findAll", query = "SELECT c FROM CuentasPorCobrar c")
public class CuentasPorCobrar extends Auditable implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
	@Column(name = "cuenta_id")
	private Long id;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "venta_id", nullable = false, referencedColumnName = "venta_id")
	private VentaCabecera ventaCabecera;
	
	@Column(name = "numero_couta", nullable = false)
	private Integer numeroCouta;
	
	@Column(name = "fecha_vencimiento", nullable = false)
	private LocalDateTime fechaVencimiento;
	
	@Column(name = "fecha_pago", nullable = true)
	private LocalDateTime fechaDePago;
	
	@Column(name = "descripcion", length = 255, nullable = true)
	private String descripcion;
	
	@Column(name = "monto_total", nullable = false, precision = 10, scale = 2)
	private BigDecimal montoTotal;
	
	@Column(name = "monto_pagado", nullable = false, precision = 10, scale = 2)
	private BigDecimal montoPagado;
	
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "estado_id", nullable = false, referencedColumnName = "estado_id")
	private Estado estado;

	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public VentaCabecera getVentaCabecera() {
		return ventaCabecera;
	}
	
	public void setVentaCabecera(VentaCabecera ventaCabecera) {
		this.ventaCabecera = ventaCabecera;
	}
	
	
	public Integer getNumeroCouta() {
		return numeroCouta;
	}
	
	public void setNumeroCouta(Integer numeroCouta) {
		this.numeroCouta = numeroCouta;
	}
	
	public LocalDateTime getFechaDePago() {
		return fechaDePago;
	}
	
	public void setFechaDePago(LocalDateTime fechaDePago) {
		this.fechaDePago = fechaDePago;
	}
	
	
	public LocalDateTime getFechaVencimiento() {
		return fechaVencimiento;
	}
	
	public void setFechaVencimiento(LocalDateTime fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public BigDecimal getMontoTotal() {
		return montoTotal;
	}
	
	public void setMontoTotal(BigDecimal montoTotal) {
		this.montoTotal = montoTotal;
	}
	
	public BigDecimal getMontoPagado() {
		return montoPagado;
	}
	
	public void setMontoPagado(BigDecimal montoPagado) {
		this.montoPagado = montoPagado;
	}
	

	
	public Estado getEstado() {
		return estado;
	}
	
	public void setEstado(Estado estado) {
		this.estado = estado;
	}


}
