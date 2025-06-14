package com.sistema.pizzeria.elpirata.api.core.entities;

import java.io.Serializable;
import java.util.Date;
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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


/**
 * The persistent class for the caja_apertura_cierre database table.
 * 
 */
@Entity
@Table(name="caja_apertura_cierre")
@NamedQuery(name="CajaAperturaCierre.findAll", query="SELECT c FROM CajaAperturaCierre c")
public class CajaAperturaCierre implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cac_id")
	private long cacId;

	@Column(name="cac_estado", nullable=false, columnDefinition="TINYINT(1) CHECK(cac_estado IN(0, 1)) DEFAULT 1")
	private boolean cacEstado;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="cac_fecha_apertura", nullable=false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date cacFechaApertura;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="cac_fecha_cierre" )
	private Date cacFechaCierre;

	//uni-directional many-to-one association to Caja
	@ManyToOne
	@JoinColumn(name="caja_id", nullable=false)
	private Caja caja;

	//uni-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="usu_id", nullable=false)
	private Usuario usuario;

	//bi-directional many-to-one association to Transaccion
	@OneToMany(mappedBy="cajaAperturaCierre", orphanRemoval=true, cascade= {CascadeType.PERSIST, CascadeType.MERGE})
	private List<Transaccion> transacciones;

	public CajaAperturaCierre() {
	}

	public long getCacId() {
		return this.cacId;
	}

	public void setCacId(int cacId) {
		this.cacId = cacId;
	}

	public boolean isCacEstado() {
		return this.cacEstado;
	}

	public void setCacEstado(boolean cacEstado) {
		this.cacEstado = cacEstado;
	}

	public Date getCacFechaApertura() {
		return this.cacFechaApertura;
	}

	public void setCacFechaApertura(Date cacFechaApertura) {
		this.cacFechaApertura = cacFechaApertura;
	}

	public Date getCacFechaCierre() {
		return this.cacFechaCierre;
	}

	public void setCacFechaCierre(Date cacFechaCierre) {
		this.cacFechaCierre = cacFechaCierre;
	}

	public Caja getCaja() {
		return this.caja;
	}

	public void setCaja(Caja caja) {
		this.caja = caja;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Transaccion> getTransacciones() {
		return this.transacciones;
	}

	public void setTransacciones(List<Transaccion> transacciones) {
		this.transacciones = transacciones;
	}

	public Transaccion addTransaccione(Transaccion transaccione) {
		getTransacciones().add(transaccione);
		transaccione.setCajaAperturaCierre(this);

		return transaccione;
	}

	public Transaccion removeTransaccione(Transaccion transaccione) {
		getTransacciones().remove(transaccione);
		transaccione.setCajaAperturaCierre(null);

		return transaccione;
	}

}