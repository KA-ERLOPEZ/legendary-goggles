package com.sistema.pizzeria.elpirata.api.core.services;

public interface OtpService {

	/**
	 * Genera un OTP (One Time Password) para el usuario.
	 * 
	 * @param key       Clave única del usuario.
	 * @param f2aSecret Secreto de dos factores del usuario.
	 * @return OTP generado.
	 */
	String generateOtp(String key, String f2aSecret);

	/**
	 * Valida un OTP (One Time Password) para el usuario.
	 * @param key
	 * @param otp
	 * @return
	 */
	boolean validateOtp(String key, String otp);

	/**
	 * Elimina el OTP (One Time Password) del usuario.
	 * 
	 * @param key
	 */
	void clearOtp(String key);
}
