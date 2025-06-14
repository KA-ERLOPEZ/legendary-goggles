package com.sistema.pizzeria.elpirata.api.core.entities;

import java.io.Serializable;
import java.math.BigDecimal;
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


/**
 * The persistent class for the pedido_detalle database table.
 * 
 */
@Entity
@Table(name="pedido_detalle")
@NamedQuery(name="PedidoDetalle.findAll", query="SELECT p FROM PedidoDetalle p")
public class PedidoDetalle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="detalle_id")
	private long detalleId;
	
	@Column(name="detalle_cantidad", nullable = false)
	private int cantidad;

	@Column(name="precio_unitario", nullable = false, precision = 10, scale = 2)
	private BigDecimal precioUnitario;

	@Column(name = "sub_total", nullable = false, precision = 10, scale = 2)
	private BigDecimal subTotal;

	//bi-directional many-to-one association to Pedido
	@ManyToOne
	@JoinColumn(name="pedido_id", nullable = false, referencedColumnName = "pedido_id")
	private Pedido pedido;

	//uni-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="producto_id", nullable = false, referencedColumnName = "prod_id")
	private Producto producto;

	//bi-directional many-to-one association to Personalizacion
	@OneToMany(mappedBy="pedidoDetalle", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
	private List<Personalizacion> personalizaciones;

	public PedidoDetalle() {
	}

	public long getDetalleId() {
		return this.detalleId;
	}

	public void setDetalleId(long detalleId) {
		this.detalleId = detalleId;
	}

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getPrecioUnitario() {
		return this.precioUnitario;
	}

	public void setPrecioUnitario(BigDecimal precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public BigDecimal getSubtotal() {
		return this.subTotal;
	}

	public void setSubtotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public Pedido getPedido() {
		return this.pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public List<Personalizacion> getPersonalizaciones() {
		return this.personalizaciones;
	}

	public void setPersonalizaciones(List<Personalizacion> personalizaciones) {
		this.personalizaciones = personalizaciones;
	}

	public Personalizacion addPersonalizacione(Personalizacion personalizacione) {
		getPersonalizaciones().add(personalizacione);
		personalizacione.setPedidoDetalle(this);

		return personalizacione;
	}

	public Personalizacion removePersonalizacione(Personalizacion personalizacione) {
		getPersonalizaciones().remove(personalizacione);
		personalizacione.setPedidoDetalle(null);

		return personalizacione;
	}

}