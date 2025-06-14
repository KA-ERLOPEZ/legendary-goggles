package com.sistema.pizzeria.elpirata.api.web.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ProductoDTO {

	private long prodId;
	
	@NotNull(message = "El campo codigo es obligatorio")
	@NotBlank(message = "El campo codigo no puede estar vacio")
	@Size(min = 1, max = 50, message = "El campo codigo no puede tener mas de 50 caracteres")
	private String prodCodigo;
	
	@NotNull(message = "El campo nombre es obligatorio")
	@NotBlank(message = "El campo nombre no puede estar vacio")
	@Size(min = 1, max = 50, message = "El campo nombre no puede tener mas de 50 caracteres")
	private String prodNombre;
	
	@NotNull(message = "El campo descripcion es obligatorio")
	@NotBlank(message = "El campo descripcion no puede estar vacio")
	@Size(min = 1, max = 200, message = "El campo descripcion no puede tener mas de 200 caracteres")
	private String prodDescripcion;
	
	@NotNull(message = "El campo precio compra es obligatorio")
	@NotBlank(message = "El campo precio compra no puede estar vacio")
	@DecimalMax(value = "9999999.99", message = "El campo precio compra no puede ser mayor a 9999999.99")
	@DecimalMin(value = "0.01", message = "El campo precio compra no puede ser menor a 0.01")
	@Pattern(regexp = "^[0-9]+(\\.[0-9]{1,2})?$", message = "El campo precio compra no es un numero valido")
	private BigDecimal prodPrecioCompra;
	
	@NotNull(message = "El campo precio venta es obligatorio")
	@NotBlank(message = "El campo precio venta no puede estar vacio")
	@DecimalMax(value = "9999999.99", message = "El campo precio venta no puede ser mayor a 9999999.99")
	@DecimalMin(value = "0.01", message = "El campo precio venta no puede ser menor a 0.01")
	@Pattern(regexp = "^[0-9]+(\\.[0-9]{1,2})?$", message = "El campo precio venta no es un numero valido")
	private BigDecimal prodPrecioVenta;
	
	@NotNull(message = "El campo produccion es obligatorio")
	@NotBlank(message = "El campo produccion no puede estar vacio")
	@Pattern(regexp = "^[0-1]$", message = "El campo produccion no es un valor valido")
	private boolean prodProduccion;
	
	@NotNull(message = "El campo imagen es obligatorio")
	@NotBlank(message = "El campo imagen no puede estar vacio")
	@Size(min = 1, max = 200, message = "El campo imagen no puede tener mas de 200 caracteres")
	//@Pattern(regexp = "^(http|https)://.*", message = "El campo imagen no es una URL valida")
	private String prodImagen;
	
	@NotNull(message = "El campo categoria es obligatorio")
	@NotBlank(message = "El campo categoria no puede estar vacio")
	@Pattern(regexp = "^[0-9]+$", message = "El campo categoria no es un numero valido")
	private double catId;
	
	private String catNombre;
	
	@NotNull(message = "El campo marca es obligatorio")
	@NotBlank(message = "El campo marca no puede estar vacio")
	@Pattern(regexp = "^[0-9]+$", message = "El campo marca no es un numero valido")
	private double marId;
	
	private String marNombre;
	
	@NotNull(message = "El campo estado es obligatorio")
	@NotBlank(message = "El campo estado no puede estar vacio")
	@Pattern(regexp = "^[0-9]+$", message = "El campo estado no es un numero valido")
	private double estadoId;
	
	private String estadoNombre;
	
	@NotNull(message = "El campo tamano es obligatorio")
	@NotBlank(message = "El campo tamano no puede estar vacio")
	@Pattern(regexp = "^[0-9]+$", message = "El campo tamano no es un numero valido")
	private double tamId;
	
	private String tamDescripcion;
	
	@NotNull(message = "El campo proveedor es obligatorio")
	@NotBlank(message = "El campo proveedor no puede estar vacio")
	@Pattern(regexp = "^[0-9]+$", message = "El campo proveedor no es un numero valido")
	private double provId;
	
	private String provRazonSocial;
	
	@NotNull(message = "El campo sabor es obligatorio")
	@NotBlank(message = "El campo sabor no puede estar vacio")
	@Pattern(regexp = "^[0-9]+$", message = "El campo sabor no es un numero valido")
	private double sabId;
	
	private String sabNombre;
	

	public ProductoDTO() {
		super();
	}

	public ProductoDTO(long prodId, String prodCodigo, String prodNombre, String prodDescripcion,
			BigDecimal prodPrecioCompra, BigDecimal prodPrecioVenta, boolean prodProduccion, String prodImagen, double catId,
			String catNombre, double marId, String marNombre, double estadoId, String estadoNombre, double tamId,
			String tamDescripcion, double provId, String provRazonSocial, double sabId, String sabNombre) {
		super();
		this.prodId = prodId;
		this.prodCodigo = prodCodigo;
		this.prodNombre = prodNombre;
		this.prodDescripcion = prodDescripcion;
		this.prodPrecioCompra = prodPrecioCompra;
		this.prodPrecioVenta = prodPrecioVenta;
		this.prodProduccion = prodProduccion;
		this.prodImagen = prodImagen;
		this.catId = catId;
		this.catNombre = catNombre;
		this.marId = marId;
		this.marNombre = marNombre;
		this.estadoId = estadoId;
		this.estadoNombre = estadoNombre;
		this.tamId = tamId;
		this.tamDescripcion = tamDescripcion;
		this.provId = provId;
		this.provRazonSocial = provRazonSocial;
		this.sabId = sabId;
		this.sabNombre = sabNombre;
	}

	public long getProdId() {
		return prodId;
	}


	public void setProdId(long prodId) {
		this.prodId = prodId;
	}


	public String getProdCodigo() {
		return prodCodigo;
	}


	public void setProdCodigo(String prodCodigo) {
		this.prodCodigo = prodCodigo;
	}


	public String getProdNombre() {
		return prodNombre;
	}


	public void setProdNombre(String prodNombre) {
		this.prodNombre = prodNombre;
	}


	public String getProdDescripcion() {
		return prodDescripcion;
	}


	public void setProdDescripcion(String prodDescripcion) {
		this.prodDescripcion = prodDescripcion;
	}


	public BigDecimal getProdPrecioCompra() {
		return prodPrecioCompra;
	}
	
	public void setProdPrecioCompra(BigDecimal prodPrecioCompra) {
		this.prodPrecioCompra = prodPrecioCompra;
	}

	public BigDecimal getProdPrecioVenta() {
		return prodPrecioVenta;
	}

	public void setProdPrecioVenta(BigDecimal prodPrecioVenta) {
		this.prodPrecioVenta = prodPrecioVenta;
	}
	
	public boolean isProdProduccion() {
		return prodProduccion;
	}


	public void setProdProduccion(boolean prodProduccion) {
		this.prodProduccion = prodProduccion;
	}


	public String getProdImagen() {
		return prodImagen;
	}


	public void setProdImagen(String prodImagen) {
		this.prodImagen = prodImagen;
	}


	public double getCatId() {
		return catId;
	}


	public void setCatId(double catId) {
		this.catId = catId;
	}


	public String getCatNombre() {
		return catNombre;
	}


	public void setCatNombre(String catNombre) {
		this.catNombre = catNombre;
	}


	public double getMarId() {
		return marId;
	}


	public void setMarId(double marId) {
		this.marId = marId;
	}


	public String getMarNombre() {
		return marNombre;
	}


	public void setMarNombre(String marNombre) {
		this.marNombre = marNombre;
	}


	public double getEstadoId() {
		return estadoId;
	}


	public void setEstadoId(double estadoId) {
		this.estadoId = estadoId;
	}


	public String getEstadoNombre() {
		return estadoNombre;
	}


	public void setEstadoNombre(String estadoNombre) {
		this.estadoNombre = estadoNombre;
	}


	public double getTamId() {
		return tamId;
	}


	public void setTamId(double tamId) {
		this.tamId = tamId;
	}


	public String getTamDescripcion() {
		return tamDescripcion;
	}


	public void setTamDescripcion(String tamDescripcion) {
		this.tamDescripcion = tamDescripcion;
	}


	public double getProvId() {
		return provId;
	}


	public void setProvId(double provId) {
		this.provId = provId;
	}


	public String getProvRazonSocial() {
		return provRazonSocial;
	}


	public void setProvRazonSocial(String provRazonSocial) {
		this.provRazonSocial = provRazonSocial;
	}


	public double getSabId() {
		return sabId;
	}


	public void setSabId(double sabId) {
		this.sabId = sabId;
	}


	public String getSabNombre() {
		return sabNombre;
	}


	public void setSabNombre(String sabNombre) {
		this.sabNombre = sabNombre;
	}
	
	
	
}
