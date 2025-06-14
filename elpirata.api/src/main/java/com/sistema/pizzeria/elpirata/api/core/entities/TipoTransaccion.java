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
 * The persistent class for the tipo_transacciones database table.
 * 
 */
@Entity
@Table(name="tipo_transacciones")
@NamedQuery(name="TipoTransaccion.findAll", query="SELECT t FROM TipoTransaccion t")
public class TipoTransaccion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="tran_tipo_id")
	private long tranTipoId;

	@ManyToOne
	@JoinColumn(name="estado_id", nullable=false, referencedColumnName="estado_id")
	private Estado estado;

	@Column(name="tran_tipo_nombre")
	private String tranTipoNombre;

	public TipoTransaccion() {
	}

	public long getTranTipoId() {
		return this.tranTipoId;
	}

	public void setTranTipoId(int tranTipoId) {
		this.tranTipoId = tranTipoId;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public String getTranTipoNombre() {
		return this.tranTipoNombre;
	}

	public void setTranTipoNombre(String tranTipoNombre) {
		this.tranTipoNombre = tranTipoNombre;
	}

}