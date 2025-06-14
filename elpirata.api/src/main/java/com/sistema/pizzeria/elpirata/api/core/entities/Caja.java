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
 * The persistent class for the cajas database table.
 * 
 */
@Entity
@Table(name="cajas")
@NamedQuery(name="Caja.findAll", query="SELECT c FROM Caja c")
public class Caja implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="caja_id")
	private long cajaId;

	@Column(name="caja_nro", length=45, nullable=false)
	private String cajaNro;

	//uni-directional many-to-one association to Estado
	@ManyToOne
	@JoinColumn(name="estado_id", nullable=false, referencedColumnName="estado_id")
	private Estado estado;

	//uni-directional many-to-one association to Sucursal
	@ManyToOne
	@JoinColumn(name="suc_id", nullable=false)
	private Sucursal sucursal;

	public Caja() {
	}

	public long getCajaId() {
		return this.cajaId;
	}

	public void setCajaId(long cajaId) {
		this.cajaId = cajaId;
	}

	public String getCajaNro() {
		return this.cajaNro;
	}

	public void setCajaNro(String cajaNro) {
		this.cajaNro = cajaNro;
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