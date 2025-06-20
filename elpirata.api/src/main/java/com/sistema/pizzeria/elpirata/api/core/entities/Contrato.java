package com.sistema.pizzeria.elpirata.api.core.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.sistema.pizzeria.elpirata.api.core.commons.Auditable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;


/**
 * The persistent class for the contratos database table.
 * 
 */
@Entity
@Table(name="contratos", uniqueConstraints = {@UniqueConstraint(columnNames = {"per_id", "cont_fec_inicio"}, name = "uk_contratos_1")})
@NamedQuery(name="Contrato.findAll", query="SELECT c FROM Contrato c")
public class Contrato extends Auditable implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cont_id")
	private long id;

	@Temporal(TemporalType.DATE)
	@Column(name="cont_fec_fin")
	private Date contFecFin;

	@Temporal(TemporalType.DATE)
	@Column(name="cont_fec_inicio", nullable = false)
	private Date contFecInicio;

	@Column(name="cont_salario", nullable = false, precision=10, scale=2)
	private BigDecimal contSalario;

	//uni-directional many-to-one association to Cargo
	@ManyToOne
	@JoinColumn(name="cargo_id", nullable = false, referencedColumnName = "cargo_id")
	private Cargo cargo;

	//uni-directional many-to-one association to Estado
	@ManyToOne
	@JoinColumn(name="estado_id", nullable = false, referencedColumnName = "estado_id")
	private Estado estado;
	
	//bi-directional many-to-one association to Persona
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name="per_id", nullable = false, referencedColumnName = "per_id")
	private PersonaFisica persona;

	//uni-directional many-to-one association to TipoDocumento
	@ManyToOne
	@JoinColumn(name="tipodoc_id", nullable = false, referencedColumnName = "tipodoc_id")
	private TipoDocumento tipoDocumento;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE},
			orphanRemoval = true, mappedBy = "contrato")
	private List<AsignacionTurno> asignaciones;

	public Contrato() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getContFecFin() {
		return this.contFecFin;
	}

	public void setContFecFin(Date contFecFin) {
		this.contFecFin = contFecFin;
	}

	public Date getContFecInicio() {
		return this.contFecInicio;
	}

	public void setContFecInicio(Date contFecInicio) {
		this.contFecInicio = contFecInicio;
	}

	public BigDecimal getContSalario() {
		return this.contSalario;
	}

	public void setContSalario(BigDecimal contSalario) {
		this.contSalario = contSalario;
	}

	public Cargo getCargo() {
		return this.cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public TipoDocumento getTipoDocumento() {
		return this.tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	
	public PersonaFisica getPersona() {
		return this.persona;
	}
	
	public void setPersona(PersonaFisica persona) {
		this.persona = persona;
	}

	public List<AsignacionTurno> getAsignaciones() {
		return asignaciones;
	}

	public void setAsignaciones(List<AsignacionTurno> asignaciones) {
		this.asignaciones = asignaciones;
	}
	
	
}