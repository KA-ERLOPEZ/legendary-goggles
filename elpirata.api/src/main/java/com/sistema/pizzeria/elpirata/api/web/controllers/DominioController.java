package com.sistema.pizzeria.elpirata.api.web.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.pizzeria.elpirata.api.core.services.DominioService;
import com.sistema.pizzeria.elpirata.api.web.dto.DominioDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/dominios")
public class DominioController {

	private final DominioService dominioService;

	public DominioController(DominioService dominioService) {
		this.dominioService = dominioService;
	}

	
	/**
	 * Obtener todos los dominios.
	 * 
	 * @return Lista de dominios.
	 */
	@Operation(summary = "Obtener todos los dominios", description = "Recupera una lista de todos los dominios disponibles.")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DominioDTO.class))) })
	@GetMapping
	public ResponseEntity<List<DominioDTO>> getAllDominios() {
		return ResponseEntity.ok(dominioService.findAll());
	}

	/**
	 * Obtener un dominio por ID.
	 * 
	 * @param id ID del dominio a recuperar.
	 * @return Dominio correspondiente al ID proporcionado.
	 */
	@Operation(summary = "Obtener un dominio por ID", description = "Recupera un dominio específico a través de su ID.")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Dominio encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DominioDTO.class))),
			@ApiResponse(responseCode = "404", description = "Dominio no encontrado", content = @Content) })
	@GetMapping("/{id}")
	public ResponseEntity<DominioDTO> getDominioById(@PathVariable long id) {
		return ResponseEntity.ok(dominioService.findById(id));
	}

	/**
	 * Crear un nuevo dominio.
	 * 
	 * @param dominioDTO Dominio a crear.
	 * @return Dominio creado.
	 */
	@Operation(summary = "Crear un nuevo dominio", description = "Crea un nuevo dominio y lo guarda en el sistema.")
	@ApiResponses({
			@ApiResponse(responseCode = "201", description = "Dominio creado exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DominioDTO.class))),
			@ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content) })
	@PostMapping
	public ResponseEntity<DominioDTO> createDominio(
			@RequestBody @Valid DominioDTO dominioDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(dominioService.save(dominioDTO));
	}
	
	/**
	 * Actualizar un dominio existente.
	 * 
	 * @param id         ID del dominio a actualizar.
	 * @param dominioDTO Dominio actualizado.
	 * @return Dominio actualizado.
	 */
	@Operation(summary = "Actualizar un dominio", description = "Actualiza un dominio existente en el sistema.")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Dominio actualizado exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DominioDTO.class))),
			@ApiResponse(responseCode = "404", description = "Dominio no encontrado", content = @Content) })
	@PostMapping("/actualizar")
	public ResponseEntity<DominioDTO> updateDominio(@RequestBody @Valid DominioDTO dominioDTO) {
		return ResponseEntity.ok(dominioService.update( dominioDTO));
	}

	/**
	 * Eliminar un dominio por ID.
	 * @param id
	 * @return mensaje de éxito.
	 */
	@Operation(summary = "Eliminar un dominio", description = "Elimina un dominio específico del sistema.")
	@ApiResponses({ @ApiResponse(responseCode = "204", description = "Dominio eliminado exitosamente"),
			@ApiResponse(responseCode = "404", description = "Dominio no encontrado", content = @Content) })
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteDominio(@PathVariable long id) {
		dominioService.deleteById(id);
		return ResponseEntity.ok(Map.of("message", "Dominio eliminado"));
	}

	/**
	 * Obtener un dominio por su código.
	 * 
	 * @param codigo Código del dominio a recuperar.
	 * @return Dominio correspondiente al código proporcionado.
	 */
	@Operation(summary = "Obtener un dominio por código", description = "Recupera un dominio utilizando su código único.")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Dominio encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DominioDTO.class))),
			@ApiResponse(responseCode = "404", description = "Dominio no encontrado", content = @Content) })
	@GetMapping("/codigo/{codigo}")
	public ResponseEntity<DominioDTO> getDominioByCodigo(@PathVariable("codigo") String codigo) {
		return ResponseEntity.ok(dominioService.findByCodigo(codigo));
	}
}
