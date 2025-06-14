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
 * The persistent class for the orden_produccion database table.
 * 
 */
@Entity
@Table(name="orden_produccion")
@NamedQuery(name="OrdenProduccion.findAll", query="SELECT o FROM OrdenProduccion o")
public class OrdenProduccion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="op_id")
	private long opId;

	@Column(name="op_cantidad", nullable = false, precision = 10, scale = 2)
	private BigDecimal opCantidad;

	@Temporal(TemporalType.DATE)
	@Column(name="op_fecha", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date opFecha;

	@Temporal(TemporalType.DATE)
	@Column(name="op_fecha_entrega", columnDefinition = "TIMESTAMP")
	private Date opFechaEntrega;

	//bi-directional many-to-one association to OpDetalle
	@OneToMany(mappedBy="ordenProduccion")
	private List<OpDetalle> opDetalles;

	//uni-directional many-to-one association to Estado
	@ManyToOne
	@JoinColumn(name="estado_id")
	private Estado estado;

	//uni-directional many-to-one association to PedidoDetalle
	@ManyToOne
	@JoinColumn(name="detalle_id")
	private PedidoDetalle pedidoDetalle;

	//uni-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="usu_id")
	private Usuario usuario;

	public OrdenProduccion() {
	}

	public long getOpId() {
		return this.opId;
	}

	public void setOpId(long opId) {
		this.opId = opId;
	}

	public BigDecimal getOpCantidad() {
		return this.opCantidad;
	}

	public void setOpCantidad(BigDecimal opCantidad) {
		this.opCantidad = opCantidad;
	}

	public Date getOpFecha() {
		return this.opFecha;
	}

	public void setOpFecha(Date opFecha) {
		this.opFecha = opFecha;
	}

	public Date getOpFechaEntrega() {
		return this.opFechaEntrega;
	}

	public void setOpFechaEntrega(Date opFechaEntrega) {
		this.opFechaEntrega = opFechaEntrega;
	}

	public List<OpDetalle> getOpDetalles() {
		return this.opDetalles;
	}

	public void setOpDetalles(List<OpDetalle> opDetalles) {
		this.opDetalles = opDetalles;
	}

	public OpDetalle addOpDetalle(OpDetalle opDetalle) {
		getOpDetalles().add(opDetalle);
		opDetalle.setOrdenProduccion(this);

		return opDetalle;
	}

	public OpDetalle removeOpDetalle(OpDetalle opDetalle) {
		getOpDetalles().remove(opDetalle);
		opDetalle.setOrdenProduccion(null);

		return opDetalle;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public PedidoDetalle getPedidoDetalle() {
		return this.pedidoDetalle;
	}

	public void setPedidoDetalle(PedidoDetalle pedidoDetalle) {
		this.pedidoDetalle = pedidoDetalle;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}