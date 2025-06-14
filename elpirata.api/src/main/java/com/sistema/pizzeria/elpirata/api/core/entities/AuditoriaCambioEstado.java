package com.sistema.pizzeria.elpirata.api.core.entities;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


/**
 * The persistent class for the auditoria_cambios_estado database table.
 * 
 */
@Entity
@Table(name="auditoria_cambios_estado")
@NamedQuery(name="AuditoriaCambioEstado.findAll", query="SELECT a FROM AuditoriaCambioEstado a")
public class AuditoriaCambioEstado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="audit_id")
	private long auditId;

	@Column(name="estado_anterior", nullable=false)
	private long estadoAnterior;

	@Column(name="estado_nuevo", nullable=false)
	private long estadoNuevo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_cambio", nullable=false)
	private Date fechaCambio;

	@Column(name="motivo_cambio", nullable=false, length=100)
	private String motivoCambio;

	@Column(name="registro_id", nullable=false)
	private long registroId;

	@Column(name="tabla_nombre", nullable=false, length=50)
	private String tablaNombre;

	@Column(name="usu_id", nullable=false)
	private long usuId;

	public AuditoriaCambioEstado() {
	}

	public long getAuditId() {
		return this.auditId;
	}

	public void setAuditId(long auditId) {
		this.auditId = auditId;
	}

	public long getEstadoAnterior() {
		return this.estadoAnterior;
	}

	public void setEstadoAnterior(long estadoAnterior) {
		this.estadoAnterior = estadoAnterior;
	}

	public long getEstadoNuevo() {
		return this.estadoNuevo;
	}

	public void setEstadoNuevo(long estadoNuevo) {
		this.estadoNuevo = estadoNuevo;
	}

	public Date getFechaCambio() {
		return this.fechaCambio;
	}

	public void setFechaCambio(Date fechaCambio) {
		this.fechaCambio = fechaCambio;
	}

	public String getMotivoCambio() {
		return this.motivoCambio;
	}

	public void setMotivoCambio(String motivoCambio) {
		this.motivoCambio = motivoCambio;
	}

	public long getRegistroId() {
		return this.registroId;
	}

	public void setRegistroId(long registroId) {
		this.registroId = registroId;
	}

	public String getTablaNombre() {
		return this.tablaNombre;
	}

	public void setTablaNombre(String tablaNombre) {
		this.tablaNombre = tablaNombre;
	}

	public long getUsuId() {
		return this.usuId;
	}

	public void setUsuId(long usuId) {
		this.usuId = usuId;
	}

}