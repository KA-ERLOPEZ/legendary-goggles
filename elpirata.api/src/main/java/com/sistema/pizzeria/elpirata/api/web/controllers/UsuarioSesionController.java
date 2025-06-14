package com.sistema.pizzeria.elpirata.api.web.controllers;

import java.util.Date;
import java.util.List;

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

import com.sistema.pizzeria.elpirata.api.core.services.UsuarioSesionService;
import com.sistema.pizzeria.elpirata.api.web.dto.UsuarioSesionDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/sessions")
@Tag(name = "Sesiones de Usuario", description = "Gestión de sesiones de usuario.")
public class UsuarioSesionController {

	private final UsuarioSesionService usuarioSesionService;

	public UsuarioSesionController(UsuarioSesionService usuarioSesionService) {
		this.usuarioSesionService = usuarioSesionService;
	}

	@Operation(summary = "Obtener todas las sesiones de usuario", description = "Devuelve todas las sesiones de usuario en la base de datos.", responses = {
			@ApiResponse(responseCode = "200", description = "Sesiones obtenidas exitosamente."), })
	@GetMapping
	public ResponseEntity<List<UsuarioSesionDTO>> getAllSessions() {
		List<UsuarioSesionDTO> sesiones = usuarioSesionService.findAll();
		return ResponseEntity.ok(sesiones);
	}

	@Operation(summary = "Obtener sesiones de usuario paginadas", description = "Devuelve sesiones paginadas.", responses = {
			@ApiResponse(responseCode = "200", description = "Sesiones obtenidas exitosamente."), })
	@GetMapping("/paginated")
	public ResponseEntity<?> getAllSessionsPaginated(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		 return ResponseEntity.ok(usuarioSesionService.findAllPagination(page, size));
	}

	@Operation(summary = "Obtener una sesión por ID", description = "Devuelve la sesión correspondiente al ID especificado.", responses = {
			@ApiResponse(responseCode = "200", description = "Sesión obtenida exitosamente."),
			@ApiResponse(responseCode = "404", description = "Sesión no encontrada.", content = @Content) })
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioSesionDTO> getSessionById(@PathVariable long id) {
		UsuarioSesionDTO sesion = usuarioSesionService.findById(id);
		return ResponseEntity.ok(sesion);
	}

	@Operation(summary = "Crear una nueva sesión", description = "Crea y guarda una nueva sesión de usuario.", responses = {
			@ApiResponse(responseCode = "201", description = "Sesión creada exitosamente."), })
	@PostMapping
	public ResponseEntity<UsuarioSesionDTO> createSession(@Valid @RequestBody UsuarioSesionDTO dto) {
		UsuarioSesionDTO nuevaSesion = usuarioSesionService.save(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(nuevaSesion);
	}

	@Operation(summary = "Actualizar una sesión existente", description = "Actualiza los datos de una sesión existente.", responses = {
			@ApiResponse(responseCode = "200", description = "Sesión actualizada exitosamente."),
			@ApiResponse(responseCode = "404", description = "Sesión no encontrada.", content = @Content) })
	@PutMapping("/{id}")
	public ResponseEntity<UsuarioSesionDTO> updateSession(@PathVariable Integer id,
			@Valid @RequestBody UsuarioSesionDTO dto) {
		dto.setSessionId(id); // Asegura que el ID coincida
		UsuarioSesionDTO sesionActualizada = usuarioSesionService.update(dto);
		return ResponseEntity.ok(sesionActualizada);
	}

	@Operation(summary = "Eliminar una sesión por ID", description = "Elimina la sesión correspondiente al ID especificado.", responses = {
			@ApiResponse(responseCode = "204", description = "Sesión eliminada exitosamente."),
			@ApiResponse(responseCode = "404", description = "Sesión no encontrada.", content = @Content) })
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteSession(@PathVariable long id) {
		usuarioSesionService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@Operation(summary = "Finalizar una sesión", description = "Marca una sesión como finalizada.", responses = {
			@ApiResponse(responseCode = "200", description = "Sesión finalizada exitosamente."),
			@ApiResponse(responseCode = "404", description = "Sesión no encontrada.", content = @Content) })
	@PutMapping("/{id}/end")
	public ResponseEntity<UsuarioSesionDTO> endSession(@PathVariable long id) {
		UsuarioSesionDTO sesion = usuarioSesionService.findById(id);
		sesion.setSessionActive(false); // Finaliza la sesión
		sesion.setSessionEnd(new Date()); // Marca el tiempo de fin
		UsuarioSesionDTO sesionFinalizada = usuarioSesionService.update(sesion);
		return ResponseEntity.ok(sesionFinalizada);
	}

	@Operation(summary = "Obtener sesiones activas", description = "Devuelve todas las sesiones que están activas.", responses = {
			@ApiResponse(responseCode = "200", description = "Sesiones activas obtenidas exitosamente."), })
	@GetMapping("/active")
	public ResponseEntity<?> getActiveSessions(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		 
		return ResponseEntity.ok(usuarioSesionService.findActiveSessions(page, size));
	}
}
