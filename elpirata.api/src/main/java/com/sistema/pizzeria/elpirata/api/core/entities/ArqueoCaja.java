package com.sistema.pizzeria.elpirata.api.core.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
 * The persistent class for the arqueo_caja database table.
 * 
 */
@Entity
@Table(name="arqueo_caja")
@NamedQuery(name="ArqueoCaja.findAll", query="SELECT a FROM ArqueoCaja a")
public class ArqueoCaja implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="arqueo_id")
	private long arqueoId;

	@Temporal(TemporalType.DATE)
	@Column(name="arqueo_fecha", nullable=false)
	private Date arqueoFecha;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="arqueo_hora", nullable=false)
	private Date arqueoHora;

	@Column(name="arqueo_saldo", nullable=false, precision=10, scale=2)
	private BigDecimal arqueoSaldo;

	@Column(name="arqueo_saldo_caja", nullable=false, precision=10, scale=2)
	private BigDecimal arqueoSaldoCaja;

	@Column(name="arqueo_saldo_faltante", nullable=false, precision=10, scale=2)
	private BigDecimal arqueoSaldoFaltante;

	@Column(name="arqueo_saldo_sobrante", nullable=false, precision=10, scale=2)
	private BigDecimal arqueoSaldoSobrante;

	//uni-directional many-to-one association to CajaAperturaCierre
	@ManyToOne
	@JoinColumn(name="cac_id", nullable=false)
	private CajaAperturaCierre cajaAperturaCierre;

	//uni-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="usu_id", nullable=false)
	private Usuario usuario;

	//bi-directional many-to-one association to DetalleArqueo
	@OneToMany(mappedBy="arqueoCaja")
	private List<DetalleArqueo> detalleArqueos;

	public ArqueoCaja() {
	}

	public long getArqueoId() {
		return this.arqueoId;
	}

	public void setArqueoId(long arqueoId) {
		this.arqueoId = arqueoId;
	}

	public Date getArqueoFecha() {
		return this.arqueoFecha;
	}

	public void setArqueoFecha(Date arqueoFecha) {
		this.arqueoFecha = arqueoFecha;
	}

	public Date getArqueoHora() {
		return this.arqueoHora;
	}

	public void setArqueoHora(Date arqueoHora) {
		this.arqueoHora = arqueoHora;
	}

	public BigDecimal getArqueoSaldo() {
		return this.arqueoSaldo;
	}

	public void setArqueoSaldo(BigDecimal arqueoSaldo) {
		this.arqueoSaldo = arqueoSaldo;
	}

	public BigDecimal getArqueoSaldoCaja() {
		return this.arqueoSaldoCaja;
	}

	public void setArqueoSaldoCaja(BigDecimal arqueoSaldoCaja) {
		this.arqueoSaldoCaja = arqueoSaldoCaja;
	}

	public BigDecimal getArqueoSaldoFaltante() {
		return this.arqueoSaldoFaltante;
	}

	public void setArqueoSaldoFaltante(BigDecimal arqueoSaldoFaltante) {
		this.arqueoSaldoFaltante = arqueoSaldoFaltante;
	}

	public BigDecimal getArqueoSaldoSobrante() {
		return this.arqueoSaldoSobrante;
	}

	public void setArqueoSaldoSobrante(BigDecimal arqueoSaldoSobrante) {
		this.arqueoSaldoSobrante = arqueoSaldoSobrante;
	}

	public CajaAperturaCierre getCajaAperturaCierre() {
		return this.cajaAperturaCierre;
	}

	public void setCajaAperturaCierre(CajaAperturaCierre cajaAperturaCierre) {
		this.cajaAperturaCierre = cajaAperturaCierre;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<DetalleArqueo> getDetalleArqueos() {
		return this.detalleArqueos;
	}

	public void setDetalleArqueos(List<DetalleArqueo> detalleArqueos) {
		this.detalleArqueos = detalleArqueos;
	}

	public DetalleArqueo addDetalleArqueo(DetalleArqueo detalleArqueo) {
		getDetalleArqueos().add(detalleArqueo);
		detalleArqueo.setArqueoCaja(this);

		return detalleArqueo;
	}

	public DetalleArqueo removeDetalleArqueo(DetalleArqueo detalleArqueo) {
		getDetalleArqueos().remove(detalleArqueo);
		detalleArqueo.setArqueoCaja(null);

		return detalleArqueo;
	}

}