package com.sistema.pizzeria.elpirata.api.web.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.pizzeria.elpirata.api.core.services.SaborService;
import com.sistema.pizzeria.elpirata.api.web.dto.SaborDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@CrossOrigin()
@RestController
@RequestMapping("/api/sabores")
public class SaborController {

	private final SaborService saborService;

	public SaborController(SaborService saborService) {
		this.saborService = saborService;
	}

	/**
	 * Method to list all Sabores.
	 * 
	 * @return a response entity with a list of Sabores
	 */
	@Operation(summary="Listar todos los sabores",description="Lista todos los sabores disponibles en el sistema.")
	@ApiResponses(value={
			@ApiResponse(responseCode="200",description="Sabores encontrados"),
			@ApiResponse(responseCode="404",description="No se encontraronsabores"),
			@ApiResponse(responseCode="500",description="Error interno delservidor")

	})
	@GetMapping("/list")
	public ResponseEntity<?> findAllSabores() {
		return ResponseEntity.ok(saborService.findAll());
	}
	
	/**
	 * Method to find a Sabor by id.
	 * 
	 * @param id the id of the Sabor
	 * @return a response entity with the Sabor
	 */
	@Operation(summary="Buscar sabor por id",description="Busca un sabor por su id.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Sabor encontrado"),
			@ApiResponse(responseCode = "404", description = "No se encontro el sabor"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor")

	})
	@GetMapping("/{id}")
	public ResponseEntity<?> findSaborById(@PathVariable long id) {
		return ResponseEntity.ok(saborService.findById(id));
	}
	
	/**
	 * Method to save a Sabor.
	 * 
	 * @param dto the SaborDTO
	 * @return a response entity with the saved Sabor
	 */
	@Operation(summary="Guardar sabor",description="Guarda un nuevo sabor en el sistema.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Sabor guardado"),
            @ApiResponse(responseCode = "404", description = "No se pudo guardar el sabor"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")

    })
	@PostMapping("/save")
	public ResponseEntity<?> saveSabor(@RequestBody @Valid SaborDTO dto) {
		return ResponseEntity.ok(saborService.save(dto));
	}
	
	/**
	 * Method to update a Sabor.
	 * 
	 * @param dto the SaborDTO
	 * @return a response entity with the updated Sabor
	 */
	@Operation(summary="Actualizar sabor",description="Actualiza un sabor existente en el sistema.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Sabor actualizado"),
            @ApiResponse(responseCode = "404", description = "No se pudo actualizar el sabor"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")

    })
	@PostMapping("/update")
	public ResponseEntity<?> updateSabor(@RequestBody @Valid SaborDTO dto) {
		return ResponseEntity.ok(saborService.update(dto));
	}
	
	/**
	 * Method to delete a Sabor.
	 * 
	 * @param id the id of the Sabor
	 * @return a response entity with the deleted Sabor
	 */
	@Operation(summary="Eliminar sabor",description="Elimina un sabor existente en el sistema.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Sabor eliminado"),
            @ApiResponse(responseCode = "404", description = "No se pudo eliminar el sabor"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")

    })
	@PostMapping("/delete")
	public ResponseEntity<?> deleteSabor(long id) {
		saborService.deleteById(id);
		return ResponseEntity.ok("Sabor eliminado");
	}
	
	/**
	 * Method to find a Sabor by its name.
	 * 
	 * @param nombre the name of the Sabor
	 * @return a response entity with the Sabor
	 */
	@Operation(summary="Buscar sabor por nombre",description="Busca un sabor por su nombre.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Sabor encontrado"),
            @ApiResponse(responseCode = "404", description = "No se encontro el sabor"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")

    })
	@GetMapping("/nombre/{nombre}")
	public ResponseEntity<?> findSaborByNombre(@PathVariable String nombre) {
		
		return ResponseEntity.ok(saborService.findByNombre(nombre));
	}
	
	/**
	 * Method to list all Sabores by estado.
	 * 
	 * @param estado the estado of the Sabores
	 * @return a response entity with a list of Sabores
	 */
	@Operation(summary="Listar sabores por estado",description="Lista todos los sabores por su estado.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Sabores encontrados"),
            @ApiResponse(responseCode = "404", description = "No se encontraron sabores"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")

    })
	@GetMapping("/estado/{estadoId}")
	public ResponseEntity<?> findSaboresByEstado(@PathVariable("estadoId") Integer estadoId) {
		return ResponseEntity.ok(saborService.findAllByEstado(estadoId));
	}
	

}
