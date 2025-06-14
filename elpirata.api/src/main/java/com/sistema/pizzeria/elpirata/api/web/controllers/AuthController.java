package com.sistema.pizzeria.elpirata.api.web.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.pizzeria.elpirata.api.core.config.jwt.JWTProvider;
import com.sistema.pizzeria.elpirata.api.core.config.jwt.TokenType;
import com.sistema.pizzeria.elpirata.api.core.services.EmailService;
import com.sistema.pizzeria.elpirata.api.core.services.OtpService;
import com.sistema.pizzeria.elpirata.api.core.services.UsuarioLoginService;
import com.sistema.pizzeria.elpirata.api.core.services.UsuarioService;
import com.sistema.pizzeria.elpirata.api.web.dto.AuthResponse;
import com.sistema.pizzeria.elpirata.api.web.dto.LoginRequest;
import com.sistema.pizzeria.elpirata.api.web.dto.RefreshTokenRequest;
import com.sistema.pizzeria.elpirata.api.web.dto.UsuarioClienteDTO;
import com.sistema.pizzeria.elpirata.api.web.dto.UsuarioLoginDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;


@CrossOrigin()
@RestController
@RequestMapping("/api/auth")
@Tag(name = "Autenticación", description = "Endpoints relacionados con la autenticación y el manejo de tokens")
public class AuthController {

    private final JWTProvider jwtProvider;
    private final AuthenticationManager authenticationManager;
    private final UsuarioLoginService usuarioLoginService;
    private final UsuarioService usuarioService;
    private final EmailService emailService;
    private final OtpService otp;

    public AuthController(JWTProvider jwtProvider, 
                          AuthenticationManager authenticationManager,
                          UsuarioLoginService usuarioLoginService,
                          UsuarioService usuarioService,
                          EmailService emailService,
                          OtpService otp) {
        this.jwtProvider = jwtProvider;
        this.authenticationManager = authenticationManager;
        this.usuarioLoginService = usuarioLoginService;
        this.usuarioService = usuarioService;
        this.emailService = emailService;
        this.otp = otp;
    }

    @Operation(
        summary = "Iniciar sesión",
        description = "Autentica a un usuario y genera un par de tokens (Access y Refresh).",
        responses = {
            @ApiResponse(responseCode = "200", description = "Inicio de sesión exitoso, retorna los tokens."),
            @ApiResponse(responseCode = "401", description = "Credenciales inválidas.")
        }
    )
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String accessToken = jwtProvider.generateAccessToken(userDetails);
            String refreshToken = jwtProvider.generateRefreshToken(userDetails.getUsername());

            String ipAddress = request.getHeader("X-Forwarded-For") != null
                    ? request.getHeader("X-Forwarded-For").split(",")[0]
                    : request.getRemoteAddr();

            logSuccessfulLogin(ipAddress, request.getHeader("User-Agent"), userDetails.getUsername());

            return ResponseEntity.ok(new AuthResponse(accessToken, refreshToken));
        } catch (Exception ex) {
            logFailedLogin(request.getRemoteAddr(), request.getHeader("User-Agent"), loginRequest.getUsername(),
                    ex.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
    }
    
	@Operation(summary = "Registrar usuario", description = "Registra un nuevo usuario en el sistema.", responses = {
			@ApiResponse(responseCode = "200", description = "Usuario registrado exitosamente."),
			@ApiResponse(responseCode = "400", description = "Datos inválidos.") })
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody UsuarioClienteDTO user) {
		
		UsuarioClienteDTO existingUser = usuarioService.registerClient(user);
		
		// Verifica si el usuTwoFactorEnabled es verdadero
		if (user.isUsuTwoFactorEnabled() && Objects.nonNull(existingUser)) {
			// Genera un código OTP y lo envía al correo electrónico del usuario
			String otpCode = otp.generateOtp(user.getUsuUsername(), user.getUsuTwoFactorSecret());
			emailService.sendSimpleMessage(user.getPersona().getPerEmail(), user.getUsuUsername(), otpCode);
		}
		return ResponseEntity.ok(existingUser);
	}

    @Operation(
        summary = "Refrescar token",
        description = "Genera un nuevo token de acceso a partir de un token de actualización válido.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Nuevo token de acceso generado."),
            @ApiResponse(responseCode = "401", description = "Token de actualización inválido o expirado.")
        }
    )
    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest request) {
        String oldRefreshToken = request.getRefreshToken();

        // Validar el Refresh Token antiguo
        if (!jwtProvider.validateToken(oldRefreshToken, TokenType.REFRESH)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Refresh token inválido o expirado");
        }

        // Extraer el usuario del Refresh Token
        String username = jwtProvider.getUserFromRefreshToken(oldRefreshToken);
        if (username == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Refresh token inválido");
        }

        // Generar nuevos tokens
        String newAccessToken = jwtProvider.generateAccessToken(new User(username, "", new ArrayList<>()));
        String newRefreshToken = jwtProvider.generateRefreshToken(username);

        // (Opcional) Registra el nuevo Refresh Token en la base de datos para invalidar el anterior
       // usuarioService.updateRefreshToken(username, newRefreshToken);

        // Devuelve ambos tokens
        return ResponseEntity.ok(new AuthResponse(newAccessToken, newRefreshToken));
    }


    /**
     * Registra un intento de inicio de sesión exitoso.
     * 
     * @param ip       Dirección IP del cliente.
     * @param device   Dispositivo del cliente.
     * @param username Usuario autenticado.
     */
    private void logSuccessfulLogin(String ip, String device, String username) {
        UsuarioClienteDTO usuario = usuarioService.findByUsername(username);
        UsuarioLoginDTO login = new UsuarioLoginDTO();
        login.setLoginIp(ip);
        login.setLoginDevice(device);
        login.setLoginSuccess(true); // Exitoso
        login.setLoginTimestamp(new Date());
        login.setUsuarioId(usuario.getUsuId());

        usuarioLoginService.save(login);
    }

    /**
     * Registra un intento de inicio de sesión fallido.
     *
     * @param ip            Dirección IP del cliente.
     * @param device        Dispositivo del cliente.
     * @param username      Nombre de usuario intentado.
     * @param failureReason Razón del fallo.
     */
    private void logFailedLogin(String ip, String device, String username, String failureReason) {
        UsuarioClienteDTO usuario = usuarioService.findByUsername(username);

        UsuarioLoginDTO login = new UsuarioLoginDTO();
        login.setLoginIp(ip);
        login.setLoginDevice(device);
        login.setLoginSuccess(false); // Fallido
        login.setLoginFailureReason(failureReason);
        login.setLoginTimestamp(new Date());

        if (Objects.nonNull(usuario)) {
            login.setUsuarioId(usuario.getUsuId());
        }

        usuarioLoginService.save(login);
    }
}

