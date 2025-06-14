package com.sistema.pizzeria.elpirata.api.web.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.pizzeria.elpirata.api.core.entities.RoleUriPK;
import com.sistema.pizzeria.elpirata.api.core.services.RoleUriService;
import com.sistema.pizzeria.elpirata.api.web.dto.RoleUriDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/role-uri")
public class RoleUriController {

	private final RoleUriService roleUriService;

	public RoleUriController(RoleUriService roleUriService) {
		this.roleUriService = roleUriService;
	}

	@Operation(summary = "Listar todas las relaciones Role-Uri", description = "Obtiene una lista de todas las relaciones entre roles, URIs y tipos de acceso.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente") })
	@GetMapping
	public ResponseEntity<List<RoleUriDTO>> findAll() {
		return ResponseEntity.ok(roleUriService.findAll());
	}

	@Operation(summary = "Obtener Role-Uri por ID", description = "Busca una relación específica entre un rol, URI y tipo de acceso utilizando su clave compuesta.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Relación obtenida exitosamente"),
			@ApiResponse(responseCode = "404", description = "Relación no encontrada") })
	@GetMapping("/{roleId}/{uriId}/{accessType}")
	public ResponseEntity<RoleUriDTO> findById(@PathVariable long roleId,
			@PathVariable long uriId, @PathVariable String accessType) {
		RoleUriPK id = new RoleUriPK(roleId, uriId, accessType);
		return ResponseEntity.ok(roleUriService.findById(id));
	}

	@Operation(summary = "Crear una nueva relación Role-Uri", description = "Registra una nueva relación entre un rol, URI y tipo de acceso.")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Relación creada exitosamente"),
			@ApiResponse(responseCode = "400", description = "Datos inválidos proporcionados") })
	@PostMapping
	public ResponseEntity<RoleUriDTO> save(@Valid @RequestBody RoleUriDTO dto) {
		RoleUriDTO savedRoleUri = roleUriService.save(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedRoleUri);
	}

	@Operation(summary = "Actualizar una relación Role-Uri existente", description = "Actualiza una relación específica entre un rol, URI y tipo de acceso.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Relación actualizada exitosamente"),
			@ApiResponse(responseCode = "400", description = "Datos inválidos proporcionados"),
			@ApiResponse(responseCode = "404", description = "Relación no encontrada") })
	@PutMapping
	public ResponseEntity<RoleUriDTO> update(@Valid @RequestBody RoleUriDTO dto) {
		RoleUriDTO updatedRoleUri = roleUriService.update(dto);
		return ResponseEntity.ok(updatedRoleUri);
	}

	@Operation(summary = "Eliminar una relación Role-Uri por ID", description = "Elimina una relación específica entre un rol, URI y tipo de acceso utilizando su clave compuesta.")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Relación eliminada exitosamente"),
			@ApiResponse(responseCode = "404", description = "Relación no encontrada") })
	@DeleteMapping("/{roleId}/{uriId}/{accessType}")
	public ResponseEntity<Void> deleteById(@PathVariable("roleId") Integer roleId, @PathVariable("uriId") Integer uriId,
			@PathVariable("accessType") String accessType) {
		RoleUriPK id = new RoleUriPK(roleId, uriId, accessType);
		roleUriService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
