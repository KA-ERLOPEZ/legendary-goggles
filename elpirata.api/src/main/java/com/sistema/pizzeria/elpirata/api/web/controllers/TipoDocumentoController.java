package com.sistema.pizzeria.elpirata.api.web.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.pizzeria.elpirata.api.core.services.TipoDocumentoService;
import com.sistema.pizzeria.elpirata.api.web.dto.TipoDocumentoDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/tipodocumento")
public class TipoDocumentoController {

	private final TipoDocumentoService tipoDocumentoService;

	/**
	 * Constructor de la clase TipoDocumentoController
	 * 
	 * @param tipoDocumentoService
	 */
	public TipoDocumentoController(TipoDocumentoService tipoDocumentoService) {
		this.tipoDocumentoService = tipoDocumentoService;
	}

	/**
	 * Método para listar todos los tipos de documentos
	 * 
	 * @return ResponseEntity<List<TipoDocumentoDTO>>
	 */
	@Operation(summary = "Listar todos los tipos de documentos", description = "Este endpoint lista todos los tipos de documentos")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Lista de tipos de documentos"),
			@ApiResponse(responseCode = "404", description = "No se encontraron tipos de documentos") })
	@GetMapping("/listar")
	public ResponseEntity<List<TipoDocumentoDTO>> listar() {
		return ResponseEntity.ok(tipoDocumentoService.findAll());
	}
	
	/**
	 * Método para listar todos los tipos de documentos por estado
	 * 
	 * @return ResponseEntity<List<TipoDocumentoDTO>>
	 */
	@Operation(summary = "Listar tipos de documentos por estado", description = "Este endpoint lista todos los tipos de documentos por estado")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Lista de tipos de documentos por estado"),
			@ApiResponse(responseCode = "404", description = "No se encontraron tipos de documentos por estado") })
	@GetMapping("/listar/{estadoId}")
	public ResponseEntity<List<TipoDocumentoDTO>> listarPorEstado(@PathVariable int estadoId) {
        return ResponseEntity.ok(tipoDocumentoService.findByEstadoId(estadoId));
        
	}
	
	/**
	 * Método para buscar un tipo de documento por su id
	 * 
	 * @param id Id del tipo de documento
	 * @return ResponseEntity<TipoDocumentoDTO>
	 */
	@Operation(summary = "Buscar tipo de documento por id", description = "Este endpoint busca un tipo de documento por su id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Tipo de documento encontrado"),
			@ApiResponse(responseCode = "404", description = "Tipo de documento no encontrado") })
	@GetMapping("/buscar/{id}")
	public ResponseEntity<TipoDocumentoDTO> buscarPorId(@PathVariable long id) {
		return ResponseEntity.ok(tipoDocumentoService.findById(id));
	}
	
	/**
	 * Método para buscar un tipo de documento por su nombre
	 * 
	 * @param tipodocNombre Nombre del tipo de documento
	 * @return ResponseEntity<TipoDocumentoDTO>
	 */
	@Operation(summary = "Buscar tipo de documento por nombre", description = "Este endpoint busca un tipo de documento por su nombre")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Tipo de documento encontrado"),
			@ApiResponse(responseCode = "404", description = "Tipo de documento no encontrado") })
	@GetMapping("/buscar/nombre/{tipodocNombre}")
	public ResponseEntity<TipoDocumentoDTO> buscarPorNombre(@PathVariable String tipodocNombre) {
		return ResponseEntity.ok(tipoDocumentoService.findByTipodocNombre(tipodocNombre));
	}
	
	/**	
	 * Método para validar si existe un tipo de documento por su nombre
	 * 
	 * @param tipodocNombre Nombre del tipo de documento
	 * @return ResponseEntity<Boolean>
	 */
	@Operation(summary = "Validar tipo de documento por nombre", description = "Este endpoint valida si existe un tipo de documento por su nombre")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Tipo de documento encontrado"),
			@ApiResponse(responseCode = "404", description = "Tipo de documento no encontrado") })
	@GetMapping("/validar/nombre/{tipodocNombre}")
	public ResponseEntity<Boolean> validarPorNombre(@PathVariable String tipodocNombre) {
		return ResponseEntity.ok(tipoDocumentoService.existsByTipodocNombre(tipodocNombre));
	}
	
	/**
	 * Método para listar todos los tipos de documentos por coincidencia en el
	 * nombre
	 * 
	 * @param tipodocNombre Nombre del tipo de documento
	 * @return ResponseEntity<List<TipoDocumentoDTO>>
	 */
	@Operation(summary = "Listar tipos de documentos por coincidencia en el nombre", description = "Este endpoint lista todos los tipos de documentos por coincidencia en el nombre")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de tipos de documentos por coincidencia en el nombre"),
			@ApiResponse(responseCode = "404", description = "No se encontraron tipos de documentos por coincidencia en el nombre") })
	@GetMapping("/listar/nombre/{tipodocNombre}")
	public ResponseEntity<List<TipoDocumentoDTO>> listarPorNombre(@PathVariable String tipodocNombre) {
		return ResponseEntity.ok(tipoDocumentoService.getByTipoDocNombreContainingIgnoreCase(tipodocNombre));
	}
	
	/**
	 * Método para guardar un tipo de documento
	 * 
	 * @param tipoDocumentoDTO Tipo de documento a guardar
	 * @return ResponseEntity<TipoDocumentoDTO>
	 */
	@Operation(summary = "Guardar tipo de documento", description = "Este endpoint guarda un tipo de documento")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Tipo de documento guardado"),
			@ApiResponse(responseCode = "404", description = "Tipo de documento no guardado") })
	public ResponseEntity<TipoDocumentoDTO> guardarTipoDocumento( @RequestBody @Valid TipoDocumentoDTO tipoDocumentoDTO) {
		return ResponseEntity.ok(tipoDocumentoService.save(tipoDocumentoDTO));
	}
	
	/**
	 * Método para actualizar un tipo de documento
	 * 
	 * @param id               Id del tipo de documento
	 * @param tipoDocumentoDTO Tipo de documento a actualizar
	 * @return ResponseEntity<TipoDocumentoDTO>
	 */
	@Operation(summary = "Actualizar tipo de documento", description = "Este endpoint actualiza un tipo de documento")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Tipo de documento actualizado"),
			@ApiResponse(responseCode = "404", description = "Tipo de documento no actualizado") })
	public ResponseEntity<TipoDocumentoDTO> actualizarTipoDocumento(@RequestBody @Valid TipoDocumentoDTO tipoDocumentoDTO) {
		return ResponseEntity.ok(tipoDocumentoService.update(tipoDocumentoDTO));
		
	}
	
	/**
	 * Método para eliminar un tipo de documento por su id
	 * 
	 * @param id Id del tipo de documento
	 * @return ResponseEntity<?>
	 */
	@Operation(summary = "Eliminar tipo de documento", description = "Este endpoint elimina un tipo de documento")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Tipo de documento eliminado"),
			@ApiResponse(responseCode = "404", description = "Tipo de documento no eliminado") })
	public ResponseEntity<?> eliminarTipoDocumento(@PathVariable long id) {
		tipoDocumentoService.deleteById(id);
		return ResponseEntity.ok(Map.of());
	}

}
