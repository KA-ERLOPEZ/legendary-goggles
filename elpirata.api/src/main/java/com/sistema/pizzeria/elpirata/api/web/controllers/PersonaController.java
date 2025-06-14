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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.pizzeria.elpirata.api.core.services.PersonaService;
import com.sistema.pizzeria.elpirata.api.web.dto.PageResponseDTO;
import com.sistema.pizzeria.elpirata.api.web.dto.PersonaDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/personas")
public class PersonaController {

	private final PersonaService personaService;

	public PersonaController(PersonaService personaService) {
		this.personaService = personaService;
	}
	
	/**
	 * Endpoint para listar todas las personas
	 * 
	 * @return ResponseEntity con la lista de personas
	 */
	@Operation(summary = "Listar todas las personas", description = "Obtiene una lista de todas las personas registradas.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de personas obtenida exitosamente") })
	@GetMapping
	public ResponseEntity<List<PersonaDTO>> listarPersonas() {
		return ResponseEntity.ok(personaService.findAll());
	}

	/**
	 * Endpoint para obtener una persona por ID
	 * 
	 * @param id
	 * @return ResponseEntity con la persona correspondiente al ID
	 */
	@Operation(summary = "Obtener una persona por ID", description = "Busca una persona específica utilizando su ID.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Persona obtenida exitosamente"),
			@ApiResponse(responseCode = "404", description = "Persona no encontrada") })
	@GetMapping("/{id}")
	public ResponseEntity<PersonaDTO> obtenerPersonaPorId(@PathVariable long id) {
		return ResponseEntity.ok(personaService.findById(id));
	}
	
	/**
	 * Endpoint para crear una nueva persona
	 * 
	 * @param personaDTO
	 * @return ResponseEntity con la persona creada
	 */
	@Operation(summary = "Crear una nueva persona", description = "Registra una nueva persona en el sistema.")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Persona creada exitosamente"),
			@ApiResponse(responseCode = "400", description = "Datos inválidos proporcionados") })
	@PostMapping
	public ResponseEntity<PersonaDTO> crearPersona(@Valid @RequestBody PersonaDTO personaDTO) {
		return ResponseEntity.status(201).body(personaService.save(personaDTO));
	}

	/**
	 * Endpoint para actualizar una persona existente
	 * 
	 * @param personaDTO
	 * @return ResponseEntity con la persona actualizada
	 */
	@Operation(summary = "Actualizar una persona existente", description = "Actualiza los datos de una persona existente.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Persona actualizada exitosamente"),
			@ApiResponse(responseCode = "404", description = "Persona no encontrada"),
			@ApiResponse(responseCode = "400", description = "Datos inválidos proporcionados") })
	@PutMapping()
	public ResponseEntity<PersonaDTO> actualizarPersona(@Valid @RequestBody PersonaDTO personaDTO) {

		return ResponseEntity.ok(personaService.update(personaDTO));
	}
	
	/**
	 * Endpoint para eliminar una persona por ID
	 * 
	 * @param id
	 * @return ResponseEntity sin contenido
	 */
	@Operation(summary = "Eliminar una persona por ID", description = "Elimina una persona específica utilizando su ID.")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Persona eliminada exitosamente"),
			@ApiResponse(responseCode = "404", description = "Persona no encontrada") })
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarPersona(@PathVariable long id) {
		personaService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	/**
	 * Endpoint para listar todas las personas paginadas
	 * 
	 * @param page
	 * @param size
	 * @return ResponseEntity con la lista de personas paginadas
	 */
	@Operation(summary = "Listar todas las personas paginadas", description = "Obtiene una lista de todas las personas registradas, paginada.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Lista de personas paginada obtenida exitosamente") })
	@GetMapping("/paginada")
	public ResponseEntity<PageResponseDTO<PersonaDTO>> listarPersonasPaginadas(
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		return ResponseEntity.ok(personaService.getAllbyPage(page, size));
	}
	
	/**
	 * Endpoint para listar todas las personas paginadas y filtradas por nombre
	 * 
	 * @param page
	 * @param size
	 * @param perNombre
	 * @return ResponseEntity con la lista de personas paginadas y filtradas por
	 *         nombre
	 */
	@Operation(summary = "Listar todas las personas paginadas y filtradas por nombre", description = "Obtiene una lista de todas las personas registradas, paginada y filtrada por nombre.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Lista de personas paginada y filtrada por nombre obtenida exitosamente") })
	@GetMapping("/paginada/filtrada")
	public ResponseEntity<PageResponseDTO<PersonaDTO>> listarPersonasPaginadasFiltradasPorNombre(
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
			@RequestParam String perNombre) {
		return ResponseEntity.ok(personaService.getAllbyPageAndFilterByPerNombre(page, size, perNombre));
	}
	
	/**
	 * Endpoint para buscar una persona por su cédula
	 * 
	 * @param perCedula
	 * @return ResponseEntity con la persona correspondiente a la cédula
	 */
	@Operation(summary = "Buscar una persona por su cédula", description = "Busca una persona específica utilizando su cédula.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Persona obtenida exitosamente"),
            @ApiResponse(responseCode = "404", description = "Persona no encontrada") })
	@GetMapping("/cedula/{perCedula}")
	public ResponseEntity<PersonaDTO> buscarPersonaPorCedula(@PathVariable String perCedula) {
		return ResponseEntity.ok(personaService.getByPerCedula(perCedula));
	}
}
