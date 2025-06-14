package com.sistema.pizzeria.elpirata.api.core.entities;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * The persistent class for the paises database table.
 * 
 */
@Entity
@Table(name = "paises")
@NamedQuery(name = "Pais.findAll", query = "SELECT p FROM Pais p")
public class Pais  implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pais_id")
	private long paisId;

	@NotBlank(message = "El nombre del país no puede estar vacío")
	@Size(max = 150, message = "El campo Nombre Pais debe tener maximo 150 caracteres")
	@Column(name = "pais_nombre", nullable = false, length = 150)
	private String paisNombre;

	@OneToMany(mappedBy = "pais", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
	private List<Ciudad> ciudades;

	@NotNull(message = "El estado no puede ser nulo")
	@ManyToOne
	@JoinColumn(name = "estado_id", nullable = false, referencedColumnName = "estado_id")
	private Estado estado;

	public Pais() {
	}

	public long getPaisId() {
		return paisId;
	}

	public void setPaisId(long paisId) {
		this.paisId = paisId;
	}

	public String getPaisNombre() {
		return paisNombre;
	}

	public void setPaisNombre(String paisNombre) {
		this.paisNombre = paisNombre;
	}

	public List<Ciudad> getCiudades() {
		return ciudades;
	}

	public void setCiudades(List<Ciudad> ciudades) {
		this.ciudades = ciudades;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

}