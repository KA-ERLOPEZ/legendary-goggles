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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


/**
 * The persistent class for the compra database table.
 * 
 */
@Entity
@NamedQuery(name="Compra.findAll", query="SELECT c FROM Compra c")
public class Compra implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="compra_id")
	private long compraId;

	@Temporal(TemporalType.DATE)
	@Column(name="compra_fecha", nullable=false)
	private Date compraFecha;

	@Column(name="compra_total", nullable=false, precision=10, scale=2)
	private BigDecimal compraTotal;

	//uni-directional many-to-one association to Estado
	@ManyToOne
	@JoinColumn(name="estado_id", nullable=false)
	private Estado estado;

	//uni-directional many-to-one association to Proveedor
	@ManyToOne
	@JoinColumn(name="prov_id", nullable=false)
	private Proveedor proveedor;

	//bi-directional many-to-one association to DetalleCompra
	@OneToMany(mappedBy="compra")
	private List<DetalleCompra> detalleCompras;

	public Compra() {
	}

	public long getCompraId() {
		return this.compraId;
	}

	public void setCompraId(long compraId) {
		this.compraId = compraId;
	}

	public Date getCompraFecha() {
		return this.compraFecha;
	}

	public void setCompraFecha(Date compraFecha) {
		this.compraFecha = compraFecha;
	}

	public BigDecimal getCompraTotal() {
		return this.compraTotal;
	}

	public void setCompraTotal(BigDecimal compraTotal) {
		this.compraTotal = compraTotal;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Proveedor getProveedor() {
		return this.proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public List<DetalleCompra> getDetalleCompras() {
		return this.detalleCompras;
	}

	public void setDetalleCompras(List<DetalleCompra> detalleCompras) {
		this.detalleCompras = detalleCompras;
	}

	public DetalleCompra addDetalleCompra(DetalleCompra detalleCompra) {
		getDetalleCompras().add(detalleCompra);
		detalleCompra.setCompra(this);

		return detalleCompra;
	}

	public DetalleCompra removeDetalleCompra(DetalleCompra detalleCompra) {
		getDetalleCompras().remove(detalleCompra);
		detalleCompra.setCompra(null);

		return detalleCompra;
	}

}