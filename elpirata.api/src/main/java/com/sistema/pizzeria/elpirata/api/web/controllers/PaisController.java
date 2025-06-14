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

import com.sistema.pizzeria.elpirata.api.core.services.PaisService;
import com.sistema.pizzeria.elpirata.api.web.dto.PaisDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@CrossOrigin()
@RestController
@RequestMapping("/api/public/paises")
public class PaisController {

	private final PaisService paisService;

	public PaisController(PaisService paisService) {
		this.paisService = paisService;
	}
	
	/**
	 * Endpoint para listar todos los países
	 * 
	 * @return ResponseEntity con la lista de países
	 */
	@Operation(summary = "Listar todos los países", description = "Obtiene una lista de todos los países registrados.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Lista de países obtenida exitosamente") })
	@GetMapping
	public List<PaisDTO> listarPaises() {
		return paisService.findAll();
	}

	/**
	 * Endpoint para obtener un país por ID
	 * 
	 * @param id
	 * @return ResponseEntity con el país encontrado
	 */
	@Operation(summary = "Obtener un país por ID", description = "Busca un país específico utilizando su ID.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "País obtenido exitosamente"),
			@ApiResponse(responseCode = "404", description = "País no encontrado") })
	@GetMapping("/{id}")
	public ResponseEntity<PaisDTO> obtenerPais(@PathVariable long id) {
		return ResponseEntity.ok(paisService.findById(id));
	}
	
	/**
	 * Endpoint para crear un nuevo país
	 * 
	 * @param paisDTO
	 * @return ResponseEntity con el país creado
	 */
	@Operation(summary = "Crear un nuevo país", description = "Registra un nuevo país en el sistema.")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "País creado exitosamente"),
			@ApiResponse(responseCode = "400", description = "Datos inválidos proporcionados") })
	@PostMapping
	public ResponseEntity<PaisDTO> crearPais(@Valid @RequestBody PaisDTO paisDTO) {
		return ResponseEntity.ok(paisService.save(paisDTO));
	}
	
	/**
	 * Endpoint para actualizar un país existente
	 * 
	 * @param paisDTO
	 * @return ResponseEntity con el país actualizado
	 */
	@Operation(summary = "Actualizar un país existente", description = "Actualiza los datos de un país existente.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "País actualizado exitosamente"),
			@ApiResponse(responseCode = "404", description = "País no encontrado"),
			@ApiResponse(responseCode = "400", description = "Datos inválidos proporcionados") })
	@PutMapping
	public ResponseEntity<PaisDTO> actualizarPais(@Valid @RequestBody PaisDTO paisDTO) {
		return ResponseEntity.ok(paisService.update(paisDTO));
	}
	
	/**
	 * Endpoint para eliminar un país por ID
	 * 
	 * @param id
	 * @return ResponseEntity sin contenido
	 */
	@Operation(summary = "Eliminar un país por ID", description = "Elimina un país específico utilizando su ID.")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "País eliminado exitosamente"),
			@ApiResponse(responseCode = "404", description = "País no encontrado") })
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarPais(@PathVariable long id) {
		paisService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
