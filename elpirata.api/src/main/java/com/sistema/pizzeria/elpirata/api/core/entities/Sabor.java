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
 * The persistent class for the sabores database table.
 * 
 */
@Entity
@Table(name="sabores")
@NamedQuery(name="Sabor.findAll", query="SELECT s FROM Sabor s")
public class Sabor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="sab_id")
	private long sabId;

	@Column(name="sab_nombre", length=50, nullable=false)
	private String sabNombre;

	//uni-directional many-to-one association to Estado
	@ManyToOne
	@JoinColumn(name="estado_id", nullable=false, referencedColumnName="estado_id")
	private Estado estado;

	public Sabor() {
	}

	public long getSabId() {
		return this.sabId;
	}

	public void setSabId(long sabId) {
		this.sabId = sabId;
	}

	public String getSabNombre() {
		return this.sabNombre;
	}

	public void setSabNombre(String sabNombre) {
		this.sabNombre = sabNombre;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

}