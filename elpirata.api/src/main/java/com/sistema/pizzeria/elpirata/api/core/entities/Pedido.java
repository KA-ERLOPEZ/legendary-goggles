package com.sistema.pizzeria.elpirata.api.core.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
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


/**
 * The persistent class for the pedidos database table.
 * 
 */
@Entity
@Table(name="pedidos")
@NamedQuery(name="Pedido.findAll", query="SELECT p FROM Pedido p")
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pedido_id")
	private long pedidoId;

	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable = false, referencedColumnName = "usu_id")
	private Usuario cliente;

	@Column( name = "pedido_fecha", nullable = false)
	private Date fecha;

	@Column(name = "pedido_total", nullable = false, precision = 10, scale = 2)
	private BigDecimal total;

	@ManyToOne
	@JoinColumn(name = "usu_id", nullable = false, referencedColumnName = "usu_id")
	private Usuario usuario;

	//bi-directional many-to-one association to PedidoDetalle
	@OneToMany(mappedBy="pedido", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
	private List<PedidoDetalle> pedidoDetalles;

	//uni-directional many-to-one association to Estado
	@ManyToOne
	@JoinColumn(name="estado_id")
	private Estado estado;

	//uni-directional many-to-one association to Sucursal
	@ManyToOne
	@JoinColumn(name="sucursal_id")
	private Sucursal sucursal;

	public Pedido() {
	}

	public long getPedidoId() {
		return this.pedidoId;
	}

	public void setPedidoId(long pedidoId) {
		this.pedidoId = pedidoId;
	}

	public Usuario getClienteId() {
		return this.cliente;
	}

	public void setClienteId(Usuario cliente) {
		this.cliente = cliente;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public BigDecimal getTotal() {
		return this.total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<PedidoDetalle> getPedidoDetalles() {
		return this.pedidoDetalles;
	}

	public void setPedidoDetalles(List<PedidoDetalle> pedidoDetalles) {
		this.pedidoDetalles = pedidoDetalles;
	}

	public PedidoDetalle addPedidoDetalle(PedidoDetalle pedidoDetalle) {
		getPedidoDetalles().add(pedidoDetalle);
		pedidoDetalle.setPedido(this);

		return pedidoDetalle;
	}

	public PedidoDetalle removePedidoDetalle(PedidoDetalle pedidoDetalle) {
		getPedidoDetalles().remove(pedidoDetalle);
		pedidoDetalle.setPedido(null);

		return pedidoDetalle;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Sucursal getSucursal() {
		return this.sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

}