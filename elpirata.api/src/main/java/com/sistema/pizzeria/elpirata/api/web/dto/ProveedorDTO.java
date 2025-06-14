package com.sistema.pizzeria.elpirata.api.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ProveedorDTO {

	private long provId;
	
	@NotBlank(message = "El campo razon social es obligatorio")
	@NotNull(message = "El campo razon social es obligatorio")
	@Size(min = 3, max = 50, message = "El campo razon social debe tener entre 3 y 50 caracteres")
	@Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "El campo razon social solo puede contener letras y numeros")
	private String provRazonSocial;
	
	@NotBlank(message = "El campo RUC es obligatorio")
	@NotNull(message = "El campo RUC es obligatorio")
	@Size(min = 13, max = 13, message = "El campo RUC debe tener 13 caracteres")
	@Pattern(regexp = "^[0-9]+$", message = "El campo RUC solo puede contener numeros")
	private String provRuc;
	
	@NotBlank(message = "El campo direccion es obligatorio")
	@NotNull(message = "El campo direccion es obligatorio")
	@Size(min = 3, max = 50, message = "El campo direccion debe tener entre 3 y 50 caracteres")
	@Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "El campo direccion solo puede contener letras y numeros")
	private String provDireccion;
	
	@NotBlank(message = "El campo telefono es obligatorio")
	@NotNull(message = "El campo telefono es obligatorio")
	@Size(min = 7, max = 10, message = "El campo telefono debe tener entre 7 y 10 caracteres")
	@Pattern(regexp = "^[0-9]+$", message = "El campo telefono solo puede contener numeros")
	private String provTelefono;
	
	@NotBlank(message = "El campo email es obligatorio")
	@NotNull(message = "El campo email es obligatorio")
	@Size(min = 3, max = 50, message = "El campo email debe tener entre 3 y 50 caracteres")
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "El campo email no es valido")
	private String provEmail;
	
	@NotBlank(message = "El campo pagina web es obligatorio")
	@NotNull(message = "El campo pagina web es obligatorio")
	@Size(min = 3, max = 50, message = "El campo pagina web debe tener entre 3 y 50 caracteres")
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+\\.[a-zA-Z]{2,}$", message = "El campo pagina web no es valido")
	private String provPagWeb;
	
	private String estadoNombre;
	
	@NotNull(message = "El campo estado es obligatorio")
	@Pattern(regexp = "^[0-9]+$", message = "El campo estado solo puede contener numeros")
	private long estadoId;
	
	@NotNull(message = "El campo barId es obligatorio")
	@Pattern(regexp = "^[0-9]+$", message = "El campo barId solo puede contener numeros")
	private long barId;
	
	private String barNombre;
	
	public ProveedorDTO() {
		super();
	}
	
	public ProveedorDTO(long provId, String provRazonSocial, String provRuc, String provDireccion,
			String provTelefono, String provEmail, String provPagWeb, String estadoNombre, long estadoId) {
		super();
		this.provId = provId;
		this.provRazonSocial = provRazonSocial;
		this.provRuc = provRuc;
		this.provDireccion = provDireccion;
		this.provTelefono = provTelefono;
		this.provEmail = provEmail;
		this.provPagWeb = provPagWeb;
		this.estadoNombre = estadoNombre;
		this.estadoId = estadoId;
	}
	
	public long getProvId() {
		return provId;
	}
	
	public void setProvId(long provId) {
		this.provId = provId;
	}
	
	public String getProvRazonSocial() {
		return provRazonSocial;
	}
	
	public void setProvRazonSocial(String provRazonSocial) {
		this.provRazonSocial = provRazonSocial;
	}
	
	public String getProvRuc() {
		return provRuc;
	}
	
	public void setProvRuc(String provRuc) {
		this.provRuc = provRuc;
	}
	
	public String getProvDireccion() {
		return provDireccion;
	}
	
	public void setProvDireccion(String provDireccion) {
		this.provDireccion = provDireccion;
	}
	
	public String getProvTelefono() {
		return provTelefono;
	}
	
	public void setProvTelefono(String provTelefono) {
		this.provTelefono = provTelefono;
	}
	
	public String getProvEmail() {
		return provEmail;
	}
	
	public void setProvEmail(String provEmail) {
		this.provEmail = provEmail;
	}
	
	public String getProvPagWeb() {
		return provPagWeb;
	}
	
	public void setProvPagWeb(String provPagWeb) {
		this.provPagWeb = provPagWeb;
	}
	
	public long getEstadoId() {
		return estadoId;
	}
	
	public void setEstadoId(long estadoId) {
		this.estadoId = estadoId;
	}
	
	public String getEstadoNombre() {
		return estadoNombre;
	}
	
	public void setEstadoNombre(String estadoNombre) {
		this.estadoNombre = estadoNombre;
	}
	
	public long getBarId() {
		return barId;
	}

	public void setBarId(long barId) {
		this.barId = barId;
	}
	
	public String getBarNombre() {
		return barNombre;
	}
	
	public void setBarNombre(String barNombre) {
		this.barNombre = barNombre;
	}
	
	
	
}
