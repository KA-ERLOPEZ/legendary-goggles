package com.sistema.pizzeria.elpirata.api.web.dto;

import java.util.Date;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PersonaDTO {

	private long perId;

	@NotNull(message = "El Id es requerido")
	@NotBlank(message = "El Id no puede estar vacio")
	private long barId;
	
	@NotNull(message = "El nombre del bar es requerido")
	@NotBlank(message = "El nombre del bar no puede estar vacio")
	@Size(max = 50, message = "El nombre del bar no puede tener mas de 50 caracteres")
	@Size(min = 4, message = "El nombre del bar no puede tener menos de 4 caracteres")
	private String barNombre;

	private NacionalidadDTO nacionalidad;

	@NotNull(message = "El apellido es requerido")
	@NotBlank(message = "El apellido no puede estar vacio")
	@Size(max = 50, message = "El apellido no puede tener mas de 50 caracteres")
	@Size(min = 4, message = "El apellido no puede tener menos de 4 caracteres")
	private String perApellido;
	
	@NotNull(message = "La direccion es requerida")
	@NotBlank(message = "La direccion no puede estar vacia")
	@Size(max = 50, message = "La direccion no puede tener mas de 50 caracteres")
	@Size(min = 4, message = "La direccion no puede tener menos de 4 caracteres")
	private String perDireccion;

	@NotNull(message = "El email es requerido")
	@NotBlank(message = "El email no puede estar vacio")
	@Size(max = 50, message = "El email no puede tener mas de 50 caracteres")
	@Size(min = 4, message = "El email no puede tener menos de 4 caracteres")
	@Email(message = "El email no es valido")
	private String perEmail;
	
	private boolean perEnabled;

	@NotNull(message = "La fecha de nacimiento es requerida")
	@NotBlank(message = "La fecha de nacimiento no puede estar vacia")
	private Date perFecNac;

	@NotNull(message = "El nombre es requerido")
	@NotBlank(message = "El nombre no puede estar vacio")
	@Size(max = 50, message = "El nombre no puede tener mas de 50 caracteres")
	@Size(min = 4, message = "El nombre no puede tener menos de 4 caracteres")
	private String perNombre;
	
	@NotNull(message = "El numero de documento es requerido")
	@NotBlank(message = "El numero de documento no puede estar vacio")
	@Size(max = 50, message = "El numero de documento no puede tener mas de 50 caracteres")
	@Size(min = 4, message = "El numero de documento no puede tener menos de 4 caracteres")	
	private String perNroCedula;

	@NotNull(message = "El numero de RUC es requerido")
	@NotBlank(message = "El numero de RUC no puede estar vacio")
	@Size(max = 50, message = "El numero de RUC no puede tener mas de 50 caracteres")
	@Size(min = 4, message = "El numero de RUC no puede tener menos de 4 caracteres")
	private String perNroRuc;

	@NotNull(message = "El telefono es requerido")
	@NotBlank(message = "El telefono no puede estar vacio")
	@Size(max = 50, message = "El telefono no puede tener mas de 50 caracteres")	
	private String perTelefono;
	
	public PersonaDTO() {

	}
	
	public PersonaDTO(long perId, long barId, String barNombre, NacionalidadDTO nacionalidad, String perApellido,
			String perDireccion, String perEmail, boolean perEnabled, Date perFecNac, String perNombre,
			String perNroCedula, String perNroRuc, String perTelefono) {
		this.perId = perId;
		this.barId = barId;
		this.barNombre = barNombre;
		this.nacionalidad = nacionalidad;
		this.perApellido = perApellido;
		this.perDireccion = perDireccion;
		this.perEmail = perEmail;
		this.perEnabled = perEnabled;
		this.perFecNac = perFecNac;
		this.perNombre = perNombre;
		this.perNroCedula = perNroCedula;
		this.perNroRuc = perNroRuc;
		this.perTelefono = perTelefono;
	}

	public long getPerId() {
		return perId;
	}

	public void setPerId(long perId) {
		this.perId = perId;
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
	
	public NacionalidadDTO getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(NacionalidadDTO nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getPerApellido() {
		return perApellido;
	}

	public void setPerApellido(String perApellido) {
		this.perApellido = perApellido;
	}

	public String getPerDireccion() {
		return perDireccion;
	}

	public void setPerDireccion(String perDireccion) {
		this.perDireccion = perDireccion;
	}

	public String getPerEmail() {
		return perEmail;
	}

	public void setPerEmail(String perEmail) {
		this.perEmail = perEmail;
	}

	public boolean isPerEnabled() {
		return perEnabled;
	}

	public void setPerEnabled(boolean perEnabled) {
		this.perEnabled = perEnabled;
	}

	public Date getPerFecNac() {
		return perFecNac;
	}

	public void setPerFecNac(Date perFecNac) {
		this.perFecNac = perFecNac;
	}

	public String getPerNombre() {
		return perNombre;
	}

	public void setPerNombre(String perNombre) {
		this.perNombre = perNombre;
	}

	public String getPerNroCedula() {
		return perNroCedula;
	}

	public void setPerNroCedula(String perNroCedula) {
		this.perNroCedula = perNroCedula;
	}

	public String getPerNroRuc() {
		return perNroRuc;
	}

	public void setPerNroRuc(String perNroRuc) {
		this.perNroRuc = perNroRuc;
	}

	public String getPerTelefono() {
		return perTelefono;
	}

	public void setPerTelefono(String perTelefono) {
		this.perTelefono = perTelefono;
	}
	
	
	
	
}
