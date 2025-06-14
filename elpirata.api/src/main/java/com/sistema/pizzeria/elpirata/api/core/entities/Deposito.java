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
 * The persistent class for the depositos database table.
 * 
 */
@Entity
@Table(name="depositos")
@NamedQuery(name="Deposito.findAll", query="SELECT d FROM Deposito d")
public class Deposito implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="depo_id")
	private long depoId;

	@Column(name="depo_nombre", length=50, nullable=false)
	private String depoNombre;

	//uni-directional many-to-one association to Estado
	@ManyToOne
	@JoinColumn(name="estado_id", nullable=false)
	private Estado estado;

	//uni-directional many-to-one association to Sucursal
	@ManyToOne
	@JoinColumn(name="suc_id", nullable=false)
	private Sucursal sucursal;

	public Deposito() {
	}

	public long getDepoId() {
		return this.depoId;
	}

	public void setDepoId(long depoId) {
		this.depoId = depoId;
	}

	public String getDepoNombre() {
		return this.depoNombre;
	}

	public void setDepoNombre(String depoNombre) {
		this.depoNombre = depoNombre;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Sucursal getSucursal() {
		return this.sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

}