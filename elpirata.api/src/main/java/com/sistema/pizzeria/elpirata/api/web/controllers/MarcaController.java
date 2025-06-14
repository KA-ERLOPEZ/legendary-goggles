package com.sistema.pizzeria.elpirata.api.web.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.pizzeria.elpirata.api.core.services.MarcaService;
import com.sistema.pizzeria.elpirata.api.web.dto.MarcaDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@CrossOrigin()
@RestController
@RequestMapping("/api/marca")
public class MarcaController {
	
	private final MarcaService marcaService;
	
	/**
	 * @Autowired public void setMarcaService(MarcaService marcaService) {
	 * this.marcaService = marcaService; }
	 */
	public MarcaController(MarcaService marcaService) {
		this.marcaService = marcaService;
		
	}
	
	/**
	 * Endpoint para listar todas las marcas
	 * 
	 * @param marcaService
	 * @return ResponseEntity con la lista de marcas
	 */
	@Operation(summary = "Listar todas las marcas", description = "Obtiene una lista de todas las marcas disponibles en el sistema.")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Lista de marcas obtenida correctamente."),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor.")
			})
	@GetMapping("/list")
	public ResponseEntity<List<MarcaDTO>> getAllMarcas() {
		List<MarcaDTO> marcas = marcaService.findAll();
		return ResponseEntity.ok(marcas);
	}
	
	/**
	 * Endpoint para listar todas las marcas por estado
	 * 
	 * @param marcaService
	 * @return ResponseEntity con la lista de marcas
	 */
	@Operation(summary = "Listar todas las marcas por estado", description = "Obtiene una lista de todas las marcas disponibles en el sistema.")
	@ApiResponses(value = { 
            @ApiResponse(responseCode = "200", description = "Lista de marcas obtenida correctamente."),
            @ApiResponse(responseCode = "404", description = "Marca no encontrada."),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor.")
            })
	@GetMapping("/list/{estadoId}")
	public ResponseEntity<List<MarcaDTO>> getAllMarcasByEstadoId(@PathVariable("id") Integer estadoId) {
		List<MarcaDTO> marcas = marcaService.findByEstadoId(estadoId);
		return ResponseEntity.ok(marcas);
	}
	
	/**
	 * Endpoint para listar todas las marcas por id
	 * 
	 * @param marcaService
	 * @return ResponseEntity con la marca.
	 */
	@Operation(summary = "Obtener marcas por id", description = "Obtiene una marca por su id.")
	@ApiResponses(value = { 
            @ApiResponse(responseCode = "200", description = "Marca obtenida correctamente."),
            @ApiResponse(responseCode = "404", description = "Marca no encontrada."),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor.")
            })
	@GetMapping("{id}")
	public ResponseEntity<MarcaDTO> getMarcaById(@PathVariable long id) {
		MarcaDTO marca = marcaService.findById(id);
		return ResponseEntity.ok(marca);
	}
	
	/**
	 * Endpoint para obtener una marca por nombre
	 * 
	 * @param marcaService
	 * @return ResponseEntity con una marca
	 */
	@Operation(summary = "Buscar marca por nombre", description = "Obtiene una marca por su nombre.")
	@ApiResponses(value = { 
            @ApiResponse(responseCode = "200", description = "Marca obtenida correctamente."),
            @ApiResponse(responseCode = "404", description = "Marca no encontrada."),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor.")
            })
	@GetMapping("/buscar/{nombre}")
	public ResponseEntity<MarcaDTO> getMarcaByNombre(@PathVariable String nombre) {
		MarcaDTO marca = marcaService.findByMarNombre(nombre);
		return ResponseEntity.ok(marca);
	}
	
	/**
	 * Endpoint para guardar una marca
	 * 
	 * @param marcaService
	 * @return ResponseEntity con la marca guardada
	 */
	@Operation(summary = "Guardar marca", description = "Guarda una marca en el sistema.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Marca guardada correctamente."),
			@ApiResponse(responseCode = "400", description = "Error al guardar la marca."),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor.") })
	public ResponseEntity<MarcaDTO> saveMarca(@RequestBody @Valid MarcaDTO marcaDTO) {
		MarcaDTO marca = marcaService.save(marcaDTO);
		return ResponseEntity.ok(marca);
	}
	
	/**
	 * Endpoint para actualizar una marca
	 * 
	 * @param marcaService
	 * @return ResponseEntity con la marca actualizada
	 */
	@Operation(summary = "Actualizar marca", description = "Actualiza una marca en el sistema.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Marca actualizada correctamente."),
            @ApiResponse(responseCode = "400", description = "Error al actualizar la marca."),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor.") })
	public ResponseEntity<MarcaDTO> updateMarca(@RequestBody @Valid MarcaDTO marcaDTO) {
		MarcaDTO marca = marcaService.update(marcaDTO);
		return ResponseEntity.ok(marca);
	}
	
	/**
	 * Endpoint para eliminar una marca
	 * 
	 * @param marcaService
	 * @return ResponseEntity con la marca eliminada
	 */
	@Operation(summary = "Eliminar marca", description = "Elimina una marca en el sistema.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Marca eliminada correctamente."),
            @ApiResponse(responseCode = "404", description = "Marca no encontrada."),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor.") })
	public ResponseEntity<Void> deleteMarca(@PathVariable long id) {
		marcaService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	

}
