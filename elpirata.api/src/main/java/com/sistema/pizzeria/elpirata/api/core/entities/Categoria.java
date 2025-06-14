package com.sistema.pizzeria.elpirata.api.core.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;


/**
 * The persistent class for the categorias database table.
 * 
 */
@Entity
@Table(name="categorias")
@NamedQuery(name="Categoria.findAll", query="SELECT c FROM Categoria c")
public class Categoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cat_id")
	private long catId;

	@Column(name="cat_nombre", length=45, nullable=false)
	private String catNombre;

	//uni-directional many-to-one association to Estado
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="estado_id", nullable=false)
	private Estado estado;

	public Categoria() {
	}

	public long getCatId() {
		return this.catId;
	}

	public void setCatId(long catId) {
		this.catId = catId;
	}

	public String getCatNombre() {
		return this.catNombre;
	}

	public void setCatNombre(String catNombre) {
		this.catNombre = catNombre;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

}