package com.sistema.pizzeria.elpirata.api.core.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;


/**
 * The persistent class for the tipo_pago database table.
 * 
 */
@Entity
@Table(name="tipo_pago")
@NamedQuery(name="TipoPago.findAll", query="SELECT t FROM TipoPago t")
public class TipoPago implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="tp_id")
	private long tpId;

	@ManyToOne
	@JoinColumn(name="estado_id", nullable = false, referencedColumnName = "estado_id")
	private Estado estado;

	@Column(name="tp_nombre", nullable = false, length=50)
	private String tpNombre;

	public TipoPago() {
	}

	public long getTpId() {
		return this.tpId;
	}

	public void setTpId(long tpId) {
		this.tpId = tpId;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public String getTpNombre() {
		return this.tpNombre;
	}

	public void setTpNombre(String tpNombre) {
		this.tpNombre = tpNombre;
	}

}