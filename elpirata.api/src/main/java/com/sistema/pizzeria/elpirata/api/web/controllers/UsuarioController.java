package com.sistema.pizzeria.elpirata.api.web.controllers;

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
import org.springframework.web.bind.annotation.RestController;

import com.sistema.pizzeria.elpirata.api.core.services.UsuarioService;
import com.sistema.pizzeria.elpirata.api.web.dto.UsuarioAdminDTO;
import com.sistema.pizzeria.elpirata.api.web.dto.UsuarioClienteDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

	private final UsuarioService usuarioService;

	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	/**
	 * Obtener todos los usuarios en formato de administración.
	 *
	 * @return Lista de usuarios.
	 */
	@Operation(summary = "Listar todos los usuarios", description = "Devuelve una lista de usuarios con detalles administrativos.")
	@ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida exitosamente.")
	@GetMapping
	public ResponseEntity<List<UsuarioAdminDTO>> listarUsuarios() {
		List<UsuarioAdminDTO> usuarios = usuarioService.findAll();
		return ResponseEntity.ok(usuarios);
	}

	/**
	 * Obtener un usuario por su ID.
	 *
	 * @param id ID del usuario.
	 * @return Usuario encontrado.
	 */
	@Operation(summary = "Obtener un usuario por ID", description = "Devuelve los detalles de un usuario específico.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Usuario encontrado."),
			@ApiResponse(responseCode = "404", description = "Usuario no encontrado.") })
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioAdminDTO> obtenerUsuarioPorId(@PathVariable long id) {
		UsuarioAdminDTO usuario = usuarioService.findById(id);
		return ResponseEntity.ok(usuario);
	}

	/**
	 * Crear un nuevo usuario.
	 *
	 * @param usuarioAdminDTO Datos del usuario a crear.
	 * @return Usuario creado.
	 */
	@Operation(summary = "Crear un nuevo usuario", description = "Registra un nuevo usuario en el sistema.")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente."),
			@ApiResponse(responseCode = "400", description = "Datos inválidos.") })
	@PostMapping
	public ResponseEntity<UsuarioAdminDTO> crearUsuario(@Valid @RequestBody UsuarioAdminDTO usuarioAdminDTO) {
		UsuarioAdminDTO nuevoUsuario = usuarioService.save(usuarioAdminDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
	}

	/**
	 * Actualizar un usuario existente.
	 *
	 * @param id              ID del usuario a actualizar.
	 * @param usuarioAdminDTO Nuevos datos del usuario.
	 * @return Usuario actualizado.
	 */
	@Operation(summary = "Actualizar un usuario", description = "Actualiza los detalles de un usuario existente.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Usuario actualizado exitosamente."),
			@ApiResponse(responseCode = "404", description = "Usuario no encontrado."),
			@ApiResponse(responseCode = "400", description = "Datos inválidos.") })
	@PutMapping("/{id}")
	public ResponseEntity<UsuarioAdminDTO> actualizarUsuario(@PathVariable Integer id,
			@Valid @RequestBody UsuarioAdminDTO usuarioAdminDTO) {
		usuarioAdminDTO.setUsuId(id);
		UsuarioAdminDTO usuarioActualizado = usuarioService.update(usuarioAdminDTO);
		return ResponseEntity.ok(usuarioActualizado);
	}

	/**
	 * Eliminar un usuario por su ID.
	 *
	 * @param id ID del usuario a eliminar.
	 * @return Respuesta de éxito.
	 */
	@Operation(summary = "Eliminar un usuario", description = "Elimina un usuario existente del sistema.")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Usuario eliminado exitosamente."),
			@ApiResponse(responseCode = "404", description = "Usuario no encontrado.") })
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarUsuario(@PathVariable long id) {
		usuarioService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	/**
	 * Buscar un usuario por su nombre de usuario.
	 *
	 * @param username Nombre de usuario.
	 * @return Usuario encontrado.
	 */
	@Operation(summary = "Buscar usuario por nombre de usuario", description = "Devuelve los detalles simplificados de un usuario.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Usuario encontrado."),
			@ApiResponse(responseCode = "404", description = "Usuario no encontrado.") })
	@GetMapping("/buscar/{username}")
	public ResponseEntity<UsuarioClienteDTO> buscarUsuarioPorUsername(@PathVariable String username) {
		UsuarioClienteDTO usuarioClienteDTO = usuarioService.findByUsername(username);
		return ResponseEntity.ok(usuarioClienteDTO);
	}
}
