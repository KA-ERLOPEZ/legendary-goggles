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


/**
 * The persistent class for the tamano database table.
 * 
 */
@Entity
@NamedQuery(name="Tamano.findAll", query="SELECT t FROM Tamano t")
public class Tamano implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="tam_id")
	private long tamId;

	@Column(name="tam_descripcion", length=50, nullable=false)
	private String tamDescripcion;

	//uni-directional many-to-one association to Estado
	@ManyToOne
	@JoinColumn(name="estado_id", nullable=false, referencedColumnName="estado_id")
	private Estado estado;

	public Tamano() {
	}

	public long getTamId() {
		return this.tamId;
	}

	public void setTamId(long tamId) {
		this.tamId = tamId;
	}

	public String getTamDescripcion() {
		return this.tamDescripcion;
	}

	public void setTamDescripcion(String tamDescripcion) {
		this.tamDescripcion = tamDescripcion;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

}