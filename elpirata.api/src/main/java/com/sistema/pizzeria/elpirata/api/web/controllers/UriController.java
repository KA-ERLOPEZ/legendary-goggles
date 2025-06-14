package com.sistema.pizzeria.elpirata.api.web.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.pizzeria.elpirata.api.core.services.UriService;
import com.sistema.pizzeria.elpirata.api.web.dto.UriDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/uris")
public class UriController {

	private final UriService uriService;

	public UriController(UriService uriService) {
		this.uriService = uriService;
	}

	/**
	 * Obtiene todas las URIs.
	 *
	 * @return Lista de URIs.
	 */
	@Operation(summary = "Obtener todas las URIs", description = "Devuelve una lista de todas las URIs registradas.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente") })
	@GetMapping
	public ResponseEntity<List<UriDTO>> getAllUris() {
		List<UriDTO> uris = uriService.findAll();
		return ResponseEntity.ok(uris);
	}

	/**
	 * Obtiene una URI por su ID.
	 *
	 * @param id ID de la URI.
	 * @return Detalles de la URI.
	 */
	@Operation(summary = "Obtener una URI por ID", description = "Devuelve los detalles de una URI específica.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "URI encontrada"),
			@ApiResponse(responseCode = "404", description = "URI no encontrada") })
	@GetMapping("/{id}")
	public ResponseEntity<UriDTO> getUriById(@PathVariable long id) {
		UriDTO uri = uriService.findById(id);
		return ResponseEntity.ok(uri);
	}

	/**
	 * Crea una nueva URI.
	 *
	 * @param uriDTO Datos de la URI a crear.
	 * @return URI creada.
	 */
	@Operation(summary = "Crear una nueva URI", description = "Registra una nueva URI en el sistema.")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "URI creada exitosamente"),
			@ApiResponse(responseCode = "400", description = "Datos inválidos") })
	@PostMapping
	public ResponseEntity<UriDTO> createUri(@Valid @RequestBody UriDTO uriDTO) {
		UriDTO createdUri = uriService.save(uriDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdUri);
	}

	/**
	 * Actualiza una URI existente.
	 *
	 * @param id     ID de la URI a actualizar.
	 * @param uriDTO Nuevos datos de la URI.
	 * @return URI actualizada.
	 */
	@Operation(summary = "Actualizar una URI", description = "Actualiza los datos de una URI específica.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "URI actualizada exitosamente"),
			@ApiResponse(responseCode = "400", description = "Datos inválidos"),
			@ApiResponse(responseCode = "404", description = "URI no encontrada") })
	@PutMapping("/{id}")
	public ResponseEntity<UriDTO> updateUri(@PathVariable Integer id, @Valid @RequestBody UriDTO uriDTO) {
		uriDTO.setUriId(id); // Asegurar la consistencia del ID
		UriDTO updatedUri = uriService.update(uriDTO);
		return ResponseEntity.ok(updatedUri);
	}

	/**
	 * Elimina una URI por su ID.
	 *
	 * @param id ID de la URI a eliminar.
	 * @return Respuesta de éxito.
	 */
	@Operation(summary = "Eliminar una URI", description = "Elimina una URI específica del sistema.")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "URI eliminada exitosamente"),
			@ApiResponse(responseCode = "404", description = "URI no encontrada") })
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUri(@PathVariable long id) {
		uriService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
