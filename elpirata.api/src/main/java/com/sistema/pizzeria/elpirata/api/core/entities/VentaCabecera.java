package com.sistema.pizzeria.elpirata.api.core.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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


/**
 * The persistent class for the venta_cabecera database table.
 * 
 */
@Entity
@Table(name="venta_cabecera")
@NamedQuery(name="VentaCabecera.findAll", query="SELECT v FROM VentaCabecera v")
public class VentaCabecera implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="venta_id")
	private long ventaId;

	@Column(name="per_id")
	private long perId;

	@Temporal(TemporalType.DATE)
	@Column(name="venta_fecha", nullable=false)
	private Date ventaFecha;


	//uni-directional many-to-one association to CajaAperturaCierre
	@ManyToOne
	@JoinColumn(name="cac_id", nullable=false, referencedColumnName="cac_id")
	private CajaAperturaCierre cajaAperturaCierre;

	//uni-directional many-to-one association to Estado
	@ManyToOne
	@JoinColumn(name="estado_id", nullable=false, referencedColumnName="estado_id")
	private Estado estado;

	//uni-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="usu_id", nullable=false, referencedColumnName="usu_id")
	private Usuario usuario;

	//bi-directional many-to-one association to VentaDetalle
	@OneToMany(mappedBy="ventaCabecera", orphanRemoval=true, cascade= {CascadeType.PERSIST, CascadeType.MERGE}, fetch=FetchType.LAZY)
	private List<VentaDetalle> ventaDetalles;

	public VentaCabecera() {
	}

	public long getVentaId() {
		return this.ventaId;
	}

	public void setVentaId(long ventaId) {
		this.ventaId = ventaId;
	}

	public long getPerId() {
		return this.perId;
	}

	public void setPerId(long perId) {
		this.perId = perId;
	}

//	public BigDecimal getTotalExentas() {
//		return this.totalExentas;
//	}
//
//	public void setTotalExentas(BigDecimal totalExentas) {
//		this.totalExentas = totalExentas;
//	}

	public Date getVentaFecha() {
		return this.ventaFecha;
	}

	public void setVentaFecha(Date ventaFecha) {
		this.ventaFecha = ventaFecha;
	}

//	public String getVentaTipo() {
//		return this.ventaTipo;
//	}
//
//	public void setVentaTipo(String ventaTipo) {
//		this.ventaTipo = ventaTipo;
//	}
//
//	public BigDecimal getVentaTotal() {
//		return this.ventaTotal;
//	}
//
//	public void setVentaTotal(BigDecimal ventaTotal) {
//		this.ventaTotal = ventaTotal;
//	}
//
//	public BigDecimal getVentaTotalIva10() {
//		return this.ventaTotalIva10;
//	}
//
//	public void setVentaTotalIva10(BigDecimal ventaTotalIva10) {
//		this.ventaTotalIva10 = ventaTotalIva10;
//	}
//
//	public BigDecimal getVentaTotalIva5() {
//		return this.ventaTotalIva5;
//	}
//
//	public void setVentaTotalIva5(BigDecimal ventaTotalIva5) {
//		this.ventaTotalIva5 = ventaTotalIva5;
//	}

	public CajaAperturaCierre getCajaAperturaCierre() {
		return this.cajaAperturaCierre;
	}

	public void setCajaAperturaCierre(CajaAperturaCierre cajaAperturaCierre) {
		this.cajaAperturaCierre = cajaAperturaCierre;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<VentaDetalle> getVentaDetalles() {
		return this.ventaDetalles;
	}

	public void setVentaDetalles(List<VentaDetalle> ventaDetalles) {
		this.ventaDetalles = ventaDetalles;
	}

	public VentaDetalle addVentaDetalle(VentaDetalle ventaDetalle) {
		getVentaDetalles().add(ventaDetalle);
		ventaDetalle.setVentaCabecera(this);

		return ventaDetalle;
	}

	public VentaDetalle removeVentaDetalle(VentaDetalle ventaDetalle) {
		getVentaDetalles().remove(ventaDetalle);
		ventaDetalle.setVentaCabecera(null);

		return ventaDetalle;
	}

}