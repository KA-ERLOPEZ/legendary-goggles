package com.sistema.pizzeria.elpirata.api.web.dto;

import java.util.Date;

public class UsuarioLoginDTO {

	private long loginId;
    private String loginDevice;
    private String loginFailureReason;
    private String loginIp;
    private boolean loginSuccess;
    private Date loginTimestamp;
    private long usuarioId;
    private String usuarioUsername;

    // Constructor sin parámetros
    public UsuarioLoginDTO() {
    }

	public UsuarioLoginDTO(long loginId, String loginDevice, String loginFailureReason, String loginIp,
			boolean loginSuccess, Date loginTimestamp, long usuarioId, String usuarioUsername) {
		super();
		this.loginId = loginId;
		this.loginDevice = loginDevice;
		this.loginFailureReason = loginFailureReason;
		this.loginIp = loginIp;
		this.loginSuccess = loginSuccess;
		this.loginTimestamp = loginTimestamp;
		this.usuarioId = usuarioId;
		this.usuarioUsername = usuarioUsername;
	}

	public long getLoginId() {
		return loginId;
	}

	public void setLoginId(long loginId) {
		this.loginId = loginId;
	}

	public String getLoginDevice() {
		return loginDevice;
	}

	public void setLoginDevice(String loginDevice) {
		this.loginDevice = loginDevice;
	}

	public String getLoginFailureReason() {
		return loginFailureReason;
	}

	public void setLoginFailureReason(String loginFailureReason) {
		this.loginFailureReason = loginFailureReason;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public boolean isLoginSuccess() {
		return loginSuccess;
	}

	public void setLoginSuccess(boolean loginSuccess) {
		this.loginSuccess = loginSuccess;
	}

	public Date getLoginTimestamp() {
		return loginTimestamp;
	}

	public void setLoginTimestamp(Date loginTimestamp) {
		this.loginTimestamp = loginTimestamp;
	}

	public long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(long usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getUsuarioUsername() {
		return usuarioUsername;
	}

	public void setUsuarioUsername(String usuarioUsername) {
		this.usuarioUsername = usuarioUsername;
	}
    
    
}
