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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * The persistent class for the estados database table.
 * 
 */

@Entity
@Table(name = "estados")
@NamedQuery(name = "Estado.findAll", query = "SELECT e FROM Estado e")
public class Estado implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "estado_id")
	private long estadoId;

	@NotBlank(message = "El campo Nombre Estado no debe qudar vacio")
	@Size(min = 4, max = 50, message = "El campo Nombre Estado debe tener entre 5 y 50 carcteres")
	@Column(name = "estado_nombre", nullable = false, length = 50)
	private String estadoNombre;

	// bi-directional many-to-one association to Dominio
	@NotNull(message = "El campo Domino no debe quedar vacio")
	@ManyToOne
	@JoinColumn(name = "dominio_id", nullable = false)
	private Dominio dominio;

	@Column(name = "estado_enabled", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
	private boolean estadoEnabled;

	public Estado(int estadoId,
			@NotBlank(message = "El campo Nombre Estado no debe qudar vacio") @Size(min = 4, max = 50, message = "El campo Nombre Estado debe tener entre 5 y 50 carcteres") String estadoNombre) {
		this.estadoId = estadoId;
		this.estadoNombre = estadoNombre;
	}

	public Estado(int estadoId,
			@NotBlank(message = "El campo Nombre Estado no debe qudar vacio") @Size(min = 4, max = 50, message = "El campo Nombre Estado debe tener entre 5 y 50 carcteres") String estadoNombre,
			Dominio dominio) {
		this.estadoId = estadoId;
		this.estadoNombre = estadoNombre;
		this.dominio = dominio;
	}

	public long getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(long estadoId) {
		this.estadoId = estadoId;
	}

	public boolean isEstadoEnabled() {
		return estadoEnabled;
	}

	public void setEstadoEnabled(boolean estadoEnabled) {
		this.estadoEnabled = estadoEnabled;
	}

	public String getEstadoNombre() {
		return estadoNombre;
	}

	public void setEstadoNombre(String estadoNombre) {
		this.estadoNombre = estadoNombre;
	}

	public Dominio getDominio() {
		return dominio;
	}

	public void setDominio(Dominio dominio) {
		this.dominio = dominio;
	}

	public Estado() {
	}

}