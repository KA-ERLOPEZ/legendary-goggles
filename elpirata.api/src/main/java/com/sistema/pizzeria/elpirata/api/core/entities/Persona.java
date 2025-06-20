package com.sistema.pizzeria.elpirata.api.core.entities;

import java.io.Serializable;
import java.util.Date;

import com.sistema.pizzeria.elpirata.api.core.commons.Auditable;

/**
 * The persistent class for the personas database table.
 * 
 */
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

@Entity
@Table(name = "personas")
@NamedQuery(name = "Persona.findAll", query = "SELECT p FROM Persona p")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Persona extends Auditable implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "per_id")
	private long perId;

	@NotNull(message = "El barrio no puede ser nulo")
	@ManyToOne
	@JoinColumn(name = "bar_id", nullable = false, referencedColumnName = "bar_id")
	private Barrio barrio;

	@NotNull(message = "La nacionalidad no puede ser nula")
	@ManyToOne
	@JoinColumn(name = "nac_id", nullable = false, referencedColumnName = "nac_id")
	private Nacionalidad nacionalidad;

	@NotBlank(message = "La dirección no puede estar vacía")
	@Column(name = "per_direccion", nullable = false, length = 150)
	private String perDireccion;

	@Email(message = "El email debe ser válido")
	@Column(name = "per_email", unique = true, length = 150)
	private String perEmail;

	@Column(name = "per_enabled", nullable = false)
	private boolean perEnabled = true;

	@Past(message = "La fecha de nacimiento debe ser en el pasado")
	@Temporal(TemporalType.DATE)
	@Column(name = "per_fec_nac", nullable = false)
	private Date perFecNac;

	@Column(name = "per_nro_ruc", unique = true, length = 60)
	private String perNroRuc;

	@NotBlank(message = "El teléfono no puede estar vacío")
	@Column(name = "per_telefono", nullable = false, length = 60)
	private String perTelefono;

	public Persona(long perId, Barrio barrio, Nacionalidad nacionalidad, String perDireccion, String perEmail,
			boolean perEnabled, Date perFecNac, String perNroRuc, String perTelefono) {
		this.perId = perId;
		this.barrio = barrio;
		this.nacionalidad = nacionalidad;
		this.perDireccion = perDireccion;
		this.perEmail = perEmail;
		this.perEnabled = perEnabled;
		this.perFecNac = perFecNac;
		this.perNroRuc = perNroRuc;
		this.perTelefono = perTelefono;
	}

	public Persona() {

	}

	public long getPerId() {
		return perId;
	}

	public void setPerId(long perId) {
		this.perId = perId;
	}

	public Barrio getBarrio() {
		return barrio;
	}

	public void setBarrio(Barrio barrio) {
		this.barrio = barrio;
	}

	public Nacionalidad getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(Nacionalidad nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getPerDireccion() {
		return perDireccion;
	}

	public void setPerDireccion(String perDireccion) {
		this.perDireccion = perDireccion;
	}

	public String getPerEmail() {
		return perEmail;
	}

	public void setPerEmail(String perEmail) {
		this.perEmail = perEmail;
	}

	public boolean isPerEnabled() {
		return perEnabled;
	}

	public void setPerEnabled(boolean perEnabled) {
		this.perEnabled = perEnabled;
	}

	public Date getPerFecNac() {
		return perFecNac;
	}

	public void setPerFecNac(Date perFecNac) {
		this.perFecNac = perFecNac;
	}

	public String getPerNroRuc() {
		return perNroRuc;
	}

	public void setPerNroRuc(String perNroRuc) {
		this.perNroRuc = perNroRuc;
	}

	public String getPerTelefono() {
		return perTelefono;
	}

	public void setPerTelefono(String perTelefono) {
		this.perTelefono = perTelefono;
	}

}
