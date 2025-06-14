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


/**
 * The persistent class for the usuario_logins database table.
 * 
 */
@Entity
@Table(name="usuario_logins")
@NamedQuery(name="UsuarioLogin.findAll", query="SELECT u FROM UsuarioLogin u")
public class UsuarioLogin implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="login_id")
	private long loginId;

	@Column(name="login_device", length=100, nullable=false)
	private String loginDevice;

	@Column(name="login_failure_reason", length=100, nullable=false)
	private String loginFailureReason;

	@Column(name="login_ip", length=100, nullable=false)
	private String loginIp;

	@Column(name="login_success", nullable=false, columnDefinition="TINYINT(1) CHECK(login_success IN (0, 1)) DEFAULT 0")
	private boolean loginSuccess;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="login_timestamp" , nullable=false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date loginTimestamp;

	//uni-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="usu_id", nullable=false, referencedColumnName = "usu_id")
	private Usuario usuario;

	public UsuarioLogin() {
	}

	public long getLoginId() {
		return this.loginId;
	}

	public void setLoginId(long loginId) {
		this.loginId = loginId;
	}

	public String getLoginDevice() {
		return this.loginDevice;
	}

	public void setLoginDevice(String loginDevice) {
		this.loginDevice = loginDevice;
	}

	public String getLoginFailureReason() {
		return this.loginFailureReason;
	}

	public void setLoginFailureReason(String loginFailureReason) {
		this.loginFailureReason = loginFailureReason;
	}

	public String getLoginIp() {
		return this.loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public boolean isLoginSuccess() {
		return this.loginSuccess;
	}

	public void setLoginSuccess(boolean loginSuccess) {
		this.loginSuccess = loginSuccess;
	}

	public Date getLoginTimestamp() {
		return this.loginTimestamp;
	}

	public void setLoginTimestamp(Date loginTimestamp) {
		this.loginTimestamp = loginTimestamp;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}