package com.sistema.pizzeria.elpirata.api.core.entities;

import java.io.Serializable;

/**
 * The persistent class for the barrios database table.
 * 
 */
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "barrios")
public class Barrio implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bar_id")
	private long barId;

	@Column(name = "bar_nombre", nullable = false, length = 50)
	private String barNombre;

	@NotNull(message = "La ciudad no puede ser nula")
	@ManyToOne
	@JoinColumn(name = "ciu_id", nullable = false)
	private Ciudad ciudad;

	@NotNull(message = "El estado no puede ser nulo")
	@ManyToOne
	@JoinColumn(name = "estado_id", nullable = false)
	private Estado estado;

	public Barrio() {
	}



	public long getBarId() {
		return barId;
	}

	public void setBarId(long barId) {
		this.barId = barId;
	}

	public String getBarNombre() {
		return barNombre;
	}

	public void setBarNombre(String barNombre) {
		this.barNombre = barNombre;
	}

	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
