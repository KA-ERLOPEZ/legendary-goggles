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

import com.sistema.pizzeria.elpirata.api.core.services.NacionalidadService;
import com.sistema.pizzeria.elpirata.api.web.dto.NacionalidadDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@CrossOrigin()
@RestController
@RequestMapping("/api/public/nacionalidades")
public class NacionalidadController {

	private final NacionalidadService nacionalidadService;

	public NacionalidadController(NacionalidadService nacionalidadService) {
		this.nacionalidadService = nacionalidadService;
	}

	/**
	 * Endpoint para listar todas las nacionalidades
	 * 
	 * @return ResponseEntity con la lista de nacionalidades
	 */
	@Operation(summary = "Listar todas las nacionalidades", description = "Obtiene una lista de todas las nacionalidades registradas.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de nacionalidades obtenida exitosamente") })
	@GetMapping
	public ResponseEntity<List<NacionalidadDTO>> listarNacionalidades() {
		return ResponseEntity.ok(nacionalidadService.findAll());
	}
	
	/**
	 * Endpoint para obtener una nacionalidad por ID
	 * @param id
	 * @return
	 */
	@Operation(summary = "Obtener una nacionalidad por ID", description = "Busca una nacionalidad específica utilizando su ID.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Nacionalidad obtenida exitosamente"),
			@ApiResponse(responseCode = "404", description = "Nacionalidad no encontrada") })
	@GetMapping("/{id}")
	public ResponseEntity<NacionalidadDTO> obtenerNacionalidadPorId(@PathVariable long id) {
		return ResponseEntity.ok(nacionalidadService.findById(id));
	}
	
	/**
	 * Endpoint para crear una nueva nacionalidad
	 * 
	 * @param nacionalidadDTO
	 * @return ResponseEntity con la nacionalidad creada
	 */
	@Operation(summary = "Crear una nueva nacionalidad", description = "Registra una nueva nacionalidad en el sistema.")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Nacionalidad creada exitosamente"),
			@ApiResponse(responseCode = "400", description = "Datos inválidos proporcionados") })
	@PostMapping
	public ResponseEntity<NacionalidadDTO> crearNacionalidad(@Valid @RequestBody NacionalidadDTO nacionalidadDTO) {
		return ResponseEntity.ok(nacionalidadService.save(nacionalidadDTO));
	}
	
	/**
	 * Endpoint para actualizar una nacionalidad existente
	 * 
	 * @param nacionalidadDTO
	 * @return ResponseEntity con la nacionalidad actualizada
	 */
	@Operation(summary = "Actualizar una nacionalidad existente", description = "Actualiza los datos de una nacionalidad existente, especificando el ID.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Nacionalidad actualizada exitosamente"),
			@ApiResponse(responseCode = "404", description = "Nacionalidad no encontrada"),
			@ApiResponse(responseCode = "400", description = "Datos inválidos proporcionados") })
	@PutMapping()
	public ResponseEntity<NacionalidadDTO> actualizarNacionalidad(@RequestBody @Valid NacionalidadDTO nacionalidadDTO) {
		return ResponseEntity.ok(nacionalidadService.update(nacionalidadDTO));
	}
	
	/**
	 * Endpoint para eliminar una nacionalidad por ID
	 * 
	 * @param id
	 * @return ResponseEntity sin contenido
	 */
	@Operation(summary = "Eliminar una nacionalidad por ID", description = "Elimina una nacionalidad específica utilizando su ID.")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Nacionalidad eliminada exitosamente"),
			@ApiResponse(responseCode = "404", description = "Nacionalidad no encontrada") })
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarNacionalidad(@PathVariable long id) {
		nacionalidadService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
