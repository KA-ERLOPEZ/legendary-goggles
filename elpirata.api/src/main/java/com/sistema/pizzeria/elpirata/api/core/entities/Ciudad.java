package com.sistema.pizzeria.elpirata.api.core.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "ciudades")
public class Ciudad implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ciu_id")
	private long ciuId;

	@NotBlank(message = "El nombre de la ciudad no puede estar vacío")
	@Size(max = 100)
	@Column(name = "ciu_nombre", nullable = false, length = 100)
	private String ciuNombre;

	@OneToMany(mappedBy = "ciudad", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Barrio> barrios;

	@NotNull(message = "El estado no puede ser nulo")
	@ManyToOne
	@JoinColumn(name = "estado_id", nullable = false)
	private Estado estado;

	@NotNull(message = "El país no puede ser nulo")
	@ManyToOne
	@JoinColumn(name = "pais_id", nullable = false)
	private Pais pais;

	public Ciudad(LocalDateTime createdAt, LocalDateTime updatedAt, int ciuId,
			@NotBlank(message = "El nombre de la ciudad no puede estar vacío") @Size(max = 100) String ciuNombre) {
		this.ciuId = ciuId;
		this.ciuNombre = ciuNombre;
	}

	public Ciudad(int ciuId,
			@NotBlank(message = "El nombre de la ciudad no puede estar vacío") @Size(max = 100) String ciuNombre) {

		this.ciuId = ciuId;
		this.ciuNombre = ciuNombre;
	}

	public Ciudad() {
	}

	public long getCiuId() {
		return ciuId;
	}

	public void setCiuId(long ciuId) {
		this.ciuId = ciuId;
	}

	public String getCiuNombre() {
		return ciuNombre;
	}

	public void setCiuNombre(String ciuNombre) {
		this.ciuNombre = ciuNombre;
	}

	public List<Barrio> getBarrios() {
		return barrios;
	}

	public void setBarrios(List<Barrio> barrios) {
		this.barrios = barrios;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
