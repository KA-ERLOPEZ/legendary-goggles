package com.sistema.pizzeria.elpirata.api.web.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.pizzeria.elpirata.api.core.services.generics.BonificacionService;
import com.sistema.pizzeria.elpirata.api.web.dto.BonificacionDTO;
import com.sistema.pizzeria.elpirata.api.web.dto.PageResponseDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/bonificaciones")
public class BonificacionController {

	private final BonificacionService bonificacionService;

	/**
	 * Constructor que recibe el servicio
	 * 
	 * @param bonificacionService
	 */
	public BonificacionController(BonificacionService bonificacionService) {
		this.bonificacionService = bonificacionService;
	}

	/**
	 * Listar todas las bonificaciones
	 * 
	 * @return Lista de bonificaciones
	 */
	@Operation(summary = "Listar todas las bonificaciones", description = "Listar todas las bonificaciones")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Bonificaciones encontradas"),
			@ApiResponse(responseCode = "404", description = "No se encontraron bonificaciones") })
	@GetMapping
	public ResponseEntity<List<BonificacionDTO>> findAll() {
		return ResponseEntity.ok(bonificacionService.findAll());
	}
	
	/**
	 * Listar bonificaciones por contrato
	 * 
	 * @param page
	 * @param size
	 * @param contratoId
	 * @return Lista de bonificaciones
	 */
	@GetMapping("/contrato")
	public ResponseEntity<PageResponseDTO<BonificacionDTO>> getByContrato(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size,
			@RequestParam long contratoId) {
		return ResponseEntity.ok(bonificacionService.findByContratoId(page, size, contratoId));
	}
	
	/**
	 * Listar bonificaciones por contrato y nombre o apellido
	 * 
	 * @param page
	 * @param size
	 * @param nombre
	 * @param apellido
	 * @return Lista de bonificaciones
	 */
	@Operation(summary = "Listar bonificaciones por contrato y nombre o apellido", description = "Listar bonificaciones por contrato y nombre o apellido")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Bonificaciones encontradas"),
			@ApiResponse(responseCode = "404", description = "No se encontraron bonificaciones") })
	@GetMapping("/contrato/contains")
	public ResponseEntity<PageResponseDTO<BonificacionDTO>> getByContratoAndNombreOrApellido(
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
			@RequestParam String nombre, @RequestParam String apellido) {
		return ResponseEntity.ok(bonificacionService.findByNombreOrApellido(page, size, nombre, apellido));
	}
	
	/**
	 * Listar bonificaciones por contrato y estado
	 * 
	 * @param page
	 * @param size
	 * @param estado
	 * @param contratoId
	 * @return Lista de bonificaciones
	 */
	@Operation(summary = "Listar bonificaciones por contrato y estado", description = "Listar bonificaciones por contrato y estado")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Bonificaciones encontradas"),
            @ApiResponse(responseCode = "404", description = "No se encontraron bonificaciones") })
	@GetMapping("/contrato/estado")
	public ResponseEntity<PageResponseDTO<BonificacionDTO>> getByEstadoAndContrato(
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
			@RequestParam String estado, @RequestParam long contratoId) {
		return ResponseEntity.ok(bonificacionService.findByEstadoAndContratoId(page, size, estado, contratoId));
	}
	
	/**
	 * Bonificacion por contrato y concepto y fecha
	 * 
	 * @param contratoId
	 * @param conceptoId
	 * @param fecha
	 * @return Bonificacion
	 */
	@Operation(summary = "Bonificacion por contrato y concepto y fecha", description = "Bonificacion por contrato y concepto y fecha")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Bonificacion encontrada"),
            @ApiResponse(responseCode = "404", description = "No se encontraron bonificaciones") })
	@GetMapping("/{contratoId}/{concepto}/{fecha}")
	public ResponseEntity<BonificacionDTO> getByContratoIdAndConceptoIdAndFecha(@PathVariable long contratoId,
			@PathVariable long conceptoId, @PathVariable String fecha) {
		return ResponseEntity
				.ok(bonificacionService.getByContratoIdAndConceptoIdAndFecha(contratoId, conceptoId, fecha));
	}
	
	/**
	 * Listar bonificaciones por contrato y fecha
	 * 
	 * @param contratoId
	 * @param fecha
	 * @return Lista de bonificaciones
	 */
	@Operation(summary = "Listar bonificaciones por contrato y fecha", description = "Listar bonificaciones por contrato y fecha")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Bonificaciones encontradas"),
            @ApiResponse(responseCode = "404", description = "No se encontraron bonificaciones") })
	@GetMapping("/contrato/{contratoId}/{fecha}")
	public ResponseEntity<PageResponseDTO<BonificacionDTO>> getByContratoIdAndFecha(@PathVariable long contratoId,
			@PathVariable String fecha, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		return ResponseEntity.ok(bonificacionService.findByContratoIdAndFecha(contratoId, fecha, page, size));
	}
	
	/**
	 * Obtener bonificacion por id
	 * 
	 * @param id
	 * @return Bonificacion
	 */
	@Operation(summary = "Obtener bonificacion por id", description = "Obtener bonificacion por id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Bonificacion encontrada"),
			@ApiResponse(responseCode = "404", description = "No se encontraron bonificaciones") })
	@GetMapping("/{id}")
	public ResponseEntity<BonificacionDTO> getById(@PathVariable long id) {
		return ResponseEntity.ok(bonificacionService.findById(id));
	}
	
	/**
	 * Eliminar bonificacion por id
	 * 
	 * @param id
	 * @return ResponseEntity
	 */
	@Operation(summary = "Eliminar bonificacion por id", description = "Eliminar bonificacion por id")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Bonificacion eliminada"),
			@ApiResponse(responseCode = "404", description = "No se encontraron bonificaciones") })
	@GetMapping("/delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable long id) {
		bonificacionService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	/**
	 * Guardar bonificacion
	 * 
	 * @param bonificacion
	 * @return Bonificacion
	 */
	
	@Operation(summary = "Guardar bonificacion", description = "Guardar bonificacion")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Bonificacion guardada"),
            @ApiResponse(responseCode = "400", description = "Error al guardar la bonificacion") })
	@GetMapping("/save")
	public ResponseEntity<BonificacionDTO> save(@RequestBody @Valid BonificacionDTO bonificacion) {
        return ResponseEntity.ok(bonificacionService.save(bonificacion));
	}
	
	/**
	 * Actualizar bonificacion
	 * 
	 * @param id
	 * @param bonificacion
	 * @return Bonificacion
	 */
	@Operation(summary = "Actualizar bonificacion", description = "Actualizar bonificacion")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Bonificacion actualizada"),
            @ApiResponse(responseCode = "400", description = "Error al actualizar la bonificacion") })
	@GetMapping("/update/{id}")
	public ResponseEntity<BonificacionDTO> update( @RequestBody @Valid BonificacionDTO bonificacion) {

		return ResponseEntity.ok(bonificacionService.update(bonificacion));
	}
	
}
