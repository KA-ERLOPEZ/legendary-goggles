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
 * The persistent class for the marcas database table.
 * 
 */
@Entity
@Table(name="marcas")
@NamedQuery(name="Marca.findAll", query="SELECT m FROM Marca m")
public class Marca implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="mar_id")
	private long marId;

	@Column(name="mar_nombre", length=50, nullable=false, unique=true)
	private String marNombre;

	//uni-directional many-to-one association to Estado
	@ManyToOne
	@JoinColumn(name="estado_id", nullable=false, referencedColumnName="estado_id")
	private Estado estado;

	public Marca() {
	}

	public long getMarId() {
		return this.marId;
	}

	public void setMarId(long marId) {
		this.marId = marId;
	}

	public String getMarNombre() {
		return this.marNombre;
	}

	public void setMarNombre(String marNombre) {
		this.marNombre = marNombre;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

}