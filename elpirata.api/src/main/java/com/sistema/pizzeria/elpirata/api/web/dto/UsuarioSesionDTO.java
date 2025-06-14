package com.sistema.pizzeria.elpirata.api.web.dto;

import java.util.Date;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

public class UsuarioSesionDTO {

	
	private long sessionId;

	@NotNull
	private boolean sessionActive;

	@NotNull(message = "La fecha de inicio de sesión no puede ser nula")
	@PastOrPresent(message = "La fecha de inicio de sesión debe ser igual o anterior a la fecha actual")
	private Date sessionStart;
	
	@FutureOrPresent(message = "La fecha de fin de sesión debe ser igual o posterior a la fecha actual")
	private Date sessionEnd;
	
	private String sessionIp;

	private String sessionDevice;

	private String sessionType;

	private String sessionLocation;
	
	private boolean sessionExpired;

	@NotNull
	@Size(max = 512)
	private String sessionToken;

	private Date sessionLastActivity;

	@NotNull
	private UsuarioAdminDTO usuario;

	public UsuarioSesionDTO(@NotNull long sessionId, @NotNull boolean sessionActive, @NotNull Date sessionStart,
			Date sessionEnd, String sessionIp, String sessionDevice, String sessionType, String sessionLocation,
			@NotNull @Size(max = 512) String sessionToken, Date sessionLastActivity, @NotNull UsuarioAdminDTO usuario) {
		super();
		this.sessionId = sessionId;
		this.sessionActive = sessionActive;
		this.sessionStart = sessionStart;
		this.sessionEnd = sessionEnd;
		this.sessionIp = sessionIp;
		this.sessionDevice = sessionDevice;
		this.sessionType = sessionType;
		this.sessionLocation = sessionLocation;
		this.sessionToken = sessionToken;
		this.sessionLastActivity = sessionLastActivity;
		this.usuario = usuario;
	}

	public Date getSessionLastActivity() {
		return sessionLastActivity;
	}

	public void setSessionLastActivity(Date sessionLastActivity) {
		this.sessionLastActivity = sessionLastActivity;
	}

	public String getSessionIp() {
		return sessionIp;
	}

	public void setSessionIp(String sessionIp) {
		this.sessionIp = sessionIp;
	}

	public String getSessionDevice() {
		return sessionDevice;
	}

	public void setSessionDevice(String sessionDevice) {
		this.sessionDevice = sessionDevice;
	}

	public String getSessionType() {
		return sessionType;
	}

	public void setSessionType(String sessionType) {
		this.sessionType = sessionType;
	}

	public String getSessionLocation() {
		return sessionLocation;
	}

	public void setSessionLocation(String sessionLocation) {
		this.sessionLocation = sessionLocation;
	}

	public long getSessionId() {
		return sessionId;
	}

	public void setSessionId(long sessionId) {
		this.sessionId = sessionId;
	}

	public boolean isSessionActive() {
		return sessionActive;
	}

	public void setSessionActive(boolean sessionActive) {
		this.sessionActive = sessionActive;
	}

	public Date getSessionStart() {
		return sessionStart;
	}

	public void setSessionStart(Date sessionStart) {
		this.sessionStart = sessionStart;
	}

	public Date getSessionEnd() {
		return sessionEnd;
	}

	public void setSessionEnd(Date sessionEnd) {
		this.sessionEnd = sessionEnd;
	}

	public String getSessionToken() {
		return sessionToken;
	}

	public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}

	public UsuarioAdminDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioAdminDTO usuario) {
		this.usuario = usuario;
	}
	
	public boolean isSessionExpired() {
		return sessionExpired;
	}
}
