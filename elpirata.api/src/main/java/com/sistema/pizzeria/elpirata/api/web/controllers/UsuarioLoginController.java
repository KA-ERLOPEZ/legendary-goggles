package com.sistema.pizzeria.elpirata.api.web.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.pizzeria.elpirata.api.core.services.UsuarioLoginService;
import com.sistema.pizzeria.elpirata.api.web.dto.UsuarioLoginDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuario-logins")
public class UsuarioLoginController {

	private final UsuarioLoginService usuarioLoginService;

	public UsuarioLoginController(UsuarioLoginService usuarioLoginService) {
		this.usuarioLoginService = usuarioLoginService;
	}

	/**
	 * Obtener todos los logins.
	 * 
	 * @return Lista de logins.
	 */
	@Operation(summary = "Listar todos los logins", description = "Devuelve una lista de logins.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Lista de logins obtenida exitosamente."),
			@ApiResponse(responseCode = "404", description = "No se encontraron logins.") })
	@GetMapping
	public ResponseEntity<List<UsuarioLoginDTO>> getAllLogins() {
		List<UsuarioLoginDTO> logins = usuarioLoginService.findAll();
		return ResponseEntity.ok(logins);
	}

	// Obtener un login por ID
	@Operation(summary = "Obtener un login por ID", description = "Devuelve los detalles de un login específico.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Login encontrado."),
			@ApiResponse(responseCode = "404", description = "Login no encontrado.") })
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioLoginDTO> getLoginById(@PathVariable long id) {
		UsuarioLoginDTO login = usuarioLoginService.findById(id);
		return ResponseEntity.ok(login);
	}

	/**
	 * Crear un nuevo login.
	 * 
	 * @param loginDTO Datos del login a crear.
	 * @return Login creado.
	 */
	@Operation(summary = "Crear un nuevo login", description = "Crea un nuevo login y devuelve los detalles del login creado.")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Login creado exitosamente."),
			@ApiResponse(responseCode = "400", description = "Error al crear el login.") })
	@PostMapping("/login")
	public ResponseEntity<UsuarioLoginDTO> createLogin(@RequestBody @Valid UsuarioLoginDTO loginDTO) {
		UsuarioLoginDTO savedLogin = usuarioLoginService.save(loginDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedLogin);
	}

	/**
	 * Actualizar un login existente.
	 * 
	 * @param id       ID del login a actualizar.
	 * @param loginDTO Datos del login a actualizar.
	 * @return Login actualizado.
	 */
	@Operation(summary = "Actualizar un login", description = "Actualiza un login existente y devuelve los detalles del login actualizado.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Login actualizado exitosamente."),
			@ApiResponse(responseCode = "404", description = "Login no encontrado."),
			@ApiResponse(responseCode = "400", description = "Error al actualizar el login.") })
	@PutMapping("/{id}")
	public ResponseEntity<UsuarioLoginDTO> updateLogin(@PathVariable Integer id,
			@RequestBody @Valid UsuarioLoginDTO loginDTO) {
		loginDTO.setLoginId(id); // Asegura que el ID proporcionado en la ruta se use en el DTO
		UsuarioLoginDTO updatedLogin = usuarioLoginService.update(loginDTO);
		return ResponseEntity.ok(updatedLogin);
	}

	/**
	 * Eliminar un login por ID.
	 * 
	 * @param id ID del login a eliminar.
	 * @return Respuesta vacía.
	 */
	@Operation(summary = "Eliminar un login por ID", description = "Elimina un login existente.")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Login eliminado exitosamente."),
			@ApiResponse(responseCode = "404", description = "Login no encontrado.") })
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteLoginById(@PathVariable long id) {
		usuarioLoginService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	/**
	 * Obtener todos los logins exitosos.
	 * 
	 * @return Lista de logins exitosos.
	 */
	@GetMapping("/successful")
	public ResponseEntity<List<UsuarioLoginDTO>> getSuccessfulLogins() {
		List<UsuarioLoginDTO> successfulLogins = usuarioLoginService.findSuccessfulLogins();
		return ResponseEntity.ok(successfulLogins);
	}

	/**
	 * Obtener todos los logins fallidos.
	 * 
	 * @return Lista de logins fallidos.
	 */
	@GetMapping("/failed")
	public ResponseEntity<List<UsuarioLoginDTO>> getFailedLogins() {
		List<UsuarioLoginDTO> failedLogins = usuarioLoginService.findFailedLogins();
		return ResponseEntity.ok(failedLogins);
	}

	/**
	 * Obtener todos los logins por IP.
	 * 
	 * @param ip Dirección IP.
	 * @return Lista de logins por IP.
	 */
	@Operation(summary = "Obtener logins por IP", description = "Devuelve una lista de logins filtrados por dirección IP.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de logins por IP obtenida exitosamente."),
			@ApiResponse(responseCode = "404", description = "No se encontraron logins por IP.") })
	@GetMapping("/by-ip")
	public ResponseEntity<List<UsuarioLoginDTO>> getLoginsByIp(@RequestParam String ip) {
		List<UsuarioLoginDTO> logins = usuarioLoginService.findByIp(ip);
		return ResponseEntity.ok(logins);
	}

	/**
	 * Obtener todos los logins por rango de fechas.
	 * 
	 * @param startDate Fecha de inicio.
	 * @param endDate   Fecha de fin.
	 * @return Lista de logins por rango de fechas.
	 */
	@Operation(summary = "Obtener logins por rango de fechas", description = "Devuelve una lista de logins filtrados por rango de fechas.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de logins por rango de fechas obtenida exitosamente."),
			@ApiResponse(responseCode = "404", description = "No se encontraron logins por rango de fechas.") })
	@GetMapping("/by-date-range")
	public ResponseEntity<List<UsuarioLoginDTO>> getLoginsByDateRange(
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date startDate,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date endDate) {
		List<UsuarioLoginDTO> logins = usuarioLoginService.findByTimestampBetween(startDate, endDate);
		return ResponseEntity.ok(logins);
	}
}
