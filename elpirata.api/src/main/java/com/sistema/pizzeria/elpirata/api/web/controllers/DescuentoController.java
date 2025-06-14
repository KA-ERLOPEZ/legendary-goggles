package com.sistema.pizzeria.elpirata.api.web.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.pizzeria.elpirata.api.core.services.DescuentoService;
import com.sistema.pizzeria.elpirata.api.web.dto.DescuentoDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/descuentos")
public class DescuentoController {
	
	private final DescuentoService descuentoService;
	
	/**
	 * Constructor for DescuentoController.
	 * 
	 * @param descuentoService the service to handle discount operations
	 */
	public DescuentoController(DescuentoService descuentoService) {
		this.descuentoService = descuentoService;
	}
	
	/**
     * Obtiene una lista de todos los descuentos disponibles.
     * 
     * @return ResponseEntity con la lista de descuentos
     */
	@Operation(summary = "Obtener descuentos", description = "Obtiene una lista de todos los descuentos disponibles.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Descuentos obtenidos correctamente"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor") })
	@GetMapping
	public ResponseEntity<?> obtenerDescuentos() {
		// Implementación para obtener descuentos
		return ResponseEntity.ok(descuentoService.findAll());
	}
	
	/**
	 * Obtiene un descuento por su ID.
	 * 
	 * @param id el ID del descuento
	 * @return ResponseEntity con el descuento encontrado o un error si no se
	 *         encuentra
	 */
	@Operation(summary = "Obtener descuento por ID", description = "Obtiene un descuento específico por su ID.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Descuento encontrado correctamente"),
            @ApiResponse(responseCode = "404", description = "Descuento no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor") })
	@GetMapping("/{id}")
	public ResponseEntity<?> obtenerDescuentoPorId(Long id) {
		// Implementación para obtener un descuento por ID
		return ResponseEntity.ok(descuentoService.findById(id));
	}
	
	/**
	 * Guardar un nuevo descuento.
	 * 
	 * @param descuento el descuento a crear
	 * @return ResponseEntity con el descuento creado
	 */
	@Operation(summary = "Guardar descuento", description = "Guardar un nuevo descuento.")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Descuento creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor") })
	@PostMapping("/guardar")
	public ResponseEntity<?> GuardarDescuento(@RequestBody @Valid DescuentoDTO descuento) {
		// Implementación para crear un descuento
		return ResponseEntity.status(201).body(descuentoService.save(descuento));
	}
	
	/**
	 * Actualizar un descuento existente.
	 * 
	 * @param id        el ID del descuento a actualizar
	 * @param descuento el descuento actualizado
	 * @return ResponseEntity con el descuento actualizado
	 */
	@Operation(summary = "Actualizar descuento", description = "Actualizar un descuento existente.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Descuento actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Descuento no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor") })
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<?> actualizarDescuento( @RequestBody @Valid DescuentoDTO descuento) {
		// Implementación para actualizar un descuento
		return ResponseEntity.ok(descuentoService.update(descuento));
	}
	
	/**
	 * Eliminar un descuento por su ID.
	 * 
	 * @param id el ID del descuento a eliminar
	 * @return ResponseEntity con el resultado de la operación
	 */
	@Operation(summary = "Eliminar descuento", description = "Eliminar un descuento por su ID.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Descuento eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Descuento no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor") })
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminarDescuento(Long id) {
		// Implementación para eliminar un descuento
		descuentoService.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	/**
	 * Obtiene descuentos por conctrato ID.
	 * 
	 * @param contratoId el ID del contrato
	 * @param page       el número de página
	 * @param size       el tamaño de la página
	 * @return ResponseEntity con los descuentos encontrados
	 */
	@Operation(summary = "Obtener descuentos por contrato ID", description = "Obtiene una lista de descuentos por contrato ID.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Descuentos obtenidos correctamente"),
			@ApiResponse(responseCode = "404", description = "Descuento no encontrado"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor") })
	@GetMapping("/contrato/{contratoId}")
	public ResponseEntity<?> obtenerDescuentosPorContratoId(@PathVariable long contratoId,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10")  int size) {
		return ResponseEntity.ok(descuentoService.getAllByContratoId(contratoId, page, size));
	}
	
	/**
	 * Obtiene descuentos por concepto ID.
	 * 
	 * @param conceptoId el ID del concepto
	 * @param page       el número de página
	 * @param size       el tamaño de la página
	 * @return ResponseEntity con los descuentos encontrados
	 */
	@Operation(summary = "Obtener descuentos por concepto ID", description = "Obtiene una lista de descuentos por concepto ID.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Descuentos obtenidos correctamente"),
            @ApiResponse(responseCode = "404", description = "Descuento no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor") })
	@GetMapping("/concepto/{conceptoId}")
	public ResponseEntity<?> obtenerDescuentosPorConceptoId(@PathVariable long conceptoId,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		return ResponseEntity.ok(descuentoService.getAllByConceptoId(conceptoId, page, size));
	}
	
	/**
	 * Obtiene descuentos por concepto ID y estado.
	 * 
	 * @param conceptoId el ID del concepto
	 * @param estado     el estado del descuento
	 * @param page       el número de página
	 * @param size       el tamaño de la página
	 * @return ResponseEntity con los descuentos encontrados
	 */
	@Operation(summary = "Obtener descuentos por concepto ID y estado", description = "Obtiene una lista de descuentos por concepto ID y estado.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Descuentos obtenidos correctamente"),
            @ApiResponse(responseCode = "404", description = "Descuento no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor") })
	@GetMapping("/concepto/{conceptoId}/{estado}")
	public ResponseEntity<?> obtenerDescuentosPorConceptoIdYEstado(@PathVariable long conceptoId,
			@PathVariable String estado, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		return ResponseEntity.ok(descuentoService.getAllByConceptoIdAndEstado(conceptoId, estado, page, size));
	}
	
	/**
	 * Busca un descuento por contrato, concepto y fecha.
	 * 
	 * @param contratoId el ID del contrato
	 * @param conceptoId el ID del concepto
	 * @param fecha      la fecha del descuento
	 * @return ResponseEntity con el descuento encontrado o un error si no se
	 *         encuentra
	 */
	@Operation(summary = "Buscar descuento por contrato, concepto y fecha", description = "Busca un descuento por contrato, concepto y fecha.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Descuento encontrado correctamente"),
            @ApiResponse(responseCode = "404", description = "Descuento no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor") })
	@GetMapping("/{contratoId}/{conceptoId}/{fecha}")
	public ResponseEntity<DescuentoDTO> buscarDescuentoPorContratoConceptoYFecha(@PathVariable long contratoId,
			@PathVariable long conceptoId, @PathVariable String fecha) {
		return ResponseEntity.ok( descuentoService.getByContratoIdAndConceptoIdAndFecha(contratoId, conceptoId, fecha));
		
	}
	
	/**
	 * Obtiene descuentos por estado.
	 * 
	 * @param estado el estado del descuento
	 * @param page   el número de página
	 * @param size   el tamaño de la página
	 * @return ResponseEntity con los descuentos encontrados
	 */
	@Operation(summary = "Obtener descuentos por fecha", description = "Obtiene una lista de descuentos entre dos fechas.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Descuentos obtenidos correctamente"),
            @ApiResponse(responseCode = "404", description = "Descuento no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor") })
	@GetMapping("/fecha")
	public ResponseEntity<?> obtenerDescuentosPorFecha(@RequestParam String startDate, @RequestParam String endDate,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		return ResponseEntity.ok(descuentoService.getAllByFechaBetween(startDate, endDate, page, size));
	}
	
	/**
	 * Obtiene descuentos por estado.
	 * 
	 * @param estado el estado del descuento
	 * @param page   el número de página
	 * @param size   el tamaño de la página
	 * @return ResponseEntity con los descuentos encontrados
	 */
	@Operation(summary = "Obtener descuentos por estado", description = "Obtiene una lista de descuentos por estado.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Descuentos obtenidos correctamente"),
            @ApiResponse(responseCode = "404", description = "Descuento no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor") })
	@GetMapping("/estado/{estado}")
	public ResponseEntity<?> obtenerDescuentosPorEstado(@PathVariable String estado,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		return ResponseEntity.ok(descuentoService.getAllByEstado(estado, page, size));
	}
	
	/**
	 * Obtiene descuentos por contrato ID y estado.
	 * 
	 * @param contratoId el ID del contrato
	 * @param estado     el estado del descuento
	 * @param page       el número de página
	 * @param size       el tamaño de la página
	 * @return ResponseEntity con los descuentos encontrados
	 */
	@Operation(summary = "Obtener descuentos por contrato ID y estado", description = "Obtiene una lista de descuentos por contrato ID y estado.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Descuentos obtenidos correctamente"),
            @ApiResponse(responseCode = "404", description = "Descuento no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor") })
	@GetMapping("/contrato/{contratoId}/estado/{estado}")
	public ResponseEntity<?> obtenerDescuentosPorContratoIdYEstado(@PathVariable long contratoId,
			@PathVariable String estado, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		return ResponseEntity.ok(descuentoService.getAllByContratoIdAndEstado(contratoId, estado, page, size));
	}

}
