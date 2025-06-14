package com.sistema.pizzeria.elpirata.api.web.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.pizzeria.elpirata.api.core.services.EmailService;
import com.sistema.pizzeria.elpirata.api.core.services.OtpService;
import com.sistema.pizzeria.elpirata.api.web.dto.OtpRequestDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@CrossOrigin()
@RestController
@RequestMapping("/api/v1/otp")
public class OTPController {

	private final EmailService emailService;
	private final OtpService otpService;
	
	public OTPController(EmailService emailService, OtpService otpService) {
		this.emailService = emailService;
		this.otpService = otpService;
	}
	
	/**
	 * Método para enviar un OTP (One Time Password) al correo electrónico del
	 * usuario.
	 * 
	 * @param otpRequest Objeto que contiene la información necesaria para enviar el
	 *                   OTP.
	 * @return ResponseEntity con el mensaje de éxito.
	 */
	@Operation(summary = "Enviar OTP", description = "Envía un OTP al correo electrónico del usuario.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OTP enviado exitosamente"),
			@ApiResponse(responseCode = "400", description = "Error al enviar el OTP"),
	})
	@RequestMapping("/send")
	public ResponseEntity<?> sendOtp(@RequestBody OtpRequestDTO otpRequest) {
		String otp =otpService.generateOtp(otpRequest.getUsername(), otpRequest.getF2aSecret());
		emailService.sendSimpleMessage(otpRequest.getEmail(), otpRequest.getUsername(), otp);
		return ResponseEntity.ok("OTP enviado exitosamente");
	}
	
	/**
	 * Método para validar un OTP (One Time Password) recibido por el usuario.
	 * 
	 * @param otpRequest Objeto que contiene la información necesaria para validar
	 *                   el OTP.
	 * @return ResponseEntity con el resultado de la validación.
	 */
	@Operation(summary = "Validar OTP", description = "Valida un OTP recibido por el usuario.")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OTP validado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error al validar el OTP"),
    })
	@RequestMapping("/validate")
	public ResponseEntity<?> validateOtp(@RequestBody OtpRequestDTO otpRequest) {
		boolean isValid = otpService.validateOtp(otpRequest.getUsername(), otpRequest.getF2aSecret());
		if (isValid) {
			return ResponseEntity.ok("OTP validado exitosamente");
		} else {
			return ResponseEntity.badRequest().body("OTP inválido o expirado");
		}
	}
}
