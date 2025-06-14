package com.sistema.pizzeria.elpirata.api.core.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;


/**
 * The persistent class for the recetas database table.
 * 
 */
@Entity
@Table(name="recetas")
@NamedQuery(name="Receta.findAll", query="SELECT r FROM Receta r")
public class Receta implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RecetaPK id;

	@Column(name="receta_cantidad", nullable=false, precision=10, scale=2)
	private BigDecimal recetaCantidad;

	//uni-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="prod_id", nullable=false, referencedColumnName="prod_id")
	private Producto platillo;

	//uni-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="ingrediente_id", nullable=false, referencedColumnName="prod_id")
	private Producto ingrediente;

	//uni-directional many-to-one association to UnidadMedida
	@ManyToOne
	@JoinColumn(name="um_id", nullable=false, referencedColumnName="um_id")
	private UnidadMedida unidadMedida;

	public Receta() {
	}

	public RecetaPK getId() {
		return this.id;
	}

	public void setId(RecetaPK id) {
		this.id = id;
	}

	public BigDecimal getRecetaCantidad() {
		return this.recetaCantidad;
	}

	public void setRecetaCantidad(BigDecimal recetaCantidad) {
		this.recetaCantidad = recetaCantidad;
	}

	public Producto getPlatillo() {
		return this.platillo;
	}

	public void setPlatillo(Producto platillo) {
		this.platillo = platillo;
	}

	public Producto getIngrediente() {
		return this.ingrediente;
	}

	public void setIngrediente(Producto ingrediente) {
		this.ingrediente = ingrediente;
	}

	public UnidadMedida getUnidadMedida() {
		return this.unidadMedida;
	}

	public void setUnidadMedida(UnidadMedida unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

}