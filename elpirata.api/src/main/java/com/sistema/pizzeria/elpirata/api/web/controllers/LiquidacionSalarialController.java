package com.sistema.pizzeria.elpirata.api.web.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.pizzeria.elpirata.api.core.services.LiquidacionSalarialService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@CrossOrigin
@RestController
@RequestMapping("/api/liquidacion-salarial")
public class LiquidacionSalarialController {
	
	private final LiquidacionSalarialService liquidacionSalarialService;
	
	/**
	 * Constructor for LiquidacionSalarialController.
	 *
	 * @param liquidacionSalarialService the service to handle liquidacion salarial
	 *                                   operations
	 */
	public LiquidacionSalarialController(LiquidacionSalarialService liquidacionSalarialService) {
		this.liquidacionSalarialService = liquidacionSalarialService;
	}
	
	/**
	 * Retrieves all liquidaciones salariales with pagination.
	 *
	 * @param page the page number to retrieve
	 * @param size the number of records per page
	 * @return a paginated list of liquidaciones salariales
	 */
	@Operation(summary = "Get all liquidaciones salariales", description = "Retrieves all liquidaciones salariales with pagination.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfully retrieved all liquidaciones salariales"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	@GetMapping("/all-paginated")
	public ResponseEntity<?> getAllLiquidacionesSalariales(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		
		return ResponseEntity.ok(liquidacionSalarialService.getAll(page, size));
	}
	
	/**
	 * Retrieves all liquidaciones salariales by contratoId with pagination.
	 *
	 * @param contratoId the ID of the contrato to filter by
	 * @param page       the page number to retrieve
	 * @param size       the number of records per page
	 * @return a paginated list of liquidaciones salariales for the specified
	 *         contratoId
	 */
	@Operation(summary = "Get liquidaciones salariales by contratoId", description = "Retrieves all liquidaciones salariales by contratoId with pagination.")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved liquidaciones salariales by contratoId"),
            @ApiResponse(responseCode = "500", description = "Internal server error") })
	@GetMapping("/by-contrato-id")
	public ResponseEntity<?> getLiquidacionesSalarialesByContratoId(@RequestParam Long contratoId,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

		return ResponseEntity.ok(liquidacionSalarialService.getAllByContratoId(contratoId, page, size));
	}
	
	/**
	 * Retrieves all liquidaciones salariales by contratoId and anio with
	 * pagination.
	 *
	 * @param contratoId the ID of the contrato to filter by
	 * @param anio       the year to filter by
	 * @param page       the page number to retrieve
	 * @param size       the number of records per page
	 * @return a paginated list of liquidaciones salariales for the specified
	 *         contratoId and anio
	 */
	@Operation(summary = "Get liquidaciones salariales by contratoId and anio", description = "Retrieves all liquidaciones salariales by contratoId and anio with pagination.")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved liquidaciones salariales by contratoId and anio"),
            @ApiResponse(responseCode = "500", description = "Internal server error") })
	@GetMapping("/by-contrato-id-and-anio")
	public ResponseEntity<?> getLiquidacionesSalarialesByContratoIdAndAnio(@RequestParam Long contratoId,
			@RequestParam Integer anio, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {

		return ResponseEntity.ok(liquidacionSalarialService.getAllByContratoIdAndAnio(contratoId, anio, page, size));
	}
	
	/**
	 * Retrieves a liquidacion salarial by contratoId, anio, and mes.
	 *
	 * @param contratoId the ID of the contrato to filter by
	 * @param anio       the year to filter by
	 * @param mes        the month to filter by
	 * @return a liquidacion salarial for the specified contratoId, anio, and mes
	 */
	@Operation(summary = "Get liquidacion salarial by contratoId, anio, and mes", description = "Retrieves a liquidacion salarial by contratoId, anio, and mes.")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved liquidacion salarial by contratoId, anio, and mes"),
            @ApiResponse(responseCode = "500", description = "Internal server error") })
	@GetMapping("/by-contrato-id-anio-mes")
	public ResponseEntity<?> getLiquidacionSalarialByContratoIdAnioMes(@RequestParam Long contratoId,
			@RequestParam Integer anio, @RequestParam Integer mes) {

		return ResponseEntity.ok(liquidacionSalarialService.getByContratoIdAndAnioAndMes(contratoId, anio, mes));
	}
	/**
	 * Retrieves all liquidaciones salariales by contratoId and mes with pagination.
	 *
	 * @param contratoId the ID of the contrato to filter by
	 * @param mes        the month to filter by
	 * @param page       the page number to retrieve
	 * @param size       the number of records per page
	 * @return a paginated list of liquidaciones salariales for the specified
	 *         contratoId and mes
	 */
	@Operation(summary = "Get liquidaciones salariales by contratoId and mes", description = "Retrieves all liquidaciones salariales by contratoId and mes with pagination.")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved liquidaciones salariales by contratoId and mes"),
            @ApiResponse(responseCode = "500", description = "Internal server error") })
	@GetMapping("/by-contrato-id-and-mes")
	public ResponseEntity<?> getLiquidacionesSalarialesByContratoIdAndMes(@RequestParam Long contratoId,
			@RequestParam Integer mes, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {

		return ResponseEntity.ok(liquidacionSalarialService.getAllByContratoIdAndMes(contratoId, mes, page, size));
	}
	
	/**
	 * Retrieves all liquidaciones salariales by anio with pagination.
	 *
	 * @param anio the year to filter by
	 * @param page the page number to retrieve
	 * @param size the number of records per page
	 * @return a paginated list of liquidaciones salariales for the specified anio
	 */
	@Operation(summary = "Get liquidaciones salariales by anio", description = "Retrieves all liquidaciones salariales by anio with pagination.")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved liquidaciones salariales by anio"),
            @ApiResponse(responseCode = "500", description = "Internal server error") })
	@GetMapping("/by-anio")
	public ResponseEntity<?> getLiquidacionesSalarialesByAnio(@RequestParam Integer anio,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

		return ResponseEntity.ok(liquidacionSalarialService.getAllByAnio(anio, page, size));
	}
	
	/**
	 * Retrieves all liquidaciones salariales by anio and mes with pagination.
	 *
	 * @param anio the year to filter by
	 * @param mes  the month to filter by
	 * @param page the page number to retrieve
	 * @param size the number of records per page
	 * @return a paginated list of liquidaciones salariales for the specified anio
	 *         and mes
	 */
	@Operation(summary = "Get liquidaciones salariales by anio and mes", description = "Retrieves all liquidaciones salariales by anio and mes with pagination.")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved liquidaciones salariales by anio and mes"),
            @ApiResponse(responseCode = "500", description = "Internal server error") })
	@GetMapping("/by-anio-and-mes")
	public ResponseEntity<?> getLiquidacionesSalarialesByAnioAndMes(@RequestParam Integer anio,
			@RequestParam Integer mes, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {

		return ResponseEntity.ok(liquidacionSalarialService.getByAnioAndMes(anio, mes, page, size));
	}
	
	/**
	 * Retrieves all liquidaciones salariales by contratosId and between fechaLiquidacion with pagination.
	 * 
	 * @param contratosId the ID of the contratos to filter by
	 * @param fechaLiquidacionInicio the start date to filter by
	 * @param fechaLiquidacionFin the end date to filter by
	 * @param page the page number to retrieve
	 * @param size the number of records per page
	 * 
	 * @return a paginated list of liquidaciones salariales for the specified contratosId and fechaLiquidacion
	 */
	@Operation(summary = "Get liquidaciones salariales by contratosId and fechaLiquidacion", description = "Retrieves all liquidaciones salariales by contratosId and between fechaLiquidacion with pagination.")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved liquidaciones salariales by contratosId and fechaLiquidacion"),
            @ApiResponse(responseCode = "500", description = "Internal server error") })
	@GetMapping("/by-contratos-id-and-fecha-liquidacion")
	public ResponseEntity<?> getLiquidacionesSalarialesByContratosIdAndFechaLiquidacion(@RequestParam Long contratosId,
			@RequestParam String fechaLiquidacionInicio, @RequestParam String fechaLiquidacionFin,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

		return ResponseEntity.ok(liquidacionSalarialService.getAllByContratoIdAndFechaLiquidacionBetween(contratosId,
				fechaLiquidacionInicio, fechaLiquidacionFin, page, size));
	}

	/**
	 * Retrieves all liquidaciones salariales by anio and between fechaLiquidacion
	 * with pagination.
	 * 
	 * @param anio                   the year to filter by
	 * @param fechaLiquidacionInicio the start date to filter by
	 * @param fechaLiquidacionFin    the end date to filter by
	 * @param page                   the page number to retrieve
	 * @param size                   the number of records per page
	 * 
	 * @return a paginated list of liquidaciones salariales for the specified anio
	 *         and fechaLiquidacion
	 */
	@Operation(summary = "Get liquidaciones salariales by anio and fechaLiquidacion", description = "Retrieves all liquidaciones salariales by anio and between fechaLiquidacion with pagination.")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved liquidaciones salariales by anio and fechaLiquidacion"),
            @ApiResponse(responseCode = "500", description = "Internal server error") })
	@GetMapping("/by-anio-and-fecha-liquidacion")
	public ResponseEntity<?> getLiquidacionesSalarialesByAnioAndFechaLiquidacion(@RequestParam Integer anio,
			@RequestParam String fechaLiquidacionInicio, @RequestParam String fechaLiquidacionFin,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

		return ResponseEntity.ok(liquidacionSalarialService.getAllByAnioAndFechaLiquidacionBetween(anio,
				fechaLiquidacionInicio, fechaLiquidacionFin, page, size));
	}
	
	/**
	 * Retrieves all liquidaciones salariales by mes and between fechaLiquidacion
	 * with pagination.
	 * 
	 * @param mes                    the month to filter by
	 * @param fechaLiquidacionInicio the start date to filter by
	 * @param fechaLiquidacionFin    the end date to filter by
	 * @param page                   the page number to retrieve
	 * @param size                   the number of records per page
	 * 
	 * @return a paginated list of liquidaciones salariales for the specified mes
	 *         and fechaLiquidacion
	 */
	@Operation(summary = "Get liquidaciones salariales by mes and fechaLiquidacion", description = "Retrieves all liquidaciones salariales by mes and between fechaLiquidacion with pagination.")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved liquidaciones salariales by mes and fechaLiquidacion"),
            @ApiResponse(responseCode = "500", description = "Internal server error") })
	@GetMapping("/by-mes-and-fecha-liquidacion")
	public ResponseEntity<?> getLiquidacionesSalarialesByMesAndFechaLiquidacion(@RequestParam Integer mes,
			@RequestParam String fechaLiquidacionInicio, @RequestParam String fechaLiquidacionFin,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

		return ResponseEntity.ok(liquidacionSalarialService.getAllByMesAndFechaLiquidacionBetween(mes,
				fechaLiquidacionInicio, fechaLiquidacionFin, page, size));
	}
	
	
}
