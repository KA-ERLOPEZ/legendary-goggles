package com.sistema.pizzeria.elpirata.api.web.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.pizzeria.elpirata.api.core.services.ConceptoService;
import com.sistema.pizzeria.elpirata.api.web.dto.ConceptoDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/conceptos")
public class ConceptoController {

	private final ConceptoService conceptoService;
	
	public ConceptoController(ConceptoService conceptoService) {
		this.conceptoService = conceptoService;
	}
	
	/**
	 * Obtiene todos los conceptos.
	 * 
	 * 
	 * @return ResponseEntity con la lista de ConceptoDTO
	 */
	@Operation(summary = "Obtener todos los conceptos", description = "Devuelve una lista de todos los conceptos disponibles en el sistema.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de conceptos obtenida exitosamente"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor") })
	@GetMapping
	public ResponseEntity<List<ConceptoDTO>> getAllConceptos() {
		List<ConceptoDTO> conceptos = conceptoService.findAll();
		return ResponseEntity.ok(conceptos);
	}
	
	/**
	 * Obtiene todos los conceptos activos.
	 * 
	 * @return ResponseEntity con la lista de ConceptoDTO activos
	 */
	@Operation(summary = "Obtener conceptos activos", description = "Devuelve una lista de todos los conceptos activos disponibles en el sistema.")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de conceptos activos obtenida exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor") })
	@GetMapping("/activos")
	public ResponseEntity<List<ConceptoDTO>> getActiveConceptos() {
		List<ConceptoDTO> conceptosActivos = conceptoService.getByActivoTrue();
		return ResponseEntity.ok(conceptosActivos);
	}
	
	/**
	 * Obtiene un concepto por su nombre.
	 * 
	 * @param nombre Nombre del concepto a buscar
	 * @return ResponseEntity con el ConceptoDTO encontrado
	 */
	@Operation(summary = "Obtener concepto por nombre", description = "Devuelve un concepto específico basado en su nombre.")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Concepto encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Concepto no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor") })
	@GetMapping("/nombre/{nombre}")
	public ResponseEntity<ConceptoDTO> getConceptoByNombre(String nombre) {
		ConceptoDTO concepto = conceptoService.getByNombre(nombre);
		if (concepto != null) {
			return ResponseEntity.ok(concepto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	/**
	 * Obtiene un concepto por su ID.
	 * 
	 * @param id ID del concepto a buscar
	 * @return ResponseEntity con el ConceptoDTO encontrado
	 */
	@Operation(summary = "Obtener concepto por ID", description = "Devuelve un concepto específico basado en su ID.")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Concepto encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Concepto no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor") })
	@GetMapping("/{id}")
	public ResponseEntity<ConceptoDTO> getConceptoById(Long id) {
		ConceptoDTO concepto = conceptoService.findById(id);
		if (concepto != null) {
			return ResponseEntity.ok(concepto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	/**
	 * Guarda un nuevo concepto.
	 * 
	 * @param conceptoDTO ConceptoDTO a guardar
	 * @return ResponseEntity con el ConceptoDTO guardado
	 */
	@Operation(summary = "Guardar nuevo concepto", description = "Crea un nuevo concepto en el sistema.")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Concepto creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor") })
	@GetMapping("/guardar")
	public ResponseEntity<ConceptoDTO> saveConcepto(@RequestBody @Valid ConceptoDTO conceptoDTO) {
		ConceptoDTO savedConcepto = conceptoService.save(conceptoDTO);
		return ResponseEntity.status(201).body(savedConcepto);
	}
	
	/**
	 * Actualiza un concepto existente.
	 * 
	 * @param conceptoDTO ConceptoDTO a actualizar
	 * @return ResponseEntity con el ConceptoDTO actualizado
	 */
	@Operation(summary = "Actualizar concepto", description = "Actualiza un concepto existente en el sistema.")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Concepto actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta"),
            @ApiResponse(responseCode = "404", description = "Concepto no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor") })
	@GetMapping("/actualizar")
	public ResponseEntity<ConceptoDTO> updateConcepto(@RequestBody @Valid ConceptoDTO conceptoDTO) {
		ConceptoDTO updatedConcepto = conceptoService.update(conceptoDTO);
		return ResponseEntity.ok(updatedConcepto);
	}
	
	/**
	 * Elimina un concepto por su ID.
	 * 
	 * @param id ID del concepto a eliminar
	 * @return ResponseEntity con el estado de la operación
	 */
	@Operation(summary = "Eliminar concepto", description = "Elimina un concepto específico basado en su ID.")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Concepto eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Concepto no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor") })
	@GetMapping("/eliminar/{id}")
	public ResponseEntity<Void> deleteConcepto(Long id) {
		conceptoService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	/**
	 * Obtiene todos los conceptos por dominio y estado activo.
	 * 
	 * @param dominioId ID del dominio al que pertenecen los conceptos
	 * @return ResponseEntity con la lista de ConceptoDTO asociados al dominio
	 */
	@Operation(summary = "Obtener conceptos por dominio y estado activo", description = "Devuelve una lista de conceptos asociados a un dominio específico y que están activos.")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de conceptos obtenida exitosamente"),
            @ApiResponse(responseCode = "404", description = "Dominio no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor") })
	@GetMapping("/dominio/{dominioId}/activos")
	public ResponseEntity<List<ConceptoDTO>> getConceptosByDominioAndActivo(long dominioId) {
		List<ConceptoDTO> conceptos = conceptoService.getByDominioIdAndActivoTrue(dominioId);
		return ResponseEntity.ok(conceptos);
	}
	
}
