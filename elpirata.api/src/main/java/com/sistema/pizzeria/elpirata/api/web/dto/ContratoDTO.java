package com.sistema.pizzeria.elpirata.api.web.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ContratoDTO {
	
	    private long id;	
	    
	    @NotNull(message = "La persona no puede ser nula")
	    PersonaDTO persona;
	    
	    @NotNull(message = "El tipo de documento no puede ser nulo")
	    TipoDocumentoDTO tipoDocumento;
	    
	    @NotNull(message = "El cargo no puede ser nulo")
	    private long cargoId;
	    
	    @NotBlank(message = "El cargo no puede estar vacío")
	    @NotNull(message = "El cargo no puede ser nulo")
	    @Size(min = 3, max = 50, message = "El cargo debe tener entre 3 y 50 caracteres")
	    private String cargoNombre;
	    
	    @NotNull(message = "El estado no puede ser nulo")
	    private long estadoId;
	    
	    private String estadoNombre;
	    
	    
	    @NotNull(message = "El usuario de creación no puede ser nulo")
	    private long usuarioCreacionId;
	    
	    private String usuarioCreacionUsername;
	    
	    @NotNull(message = "El usuario de modificación no puede ser nulo")
	    private long usuarioModificacionId;
	    
	    private String usuarioModificacionUsername;
	    
	    @NotNull(message = "La fecha de inicio no puede ser nula")
	    @NotBlank(message = "La fecha de inicio no puede estar vacía")
	    @PastOrPresent(message = "La fecha de inicio debe ser en el pasado o presente")
	    private LocalDate contFecInicio;
	    
	    @NotNull(message = "La fecha de fin no puede ser nula")
	    @NotBlank(message = "La fecha de fin no puede estar vacía")
	    @PastOrPresent(message = "La fecha de fin debe ser en el pasado")
	    private LocalDate contFecFin;
	    
	    @NotNull(message = "El salario no puede ser nulo")
	    @NotBlank(message = "El salario no puede estar vacío")
	    @Pattern(regexp = "^\\d+(\\.\\d{1,2})?$", message = "El salario debe ser un número válido con hasta 2 decimales")
	    private BigDecimal contSalario;

		public ContratoDTO() {
		}
		
		public ContratoDTO(long id, PersonaDTO persona, TipoDocumentoDTO tipoDocumento, long cargoId, String cargoNombre,
				long estadoId, String estadoNombre, long usuarioCreacionId, String usuarioCreacionUsername,
				long usuarioModificacionId, String usuarioModificacionUsername, LocalDate contFecInicio,
				LocalDate contFecFin, BigDecimal contSalario) {
			this.id = id;
			this.persona = persona;
			this.tipoDocumento = tipoDocumento;
			this.cargoId = cargoId;
			this.cargoNombre = cargoNombre;
			this.estadoId = estadoId;
			this.estadoNombre = estadoNombre;
			this.usuarioCreacionId = usuarioCreacionId;
			this.usuarioCreacionUsername = usuarioCreacionUsername;
			this.usuarioModificacionId = usuarioModificacionId;
			this.usuarioModificacionUsername = usuarioModificacionUsername;
			this.contFecInicio = contFecInicio;
			this.contFecFin = contFecFin;
			this.contSalario = contSalario;
		}

		public PersonaDTO getPersona() {
			return persona;
		}

		public void setId(long id) {
			this.id = id;
		}

		public long getId() {
			return id;
		}
		
		public void setPersona(PersonaDTO persona) {
			this.persona = persona;
		}

		public TipoDocumentoDTO getTipoDocumento() {
			return tipoDocumento;
		}

		public void setTipoDocumento(TipoDocumentoDTO tipoDocumento) {
			this.tipoDocumento = tipoDocumento;
		}

		public long getCargoId() {
			return cargoId;
		}

		public void setCargoId(long cargoId) {
			this.cargoId = cargoId;
		}

		public String getCargoNombre() {
			return cargoNombre;
		}

		public void setCargoNombre(String cargoNombre) {
			this.cargoNombre = cargoNombre;
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

		public long getUsuarioCreacionId() {
			return usuarioCreacionId;
		}

		public void setUsuarioCreacionId(long usuarioCreacionId) {
			this.usuarioCreacionId = usuarioCreacionId;
		}

		public String getUsuarioCreacionUsername() {
			return usuarioCreacionUsername;
		}

		public void setUsuarioCreacionUsername(String usuarioCreacionUsername) {
			this.usuarioCreacionUsername = usuarioCreacionUsername;
		}

		public long getUsuarioModificacionId() {
			return usuarioModificacionId;
		}

		public void setUsuarioModificacionId(long usuarioModificacionId) {
			this.usuarioModificacionId = usuarioModificacionId;
		}

		public String getUsuarioModificacionUsername() {
			return usuarioModificacionUsername;
		}

		public void setUsuarioModificacionUsername(String usuarioModificacionUsername) {
			this.usuarioModificacionUsername = usuarioModificacionUsername;
		}

		public LocalDate getContFecInicio() {
			return contFecInicio;
		}

		public void setContFecInicio(LocalDate contFecInicio) {
			this.contFecInicio = contFecInicio;
		}

		public LocalDate getContFecFin() {
			return contFecFin;
		}

		public void setContFecFin(LocalDate contFecFin) {
			this.contFecFin = contFecFin;
		}

		public BigDecimal getContSalario() {
			return contSalario;
		}

		public void setContSalario(BigDecimal contSalario) {
			this.contSalario = contSalario;
		}
		
		
		

}
