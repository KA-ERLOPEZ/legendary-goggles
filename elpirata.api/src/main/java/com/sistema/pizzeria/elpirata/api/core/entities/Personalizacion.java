package com.sistema.pizzeria.elpirata.api.core.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import com.sistema.pizzeria.elpirata.api.core.commons.Auditable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;


/**
 * The persistent class for the personalizaciones database table.
 * 
 */
@Entity
@Table(name="personalizaciones")
@NamedQuery(name="Personalizacion.findAll", query="SELECT p FROM Personalizacion p")
public class Personalizacion extends Auditable implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="personalizacion_id")
	private long personalizacionId;
	
	@Column(nullable = false, precision = 10, scale = 2)
	private BigDecimal cantidad;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="estado_id", referencedColumnName = "estado_id", nullable = false)
	private Estado estado;

	@Column(name="precio_adicional", precision = 10, scale = 2)
	private BigDecimal precioAdicional;

	@Column(name="tipo_personalizacion", length = 5,  columnDefinition = "VARCHAR(5) CHECK(tipo_personalizacion IN ('extra', 'sin'))")
	private String tipoPersonalizacion;

	//bi-directional many-to-one association to PedidoDetalle
	@ManyToOne
	@JoinColumn(name="detalle_id", referencedColumnName = "detalle_id", nullable = false)
	private PedidoDetalle pedidoDetalle;

	//uni-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="producto_id", referencedColumnName = "prod_id", nullable = false)
	private Producto producto;

	//bi-directional many-to-one association to VentaDetalle
	@ManyToOne
	@JoinColumn(name="venta_detalle_id", referencedColumnName = "vd_id", nullable = false)
	private VentaDetalle ventaDetalle;

	public Personalizacion() {
	}

	public long getPersonalizacionId() {
		return this.personalizacionId;
	}

	public void setPersonalizacionId(long personalizacionId) {
		this.personalizacionId = personalizacionId;
	}

	public BigDecimal getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public BigDecimal getPrecioAdicional() {
		return this.precioAdicional;
	}
	
	public void setPrecioAdicional(BigDecimal precioAdicional) {
		this.precioAdicional = precioAdicional;
	}
	

	public String getTipoPersonalizacion() {
		return this.tipoPersonalizacion;
	}

	public void setTipoPersonalizacion(String tipoPersonalizacion) {
		this.tipoPersonalizacion = tipoPersonalizacion;
	}

	public PedidoDetalle getPedidoDetalle() {
		return this.pedidoDetalle;
	}

	public void setPedidoDetalle(PedidoDetalle pedidoDetalle) {
		this.pedidoDetalle = pedidoDetalle;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public VentaDetalle getVentaDetalle() {
		return this.ventaDetalle;
	}

	public void setVentaDetalle(VentaDetalle ventaDetalle) {
		this.ventaDetalle = ventaDetalle;
	}

}