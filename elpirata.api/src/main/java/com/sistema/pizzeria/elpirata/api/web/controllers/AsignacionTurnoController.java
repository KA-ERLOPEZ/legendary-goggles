package com.sistema.pizzeria.elpirata.api.web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.pizzeria.elpirata.api.core.services.AsignacionTurnoService;
import com.sistema.pizzeria.elpirata.api.web.dto.AsignacionTurnoDTO;
import com.sistema.pizzeria.elpirata.api.web.dto.PageResponseDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/asignacion")
public class AsignacionTurnoController {

    private final AsignacionTurnoService service;

    public AsignacionTurnoController(AsignacionTurnoService service) {
        this.service = service;
    }

    @Operation(summary = "Listar todas las asignaciones", description = "Obtener todas las asignaciones activas y no activas")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    public ResponseEntity<?> listarAsignaciones() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Crear una asignación", description = "Crear una nueva asignación de turno")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Asignación creada correctamente"),
        @ApiResponse(responseCode = "400", description = "Error al crear la asignación"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping("/guardar")
    public ResponseEntity<AsignacionTurnoDTO> guardarAsignacion(@Valid @RequestBody AsignacionTurnoDTO asignacion) {
        return new ResponseEntity<>(service.save(asignacion), HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar una asignación", description = "Actualizar una asignación existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "202", description = "Actualización aceptada"),
        @ApiResponse(responseCode = "400", description = "Error al actualizar la asignación"),
        @ApiResponse(responseCode = "404", description = "Asignación no encontrada"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<AsignacionTurnoDTO> actualizarAsignacion(
            @PathVariable long id,@Valid @RequestBody AsignacionTurnoDTO asignacion) {
        return new ResponseEntity<>(service.update(id, asignacion), HttpStatus.ACCEPTED);
    }

    @Operation(summary = "Buscar asignación por ID", description = "Obtener una asignación mediante su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Asignación encontrada"),
        @ApiResponse(responseCode = "404", description = "Asignación no encontrada"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<AsignacionTurnoDTO> buscarPorId(@PathVariable long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @Operation(summary = "Listar todas las asignaciones paginadas", description = "Listar todas las asignaciones (activas y no activas) en formato paginado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/all/paginated")
    public ResponseEntity<PageResponseDTO<AsignacionTurnoDTO>> listarTodoPaginado(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(service.getAllPage(page, size));
    }

    @Operation(summary = "Listar asignaciones activas paginadas", description = "Listar todas las asignaciones activas en formato paginado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/activos/paginated")
    public ResponseEntity<PageResponseDTO<AsignacionTurnoDTO>> listarAsignacionesActivasPaginado(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(service.getAllByActivoTrue(page, size));
    }

    @Operation(summary = "Listar asignaciones activas por turno", description = "Listar asignaciones activas filtradas por turno en formato paginado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/turno/activos/paginated")
    public ResponseEntity<PageResponseDTO<AsignacionTurnoDTO>> listarAsignacionesPorTurnoPaginado(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam long turnoId) {
        return ResponseEntity.ok(service.getAllByTurnoAndActivoTrue(page, size, turnoId));
    }

    @Operation(summary = "Listar asignaciones activas por contrato", description = "Listar asignaciones activas filtradas por contrato en formato paginado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/contrato/activos/paginated")
    public ResponseEntity<PageResponseDTO<AsignacionTurnoDTO>> listarAsignacionesPorContratoPaginado(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam long contratoId) {
        return ResponseEntity.ok(service.getAllByContratoAndActivoTrue(page, size, contratoId));
    }

}

