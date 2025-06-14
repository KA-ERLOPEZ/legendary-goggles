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

import com.sistema.pizzeria.elpirata.api.core.services.EstadoService;
import com.sistema.pizzeria.elpirata.api.web.dto.DominioDTO;
import com.sistema.pizzeria.elpirata.api.web.dto.EstadoDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/estados")
public class EstadoController {

	private final EstadoService estadoService;

	public EstadoController(EstadoService estadoService) {
		this.estadoService = estadoService;
	}

	/**
	 * Listar todos los estados.
	 * 
	 * @return Lista de estados.
	 */
	@Operation(summary = "Obtener todos los estados", description = "Recupera una lista de todos los estados disponibles.")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EstadoDTO.class))) })
	@GetMapping
	public ResponseEntity<List<EstadoDTO>> listarEstados() {
		List<EstadoDTO> estados = estadoService.findAll();
		return ResponseEntity.ok(estados);
	}

	/**
	 * Obtener estado por ID.
	 * 
	 * @param id ID del estado a recuperar.
	 * @return Estado correspondiente al ID proporcionado.
	 */
	@Operation(summary = "Obtener un estado por ID", description = "Recupera un estado específico utilizando su ID.")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Estado encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EstadoDTO.class))),
			@ApiResponse(responseCode = "404", description = "Estado no encontrado", content = @Content) })
	@GetMapping("/{id}")
	public ResponseEntity<EstadoDTO> obtenerEstadoPorId(@PathVariable long id) {
		EstadoDTO estado = estadoService.findById(id);
		return ResponseEntity.ok(estado);
	}

	/**
	 * Crear un nuevo estado.
	 * 
	 * @param estadoDTO Datos del nuevo estado a crear.
	 * @return Estado creado.
	 */
	@Operation(summary = "Crear un nuevo estado", description = "Crea un estado nuevo y lo guarda en el sistema.")
	@ApiResponses({
			@ApiResponse(responseCode = "201", description = "Estado creado exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EstadoDTO.class))),
			@ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content) })
	@PostMapping
	public ResponseEntity<EstadoDTO> crearEstado(@RequestBody @Valid EstadoDTO estadoDTO) {
		EstadoDTO nuevoEstado = estadoService.save(estadoDTO);
		return ResponseEntity.ok(nuevoEstado);
	}

	/**
	 * Actualizar un estado existente.
	 * 
	 * @param estadoDTO Datos del estado a actualizar.
	 * @return Estado actualizado.
	 */
	@Operation(summary = "Actualizar un estado existente", description = "Actualiza un estado utilizando su ID y el cuerpo de la solicitud.")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Estado actualizado exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EstadoDTO.class))),
			@ApiResponse(responseCode = "404", description = "Estado no encontrado", content = @Content) })
	@PutMapping()
	public ResponseEntity<EstadoDTO> actualizarEstado(@RequestBody EstadoDTO estadoDTO) {
		EstadoDTO estadoActualizado = estadoService.update(estadoDTO);
		return ResponseEntity.ok(estadoActualizado);
	}

	/**
	 * Eliminar un estado por ID.
	 * 
	 * @param id ID del estado a eliminar.
	 * @return Respuesta vacía con código 204 si se elimina exitosamente.
	 */
	@Operation(summary = "Eliminar un estado", description = "Elimina un estado específico del sistema utilizando su ID.")
	@ApiResponses({ @ApiResponse(responseCode = "204", description = "Estado eliminado exitosamente"),
			@ApiResponse(responseCode = "404", description = "Estado no encontrado", content = @Content) })
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarEstado(@PathVariable long id) {
		estadoService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	/**
	 * Buscar estados por dominio.
	 * 
	 * @param dominioDTO Dominio para buscar estados relacionados.
	 * @return Lista de estados relacionados con el dominio.
	 */
	@Operation(summary = "Buscar estados por dominio", description = "Recupera una lista de estados relacionados con un dominio específico.")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Estados encontrados", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EstadoDTO.class))),
			@ApiResponse(responseCode = "404", description = "Dominio no encontrado", content = @Content) })
	@GetMapping("/buscar-por-dominio")
	public ResponseEntity<List<EstadoDTO>> buscarEstadosPorDominio(@RequestBody @Valid DominioDTO dominioDTO) {
		List<EstadoDTO> estados = estadoService.buscarEstadoPorDominio(dominioDTO);
		return ResponseEntity.ok(estados);
	}
}
