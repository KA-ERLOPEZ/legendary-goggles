package com.sistema.pizzeria.elpirata.api.core.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


/**
 * The persistent class for the venta_detalle database table.
 * 
 */
@Entity
@Table(name="venta_detalle")
@NamedQuery(name="VentaDetalle.findAll", query="SELECT v FROM VentaDetalle v")
public class VentaDetalle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="vd_id")
	private long vdId;

	@Column(name="vd_cantidad")
	private BigDecimal vdCantidad;

	@Column(name="vd_iva_monto")
	private BigDecimal vdIvaMonto;

	@Column(name="vd_precio")
	private BigDecimal vdPrecio;

	@Column(name="vd_subtotal")
	private BigDecimal vdSubtotal;

	@Column(name="vd_tipo_iva")
	private String vdTipoIva;

	//bi-directional many-to-one association to Personalizacion
	@OneToMany(mappedBy="ventaDetalle")
	private List<Personalizacion> personalizaciones;

	//uni-directional many-to-one association to Stock
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="depo_id", referencedColumnName="depo_id"),
		@JoinColumn(name="prod_id", referencedColumnName="prod_id")
		})
	private Stock stock;

	//bi-directional many-to-one association to VentaCabecera
	@ManyToOne
	@JoinColumn(name="venta_id")
	private VentaCabecera ventaCabecera;

	public VentaDetalle() {
	}

	public long getVdId() {
		return this.vdId;
	}

	public void setVdId(long vdId) {
		this.vdId = vdId;
	}

	public BigDecimal getVdCantidad() {
		return this.vdCantidad;
	}

	public void setVdCantidad(BigDecimal vdCantidad) {
		this.vdCantidad = vdCantidad;
	}

	public BigDecimal getVdIvaMonto() {
		return this.vdIvaMonto;
	}

	public void setVdIvaMonto(BigDecimal vdIvaMonto) {
		this.vdIvaMonto = vdIvaMonto;
	}

	public BigDecimal getVdPrecio() {
		return this.vdPrecio;
	}

	public void setVdPrecio(BigDecimal vdPrecio) {
		this.vdPrecio = vdPrecio;
	}

	public BigDecimal getVdSubtotal() {
		return this.vdSubtotal;
	}

	public void setVdSubtotal(BigDecimal vdSubtotal) {
		this.vdSubtotal = vdSubtotal;
	}

	public String getVdTipoIva() {
		return this.vdTipoIva;
	}

	public void setVdTipoIva(String vdTipoIva) {
		this.vdTipoIva = vdTipoIva;
	}

	public List<Personalizacion> getPersonalizaciones() {
		return this.personalizaciones;
	}

	public void setPersonalizaciones(List<Personalizacion> personalizaciones) {
		this.personalizaciones = personalizaciones;
	}

	public Personalizacion addPersonalizacione(Personalizacion personalizacione) {
		getPersonalizaciones().add(personalizacione);
		personalizacione.setVentaDetalle(this);

		return personalizacione;
	}

	public Personalizacion removePersonalizacione(Personalizacion personalizacione) {
		getPersonalizaciones().remove(personalizacione);
		personalizacione.setVentaDetalle(null);

		return personalizacione;
	}

	public Stock getStock() {
		return this.stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public VentaCabecera getVentaCabecera() {
		return this.ventaCabecera;
	}

	public void setVentaCabecera(VentaCabecera ventaCabecera) {
		this.ventaCabecera = ventaCabecera;
	}

}