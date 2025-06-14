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
 * The persistent class for the productos database table.
 * 
 */
@Entity
@Table(name="productos")
@NamedQuery(name="Producto.findAll", query="SELECT p FROM Producto p")
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="prod_id")
	private long prodId;

	@Column(name="prod_codigo", unique=true, nullable=false, length=50)
	private String prodCodigo;

	@Column(name="prod_descripcion", length=255, nullable=false)
	private String prodDescripcion;

	@Column(name="prod_nombre", nullable=false, length=100)
	private String prodNombre;

	@Column(name="prod_precio_compra", precision=10, scale=2, nullable=false)
	private BigDecimal prodPrecioCompra;

	@Column(name="prod_precio_venta", precision=10, scale=2, nullable=false)
	private BigDecimal prodPrecioVenta;

	@Column(name="prod_produccion", nullable=false, columnDefinition="TINYINT(1) CHECK (prod_produccion IN (0, 1)) DEFAULT 0")
	private boolean prodProduccion;
	
	@Column(name="prod_imagen", length=255, nullable=true)
	private String prodImagen;

	//bi-directional many-to-one association to HistorialPrecio
	@OneToMany(mappedBy="producto", fetch= FetchType.EAGER, orphanRemoval=true, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private List<HistorialPrecio> historialPrecios;

	//uni-directional many-to-one association to Categoria
	@ManyToOne
	@JoinColumn(name="cat_id", referencedColumnName = "cat_id", nullable = false)
	private Categoria categoria;

	//uni-directional many-to-one association to Estado
	@ManyToOne
	@JoinColumn(name="estado_id", referencedColumnName = "estado_id", nullable = false)
	private Estado estado;

	//uni-directional many-to-one association to Marca
	@ManyToOne
	@JoinColumn(name="mar_id", referencedColumnName = "mar_id", nullable = false)
	private Marca marca;

	//uni-directional many-to-one association to Proveedor
	@ManyToOne
	@JoinColumn(name="prov_id", referencedColumnName = "prov_id", nullable = false)
	private Proveedor proveedor;

	//uni-directional many-to-one association to Sabor
	@ManyToOne
	@JoinColumn(name="sab_id", referencedColumnName = "sab_id", nullable = false)
	private Sabor sabor;

	//uni-directional many-to-one association to Tamano
	@ManyToOne
	@JoinColumn(name="tam_id")
	private Tamano tamano;

	//bi-directional many-to-one association to Stock
	@OneToMany(mappedBy="producto", fetch= FetchType.EAGER, orphanRemoval=true, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private List<Stock> stocks;

	public Producto() {
	}

	public long getProdId() {
		return this.prodId;
	}

	public void setProdId(long prodId) {
		this.prodId = prodId;
	}

	public String getProdCodigo() {
		return this.prodCodigo;
	}

	public void setProdCodigo(String prodCodigo) {
		this.prodCodigo = prodCodigo;
	}

	public String getProdDescripcion() {
		return this.prodDescripcion;
	}

	public void setProdDescripcion(String prodDescripcion) {
		this.prodDescripcion = prodDescripcion;
	}

	public String getProdNombre() {
		return this.prodNombre;
	}

	public void setProdNombre(String prodNombre) {
		this.prodNombre = prodNombre;
	}

	public String getProdImagen() {
		return this.prodImagen;
	}

	public void setProdImagen(String prodImagen) {
		this.prodImagen = prodImagen;
	}
	
	public BigDecimal getProdPrecioCompra() {
		return this.prodPrecioCompra;
	}
	
	public void setProdPrecioCompra(BigDecimal prodPrecioCompra) {
		this.prodPrecioCompra = prodPrecioCompra;
	}

	public BigDecimal getProdPrecioVenta() {
		return this.prodPrecioVenta;
	}
	
	public void setProdPrecioVenta(BigDecimal prodPrecioVenta) {
		this.prodPrecioVenta = prodPrecioVenta;
	}
	

	public boolean isProdProduccion() {
		return this.prodProduccion;
	}

	public void setProdProduccion(boolean prodProduccion) {
		this.prodProduccion = prodProduccion;
	}

	public List<HistorialPrecio> getHistorialPrecios() {
		return this.historialPrecios;
	}

	public void setHistorialPrecios(List<HistorialPrecio> historialPrecios) {
		this.historialPrecios = historialPrecios;
	}

	public HistorialPrecio addHistorialPrecio(HistorialPrecio historialPrecio) {
		getHistorialPrecios().add(historialPrecio);
		historialPrecio.setProducto(this);

		return historialPrecio;
	}

	public HistorialPrecio removeHistorialPrecio(HistorialPrecio historialPrecio) {
		getHistorialPrecios().remove(historialPrecio);
		historialPrecio.setProducto(null);

		return historialPrecio;
	}

	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Marca getMarca() {
		return this.marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public Proveedor getProveedor() {
		return this.proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public Sabor getSabor() {
		return this.sabor;
	}

	public void setSabor(Sabor sabor) {
		this.sabor = sabor;
	}

	public Tamano getTamano() {
		return this.tamano;
	}

	public void setTamano(Tamano tamano) {
		this.tamano = tamano;
	}

	public List<Stock> getStocks() {
		return this.stocks;
	}

	public void setStocks(List<Stock> stocks) {
		this.stocks = stocks;
	}

	public Stock addStock(Stock stock) {
		getStocks().add(stock);
		stock.setProducto(this);

		return stock;
	}

	public Stock removeStock(Stock stock) {
		getStocks().remove(stock);
		stock.setProducto(null);

		return stock;
	}

}