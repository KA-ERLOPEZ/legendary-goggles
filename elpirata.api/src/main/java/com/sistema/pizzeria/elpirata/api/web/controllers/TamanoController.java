package com.sistema.pizzeria.elpirata.api.web.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.pizzeria.elpirata.api.core.services.TamanoService;
import com.sistema.pizzeria.elpirata.api.web.dto.TamanoDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/tamano")
public class TamanoController {

	
	private final TamanoService tamanoService;
	
	/**
	 * Constructor de la clase TamanoController
	 * @param tamanoService
	 */
	public TamanoController(TamanoService tamanoService) {
		this.tamanoService = tamanoService;
	}
	
	/**
	 * Metodo para obtener todos los tamaños
	 * 
	 * @return ResponseEntity<List<LisTamanoDTO>>
	 */
	@Operation(summary = "Listar todos los tamaños", description = "Listar todos los tamaños")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Lista de tamaños"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor") })
	@GetMapping("/list")
	public ResponseEntity<List<TamanoDTO>> getAll() {
		return ResponseEntity.ok(tamanoService.findAll());
	}
	
	/**
	 * Metodo para obtener un tamaño por su id
	 * 
	 * @param id
	 * @return ResponseEntity<TamanoDTO>
	 */
	@Operation(summary = "Listar un tamaño por su id", description = "Listar un tamaño por su id")
	@ApiResponses(value = { 
            @ApiResponse(responseCode = "200", description = "Tamaño encontrado"),
            @ApiResponse(responseCode = "404", description = "Tamaño no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor") })
	@GetMapping("/{id}")
	public ResponseEntity<TamanoDTO> getById(long id) {
		return ResponseEntity.ok(tamanoService.findById(id));
	}
	
	/**
	 * Metodo para guardar un tamaño
	 * 
	 * @param dto
	 * @return ResponseEntity<TamanoDTO>
	 */
	@Operation(summary = "Guardar un tamaño", description = "Guardar un tamaño")
	@ApiResponses(value = { 
            @ApiResponse(responseCode = "200", description = "Tamaño guardado"),
            @ApiResponse(responseCode = "400", description = "Error al guardar el tamaño"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor") })
	@PostMapping("/save")
	public ResponseEntity<TamanoDTO> save( @RequestBody @Valid TamanoDTO dto) {
        return ResponseEntity.ok(tamanoService.save(dto));
    }
	
	/**
	 * Metodo para actualizar un tamaño
	 * 
	 * @param dto
	 * @return ResponseEntity<TamanoDTO>
	 */
	@Operation(summary = "Actualizar un tamaño", description = "Actualizar un tamaño")
	@ApiResponses(value = { 
            @ApiResponse(responseCode = "200", description = "Tamaño actualizado"),
            @ApiResponse(responseCode = "400", description = "Error al actualizar el tamaño"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor") })
	@PostMapping("/update")
	public ResponseEntity<TamanoDTO> update(@RequestBody @Valid TamanoDTO dto) {
		return ResponseEntity.ok(tamanoService.update(dto));
	}
	
	/**
	 * Metodo para eliminar un tamaño por su id
	 * 
	 * @param id
	 * @return ResponseEntity<Void>
	 */
	@Operation(summary = "Eliminar un tamaño por su id", description = "Eliminar un tamaño por su id")
	@ApiResponses(value = { 
            @ApiResponse(responseCode = "200", description = "Tamaño eliminado"),
            @ApiResponse(responseCode = "404", description = "Tamaño no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor") })
	@PostMapping("/delete/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable long id) {
		tamanoService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
