package com.sistema.pizzeria.elpirata.api.core.entities;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * The persistent class for the usuario_sesiones database table.
 * 
 */
@Entity
@Table(name = "usuario_sesiones")
@NamedQuery(name = "UsuarioSesion.findAll", query = "SELECT u FROM UsuarioSesion u")
public class UsuarioSesion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "session_id")
	private long sessionId;

	@Column(name = "session_active", nullable = false)
	private boolean sessionActive;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "session_start", nullable = false)
	private Date sessionStart;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "session_end")
	private Date sessionEnd;

	@Column(name = "session_token", nullable = false, unique = true, length = 512)
	@NotNull
	@Size(max = 512)
	private String sessionToken;

	@Column(name = "session_ip", length = 45)
	@Size(max = 45)
	private String sessionIp;

	@Column(name = "session_device", length = 255)
	@Size(max = 255)
	private String sessionDevice;

	@Column(name = "session_type", length = 50)
	@Size(max = 50)
	private String sessionType;

	@Column(name = "session_location", length = 255)
	@Size(max = 255)
	private String sessionLocation;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "session_last_activity")
	private Date sessionLastActivity;
	

	@ManyToOne
	@JoinColumn(name = "usu_id", nullable = false)
	@NotNull
	private Usuario usuario;

	public UsuarioSesion() {
	}

	// Getters y Setters
	public long getSessionId() {
		return this.sessionId;
	}

	public void setSessionId(long sessionId) {
		this.sessionId = sessionId;
	}

	public boolean isSessionActive() {
		return this.sessionActive;
	}

	public void setSessionActive(boolean sessionActive) {
		this.sessionActive = sessionActive;
	}

	public Date getSessionEnd() {
		return this.sessionEnd;
	}

	public void setSessionEnd(Date sessionEnd) {
		this.sessionEnd = sessionEnd;
	}

	public Date getSessionStart() {
		return this.sessionStart;
	}

	public void setSessionStart(Date sessionStart) {
		this.sessionStart = sessionStart;
	}

	public String getSessionToken() {
		return this.sessionToken;
	}

	public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
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

	public Date getSessionLastActivity() {
		return sessionLastActivity;
	}

	public void setSessionLastActivity(Date sessionLastActivity) {
		this.sessionLastActivity = sessionLastActivity;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
