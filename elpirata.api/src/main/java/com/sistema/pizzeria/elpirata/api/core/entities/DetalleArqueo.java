package com.sistema.pizzeria.elpirata.api.core.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;


/**
 * The persistent class for the detalle_arqueo database table.
 * 
 */
@Entity
@Table(name="detalle_arqueo")
@NamedQuery(name="DetalleArqueo.findAll", query="SELECT d FROM DetalleArqueo d")
public class DetalleArqueo implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DetalleArqueoPK id;

	@Column(name="da_cantidad")
	private long daCantidad;

	//bi-directional many-to-one association to ArqueoCaja
	@ManyToOne
	@JoinColumn(name="arqueo_id", nullable=false)
	private ArqueoCaja arqueoCaja;

	//uni-directional many-to-one association to Denominacion
	@ManyToOne
	@JoinColumn(name="deno_id", nullable=false)
	private Denominacion denominacion;

	public DetalleArqueo() {
	}

	public DetalleArqueoPK getId() {
		return this.id;
	}

	public void setId(DetalleArqueoPK id) {
		this.id = id;
	}

	public long getDaCantidad() {
		return this.daCantidad;
	}

	public void setDaCantidad(long daCantidad) {
		this.daCantidad = daCantidad;
	}

	public ArqueoCaja getArqueoCaja() {
		return this.arqueoCaja;
	}

	public void setArqueoCaja(ArqueoCaja arqueoCaja) {
		this.arqueoCaja = arqueoCaja;
	}

	public Denominacion getDenominacion() {
		return this.denominacion;
	}

	public void setDenominacion(Denominacion denominacion) {
		this.denominacion = denominacion;
	}

}