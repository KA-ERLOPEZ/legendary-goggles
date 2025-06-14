package com.sistema.pizzeria.elpirata.api.web.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.pizzeria.elpirata.api.core.services.TurnoService;
import com.sistema.pizzeria.elpirata.api.web.dto.TurnoDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/turnos")
public class TurnoController {
	
	private final TurnoService service;
	/**
	 * Constructor de la clase TurnoController
	 * 
	 * @param service el servicio de turnos
	 */
	public TurnoController(TurnoService service) {
		this.service = service;
	}
	
	/**
	 * Actualiza el estado activo de un turno.
	 * @param id      el ID del turno
	 * @param activo  el nuevo estado (true o false)
	 * @return        el Turno actualizado
	 */
	@Operation(summary = "Actualizar estado activo de un turno", description = "Actualiza el estado activo de un turno por su ID")
	@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Turno actualizado correctamente"),
        @ApiResponse(responseCode = "404", description = "Turno no encontrado"),
    })
	@PutMapping("/turnos/{id}/activo/{activo}")
	public ResponseEntity<TurnoDTO> updateTurnoActivo(@PathVariable Long id, @PathVariable boolean activo) {
	    TurnoDTO updatedTurno = service.updateTurnoActivo(id, activo);
	    return ResponseEntity.ok(updatedTurno);
	}
	
	/**
	 * Obtiene todos los turnos activos.
	 * 
	 * @return una lista de objetos TurnoDTO
	 */
	@Operation(summary = "Obtener todos los turnos activos", description = "Devuelve una lista de todos los turnos activos")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Lista de turnos activos"),
			@ApiResponse(responseCode = "404", description = "No se encontraron turnos activos"), })
	@GetMapping("/activos")
	public ResponseEntity<List<TurnoDTO>> getAllActivos() {
		return ResponseEntity.ok(service.getAllActivos());
	}
	
	/**
	 * Obtiene todos los turnos.
	 * 
	 * @return una lista de objetos TurnoDTO
	 */
	@Operation(summary = "Obtener todos los turnos", description = "Devuelve una lista de todos los turnos")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Lista de turnos"),
			@ApiResponse(responseCode = "404", description = "No se encontraron turnos"), })
	@GetMapping
	public ResponseEntity<List<TurnoDTO>> getAll() {
		return ResponseEntity.ok(service.findAll());
	}
	
	/**
	 * Obtiene un turno por su ID.
	 * 
	 * @param id el ID del turno
	 * @return el objeto TurnoDTO correspondiente al ID
	 */
	@Operation(summary = "Obtener turno por ID", description = "Devuelve un turno por su ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Turno encontrado"),
			@ApiResponse(responseCode = "404", description = "Turno no encontrado"), })
	@GetMapping("/{id}")
	public ResponseEntity<TurnoDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok(service.findById(id));
	}
	
	/**
	 * Guarda un nuevo turno.
	 * 
	 * @param dto el objeto TurnoDTO a guardar
	 * @return el objeto TurnoDTO guardado
	 */
	@Operation(summary = "Guardar nuevo turno", description = "Guarda un nuevo turno")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Turno guardado correctamente"),
            @ApiResponse(responseCode = "400", description = "Error al guardar el turno"), })
	@PutMapping("/guardar")
	public ResponseEntity<TurnoDTO> save(@Valid @RequestBody TurnoDTO dto) {
		return ResponseEntity.ok(service.save(dto));
	}
	
	/**
	 * Actualiza un turno existente.
	 * 
	 * @param dto el objeto TurnoDTO a actualizar
	 * @return el objeto TurnoDTO actualizado
	 */
	@Operation(summary = "Actualizar turno", description = "Actualiza un turno existente")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Turno actualizado correctamente"),
            @ApiResponse(responseCode = "400", description = "Error al actualizar el turno"), })
	@PutMapping("/actualizar")
	public ResponseEntity<TurnoDTO> update(@Valid @RequestBody TurnoDTO dto) {
		return ResponseEntity.ok(service.update(dto));
	}
	
	/**
	 * Elimina un turno por su ID.
	 * 
	 * @param id el ID del turno a eliminar
	 * @return una respuesta vacía
	 */
	@Operation(summary = "Eliminar turno por ID", description = "Elimina un turno por su ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Turno eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Turno no encontrado"), })
	@PutMapping("/eliminar/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.ok(Map.of("message: ","Turno eliminado con exito"));
	}
	
}
