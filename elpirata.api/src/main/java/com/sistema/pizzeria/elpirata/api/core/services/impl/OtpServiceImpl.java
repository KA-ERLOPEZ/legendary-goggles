package com.sistema.pizzeria.elpirata.api.core.services.impl;

import java.time.Duration;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.bastiaanjansen.otp.HMACAlgorithm;
import com.bastiaanjansen.otp.TOTPGenerator;
import com.sistema.pizzeria.elpirata.api.core.services.OtpService;

@Service
public class OtpServiceImpl implements OtpService {

	private final RedisTemplate<String, String> redisTemplate;
	
	public OtpServiceImpl(RedisTemplate<String, String> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	
	/*
	 * Implementación de los métodos de la interfaz OtpService para la generación,
	 * validación y eliminación de códigos OTP (One-Time Password) en la aplicación.
	 * Estos códigos son utilizados para la autenticación de usuarios y la
	 * verificación de identidad en diferentes procesos. La implementación de estos
	 * métodos puede variar dependiendo
	 */
	@Override
	public String generateOtp(String key, String f2aSecret) {
		
		TOTPGenerator totp = new TOTPGenerator.Builder(f2aSecret.getBytes()).withHOTPGenerator(builder -> {
			builder.withPasswordLength(6);
			builder.withAlgorithm(HMACAlgorithm.SHA256);
			
		}).withPeriod(Duration.ofMinutes(5)).build();
		
		String otpStr = totp.now();
		String keyOtp = getRedisKey(key);
		redisTemplate.opsForValue().set(keyOtp, otpStr, Duration.ofMinutes(5));
		return otpStr;

	}

	@Override
	public boolean validateOtp(String key, String otp) {
		
		String keyOtp = getRedisKey(key);
		String otpStored = redisTemplate.opsForValue().get(keyOtp);
		if (otpStored != null && otpStored.equals(otp)) {
			redisTemplate.delete(keyOtp);
			return true;
		}
		
		return false;
	}

	@Override
	public void clearOtp(String key) {
		// TODO Auto-generated method stub
		String keyOtp = getRedisKey(key);
		redisTemplate.delete(keyOtp);

	}
	
	private String getRedisKey(String key) {
		return "OTP:" + key;
	}

}
