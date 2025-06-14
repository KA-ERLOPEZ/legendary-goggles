package com.sistema.pizzeria.elpirata.api.core.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;


/**
 * The persistent class for the cargos database table.
 * 
 */
@Entity
@Table(name="cargos")
@NamedQuery(name="Cargo.findAll", query="SELECT c FROM Cargo c")
public class Cargo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cargo_id")
	private long cargoId;

	@Column(name="cargo_enabled", nullable=false, columnDefinition="TINYINT(1) DEFAULT 1 ")
	private boolean cargoEnabled;

	@Column(name="cargo_nombre", nullable=false, length=45)
	private String cargoNombre;

	public Cargo() {
	}

	public long getCargoId() {
		return this.cargoId;
	}

	public void setCargoId(int cargoId) {
		this.cargoId = cargoId;
	}

	public boolean getCargoEnabled() {
		return this.cargoEnabled;
	}

	public void setCargoEnabled(boolean cargoEnabled) {
		this.cargoEnabled = cargoEnabled;
	}

	public String getCargoNombre() {
		return this.cargoNombre;
	}

	public void setCargoNombre(String cargoNombre) {
		this.cargoNombre = cargoNombre;
	}

}