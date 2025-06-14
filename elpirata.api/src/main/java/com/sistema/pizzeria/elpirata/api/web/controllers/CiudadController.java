package com.sistema.pizzeria.elpirata.api.web.controllers;

import java.util.List;
import java.util.Map;

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

import com.sistema.pizzeria.elpirata.api.core.services.CiudadService;
import com.sistema.pizzeria.elpirata.api.web.dto.CiudadDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/public/ciudades")
public class CiudadController {

	private final CiudadService ciudadService;

	public CiudadController(CiudadService ciudadService) {
		this.ciudadService = ciudadService;
	}

	/**
	 * Listar todas las ciudades.
	 * 
	 * @return Lista de ciudades.
	 */
	@Operation(summary = "Listar todas las ciudades", description = "Obtiene una lista de todas las ciudades disponibles.")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Operación exitosa", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CiudadDTO.class))) })
	@GetMapping
	public ResponseEntity<List<CiudadDTO>> listarCiudades() {
		return ResponseEntity.ok(ciudadService.findAll());
	}

	/**
	 * Obtener ciudad por ID.
	 * 
	 * @param id ID de la ciudad a obtener.
	 * @return Ciudad correspondiente al ID proporcionado.
	 */
	@Operation(summary = "Obtener ciudad por ID", description = "Obtiene una ciudad específica mediante su ID.")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Operación exitosa", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CiudadDTO.class))),
			@ApiResponse(responseCode = "404", description = "Ciudad no encontrada", content = @Content) })
	@GetMapping("/{id}")
	public ResponseEntity<CiudadDTO> obtenerCiudadPorId(@PathVariable long id) {
		return ResponseEntity.ok(ciudadService.findById(id));
	}

	/**
	 * Crear una nueva ciudad.
	 * 
	 * @param ciudadDTO Datos de la ciudad a crear.
	 * @return Ciudad creada.
	 */
	@Operation(summary = "Crear una nueva ciudad", description = "Crea una nueva ciudad en el sistema.")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Ciudad creada exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CiudadDTO.class))) })
	@PostMapping
	public ResponseEntity<CiudadDTO> crearCiudad(@Valid @RequestBody CiudadDTO ciudadDTO) {
		return ResponseEntity.ok(ciudadService.save(ciudadDTO));
	}

	/**
	 * Actualizar una ciudad existente.
	 * 
	 * @param ciudadDTO Datos de la ciudad a actualizar.
	 * @return Ciudad actualizada.
	 */
	@Operation(summary = "Actualizar una ciudad", description = "Actualiza los datos de una ciudad existente.")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Ciudad actualizada exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CiudadDTO.class))),
			@ApiResponse(responseCode = "404", description = "Ciudad no encontrada", content = @Content) })
	@PutMapping()
	public ResponseEntity<CiudadDTO> actualizarCiudad(@Valid @RequestBody CiudadDTO ciudadDTO) {
		return ResponseEntity.ok(ciudadService.update(ciudadDTO));
	}

	/**
	 * Eliminar una ciudad por ID.
	 * 
	 * @param id ID de la ciudad a eliminar.
	 * @return Respuesta vacía si la eliminación fue exitosa.
	 */
	@Operation(summary = "Eliminar una ciudad", description = "Elimina una ciudad del sistema mediante su ID.")
	@ApiResponses({ @ApiResponse(responseCode = "204", description = "Ciudad eliminada exitosamente"),
			@ApiResponse(responseCode = "404", description = "Ciudad no encontrada", content = @Content) })
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarCiudad(@PathVariable long id) {
		ciudadService.deleteById(id);
		return ResponseEntity.ok(Map.of("message", "Ciudad eliminada exitosamente"));
	}
	
	/**
	 * Listar ciudades por ID de país.
	 * 
	 * @param paisId ID del país para filtrar las ciudades.
	 * @return Lista de ciudades correspondientes al ID de país proporcionado.
	 */
	@Operation(summary = "Listar ciudades por ID de país", description = "Obtiene una lista de ciudades filtradas por ID de país.")
	@ApiResponses({
            @ApiResponse(responseCode = "200", description = "Operación exitosa", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CiudadDTO.class))) })
	@GetMapping("/pais/{paisId}")
	public ResponseEntity<List<CiudadDTO>> listarCiudadesPorPais(@PathVariable long paisId) {
		return ResponseEntity.ok(ciudadService.findAllByPaisId(paisId));
	}
}
