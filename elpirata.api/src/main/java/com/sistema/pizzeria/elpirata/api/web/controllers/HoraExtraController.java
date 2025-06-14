package com.sistema.pizzeria.elpirata.api.web.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.pizzeria.elpirata.api.core.services.HoraExtraService;
import com.sistema.pizzeria.elpirata.api.web.dto.HoraExtraDTO;
import com.sistema.pizzeria.elpirata.api.web.dto.PageResponseDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/hora-extra")
public class HoraExtraController {
		
	private final HoraExtraService service;
	
	/**
	 * Constructor de la clase HoraExtraController
	 * 
	 * @param service el servicio de horas extras
	 */
	public HoraExtraController(HoraExtraService service) {
		this.service = service;
	}

	/**
	 * Método para obtener todas las horas extras
	 * 
	 * @return ResponseEntity con la lista de horas extras
	 */
	@Operation(summary = "Obtener todas las horas extras", description = "Este endpoint permite obtener todas las horas extras registradas en el sistema.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de horas extras obtenida correctamente"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor") })
	@GetMapping("/all")
	public ResponseEntity<List<HoraExtraDTO>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}
	
	/**
	 * Método para obtener una hora extra por empleado
	 * @param empleadoId
	 * @param page
	 * @param size
	 * @return
	 */
	@Operation(summary = "Obtener horas extras por empleado", description = "Este endpoint permite obtener todas las horas extras de un empleado.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de horas extras obtenida correctamente"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor") })
	@GetMapping("/empleado")
	public ResponseEntity<PageResponseDTO<HoraExtraDTO>> findAllByEmpleadoId(
			@RequestParam Long empleadoId ,
			@RequestParam(defaultValue = "0") int page, 
			@RequestParam(defaultValue = "10") int size) {
		return ResponseEntity.ok(service.findAllByEmpleadoId(page, size, empleadoId));
	}
	
	/**
	 * Método para obtener horas extras por contrato y estado
	 * 
	 * @param contratoId id del contrato
	 * @param estado     estado de la hora extra
	 * @param page       número de página
	 * @param size       tamaño de la página
	 * @return ResponseEntity con la lista de horas extras
	 */
	@Operation(summary = "Obtener horas extras por contrato y estado", description = "Este endpoint permite obtener todas las horas extras de un contrato con el estado especificado.")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de horas extras obtenida correctamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor") })
	@GetMapping("/contrato")
	public ResponseEntity<PageResponseDTO<HoraExtraDTO>> getByContratoIdAndEstado(@RequestParam long contratoId,
			@RequestParam String estado, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		return ResponseEntity.ok(service.getByContratoIdAndEstado(contratoId, estado, page, size));
	}
	
	/**
	 * Método para obtener horas extras por contrato y fecha
	 * 
	 * @param contratoId id del contrato
	 * @param fecha    fecha de la hora extra
	 * @return ResponseEntity con la hora extra
	 */
	@Operation(summary = "Obtener horas extras por contrato y fecha", description = "Este endpoint permite obtener una hora extra de un contrato en una fecha especificada.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Hora extra obtenida correctamente"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor") })
	@GetMapping("/contrato/{contratoId}/fecha/{fecha}")
	public ResponseEntity<HoraExtraDTO> getByContratoIdAndFecha(@PathVariable long contratoId, @PathVariable LocalDate fecha){
		return ResponseEntity.ok(service.getByContratoIdAndFecha(contratoId, fecha));
	}
	
	/**
	 * Método para obtener horas extras paginadas
	 * @param page
	 * @param size
	 * @return ResponseEntity con la lista de horas extras
	 */
	@Operation(summary = "Obtener horas extras paginadas", description = "Este endpoint permite obtener todas las horas extras registradas en el sistema de forma paginada.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de horas extras obtenida correctamente"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor") })
	@GetMapping("/pageable")
	public ResponseEntity<PageResponseDTO<HoraExtraDTO>> getAllPageable(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(service.getAll(page, size));
	}
	
	/**
	 * Método para obtener una hora extra por id
	 * 
	 * @param id id de la hora extra
	 * @return ResponseEntity con la hora extra
	 */
	@Operation(summary = "Obtener hora extra por id", description = "Este endpoint permite obtener una hora extra registrada en el sistema.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Hora extra obtenida correctamente"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor") })
	@GetMapping("/{id}")
	public ResponseEntity<HoraExtraDTO>getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }
	
	/**
	 * Método para guardar una hora extra
	 * 
	 * @param dto el objeto HoraExtraDTO a guardar
	 * @return ResponseEntity con la hora extra guardada
	 */
	@Operation(summary = "Guardar hora extra", description = "Este endpoint permite guardar una hora extra en el sistema.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Hora extra guardada correctamente"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor") })
	public ResponseEntity<HoraExtraDTO> saver(@Valid @RequestBody HoraExtraDTO dto) {
		return ResponseEntity.ok(service.save(dto));
	}
	
	
	/**
	 * Método para eliminar una hora extra
	 * 
	 * @param id id de la hora extra a eliminar
	 * @return ResponseEntity con el resultado de la operación
	 */
	@Operation(summary = "Eliminar hora extra", description = "Este endpoint permite eliminar una hora extra registrada en el sistema.")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Hora extra eliminada correctamente"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor") })
	public ResponseEntity<?> delete(@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.ok(Map.of("message", "Hora extra eliminada correctamente"));
	}

}
