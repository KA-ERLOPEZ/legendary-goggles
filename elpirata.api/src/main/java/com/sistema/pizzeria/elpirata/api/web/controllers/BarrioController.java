package com.sistema.pizzeria.elpirata.api.web.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.pizzeria.elpirata.api.core.services.BarrioService;
import com.sistema.pizzeria.elpirata.api.web.dto.BarrioDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@CrossOrigin()
@RestController
@RequestMapping("/api/public/barrios")
public class BarrioController {

	private final BarrioService barrioService;

	public BarrioController(BarrioService barrioService) {
		this.barrioService = barrioService;
	}

	
	/**
	 * Listar todos los barrios
	 * @return Lista de barrios
	 */
	@Operation(summary = "Listar todos los barrios", description = "Obtiene una lista de todos los barrios disponibles.")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Operación exitosa", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BarrioDTO.class))) })
	@GetMapping("/")
	public ResponseEntity<List<BarrioDTO>> listarBarrios() {
		return ResponseEntity.ok(barrioService.findAll());
	}

	/**
	 * Obtener barrio por ID
	 * 
	 * @param id ID del barrio
	 * @return BarrioDTO
	 */
	@Operation(summary = "Obtener barrio por ID", description = "Obtiene un barrio específico mediante su ID.")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Operación exitosa", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BarrioDTO.class))),
			@ApiResponse(responseCode = "404", description = "Barrio no encontrado", content = @Content) })
	@GetMapping("/{id}")
	public ResponseEntity<BarrioDTO> obtenerBarrioPorId(@PathVariable long id) {
		return ResponseEntity.ok(barrioService.findById(id));
	}

	/**
	 * Crear un nuevo barrio
	 * 
	 * @param barrioDTO Barrio a crear
	 * @return BarrioDTO creado
	 */
	@Operation(summary = "Crear un nuevo barrio", description = "Crea un nuevo barrio en el sistema.")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Barrio creado exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BarrioDTO.class))) })
	@PostMapping
	public ResponseEntity<BarrioDTO> crearBarrio( @RequestBody @Valid BarrioDTO barrioDTO) {
		return ResponseEntity.ok(barrioService.save(barrioDTO));
	}
	
	/**
	 * Actualizar un barrio existente
	 * 
	 * @param barrioDTO Barrio a actualizar
	 * @return BarrioDTO actualizado
	 */
	@Operation(summary = "Actualizar un barrio", description = "Actualiza los datos de un barrio existente.")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Barrio actualizado exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BarrioDTO.class))),
			@ApiResponse(responseCode = "404", description = "Barrio no encontrado", content = @Content) })
	@PutMapping()
	public ResponseEntity<BarrioDTO> actualizarBarrio( @Valid @RequestBody BarrioDTO barrioDTO) {

		return ResponseEntity.ok(barrioService.update(barrioDTO));
	}

	/**
	 * Eliminar un barrio por ID
	 * 
	 * @param id ID del barrio a eliminar
	 * @return ResponseEntity<Void>
	 */
	@Operation(summary = "Eliminar un barrio", description = "Elimina un barrio del sistema mediante su ID.")
	@ApiResponses({ @ApiResponse(responseCode = "204", description = "Barrio eliminado exitosamente"),
			@ApiResponse(responseCode = "404", description = "Barrio no encontrado", content = @Content) })
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarBarrio(@PathVariable long id) {
		barrioService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	/**
	 * Listar barrios por ciudad
	 * 
	 * @param ciuId ID de la ciudad
	 * @return Lista de barrios
	 */
	@Operation(summary = "Listar barrios por ciudad", description = "Obtiene una lista de barrios por ciudad.")
	@ApiResponses({
            @ApiResponse(responseCode = "200", description = "Operación exitosa", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BarrioDTO.class))) })
	@GetMapping("/ciudad/{ciuId}")
	public ResponseEntity<List<BarrioDTO>> listarBarriosPorCiudad(@PathVariable long ciuId) {
		return ResponseEntity.ok(barrioService.findByCiudad_CiuId(ciuId));
	}
}
