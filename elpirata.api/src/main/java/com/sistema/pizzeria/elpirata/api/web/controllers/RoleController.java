package com.sistema.pizzeria.elpirata.api.web.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.pizzeria.elpirata.api.core.services.RoleService;
import com.sistema.pizzeria.elpirata.api.web.dto.RoleDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

	private final RoleService roleService;

	public RoleController(RoleService roleService) {
		this.roleService = roleService;
	}
	
	/**
	 * Método para listar todos los roles.
	 * @return Lista de roles.
	 */
	@Operation(summary = "Listar todos los roles", description = "Obtiene una lista de todos los roles registrados.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Lista de roles obtenida exitosamente") })
	@GetMapping
	public ResponseEntity<List<RoleDTO>> listarRoles() {
		return ResponseEntity.ok(roleService.findAll());
	}
	
	/**
	 * Método para obtener un rol por su ID.
	 * 
	 * @param id ID del rol a buscar.
	 * @return Rol encontrado.
	 */
	@Operation(summary = "Obtener un rol por ID", description = "Busca un rol específico utilizando su ID.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Rol obtenido exitosamente"),
			@ApiResponse(responseCode = "404", description = "Rol no encontrado") })
	@GetMapping("/{id}")
	public ResponseEntity<RoleDTO> obtenerRolePorId(@PathVariable long id) {
		return ResponseEntity.ok(roleService.findById(id));
	}
	
	/**
	 * Método para crear un nuevo rol.
	 * 
	 * @param roleDTO Datos del rol a crear.
	 * @return Rol creado.
	 */
	@Operation(summary = "Crear un nuevo rol", description = "Registra un nuevo rol en el sistema.")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Rol creado exitosamente"),
			@ApiResponse(responseCode = "400", description = "Datos inválidos proporcionados") })
	@PostMapping
	public ResponseEntity<RoleDTO> crearRole( @RequestBody @Valid RoleDTO roleDTO) {
		return ResponseEntity.status(201).body(roleService.save(roleDTO));
	}
	
	/**
	 * Método para actualizar un rol existente.
	 * 
	 * @param roleDTO Datos del rol a actualizar.
	 * @return Rol actualizado.
	 */
	@Operation(summary = "Actualizar un rol existente", description = "Actualiza los datos de un rol existente.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Rol actualizado exitosamente"),
			@ApiResponse(responseCode = "404", description = "Rol no encontrado"),
			@ApiResponse(responseCode = "400", description = "Datos inválidos proporcionados") })
	@PutMapping
	public ResponseEntity<RoleDTO> actualizarRole(@RequestBody @Valid RoleDTO roleDTO) {
		return ResponseEntity.ok(roleService.update(roleDTO));
	}
	
	/**
	 * Método para eliminar un rol por su ID.
	 * 
	 * @param id ID del rol a eliminar.
	 * @return Respuesta vacía.
	 */
	@Operation(summary = "Eliminar un rol por ID", description = "Elimina un rol específico utilizando su ID.")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Rol eliminado exitosamente"),
			@ApiResponse(responseCode = "404", description = "Rol no encontrado") })
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarRole(@PathVariable long id) {
		roleService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	/**
	 * Método para buscar roles habilitados/deshabilitados.
	 * 
	 * @param roleEnabled Estado del rol (habilitado o deshabilitado).
	 * @return Lista de roles según el estado proporcionado.
	 */
	@Operation(summary = "Buscar roles habilitados/deshabilitados", description = "Obtiene una lista de roles según su estado habilitado o deshabilitado.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Lista de roles obtenida exitosamente") })
	@GetMapping("/enabled/{roleEnabled}")
	public ResponseEntity<List<RoleDTO>> buscarRolesPorEnabled(@PathVariable boolean roleEnabled) {
		return ResponseEntity.ok(roleService.buscarRolePorRoleEnabled(roleEnabled));
	}
	
	/**
	 * Método para buscar un rol por su descripción.
	 * 
	 * @param descripcion Descripción del rol a buscar.
	 * @return Rol encontrado.
	 */
	@Operation(summary = "Buscar un rol por descripción", description = "Obtiene un rol específico utilizando su descripción.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Rol obtenido exitosamente"),
			@ApiResponse(responseCode = "404", description = "Rol no encontrado") })
	@GetMapping("/descripcion/{descripcion}")
	public ResponseEntity<RoleDTO> buscarRolePorDescripcion(@PathVariable String descripcion) {
		return ResponseEntity.ok(roleService.buscarRolePorDescripcion(descripcion));
	}
}
