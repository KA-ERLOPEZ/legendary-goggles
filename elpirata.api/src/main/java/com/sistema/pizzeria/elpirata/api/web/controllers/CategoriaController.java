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

import com.sistema.pizzeria.elpirata.api.core.services.CategoriaService;
import com.sistema.pizzeria.elpirata.api.web.dto.CategoriaDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

	private final CategoriaService categoriaService;

	/**
	 * Constructor de la clase CategoriaController
	 * 
	 * @param categoriaService
	 */
	public CategoriaController(CategoriaService categoriaService) {
		this.categoriaService = categoriaService;
	}

	/**
	 * Listar todas las categorias
	 * 
	 * @return ResponseEntity<List<CategoriaDTO>>
	 */
	@Operation(summary = "Listar todas las categorias", description = "Este endpoint permite listar todas las categorias del sistema")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Operación exitosa") })
	@GetMapping
	public ResponseEntity<List<CategoriaDTO>> findAll() {
		return ResponseEntity.ok(categoriaService.findAll());
	}

	/**
	 * Listar categorias por estado
	 * 
	 * @param id
	 * @return ResponseEntity<List<CategoriaDTO>>
	 */
	@Operation(summary = "Listar categorias por estado", description = "Este endpoint permite listar todas las categorias del sistema por estado")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Operación exitosa") })
	@GetMapping("/estado/{id}")
	public ResponseEntity<List<CategoriaDTO>> findByEstado_EstadoId(@PathVariable long id) {
		return ResponseEntity.ok(categoriaService.findByEstado_EstadoId(id));
	}

	/**
	 * Listar categorias por nombre
	 * 
	 * @param nombre
	 * @return ResponseEntity<CategoriaDTO>
	 */
	@Operation(summary = "Listar categorias por nombre", description = "Este endpoint permite listar todas las categorias del sistema por nombre")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Operación exitosa") })
	@GetMapping("/nombre/{nombre}")
	public ResponseEntity<CategoriaDTO> findByCatNombre(@PathVariable String nombre) {
		return ResponseEntity.ok(categoriaService.findByCatNombre(nombre));
	}

	/**
	 * Listar categorias por id
	 * 
	 * @param id
	 * @return ResponseEntity<CategoriaDTO>
	 */
	@Operation(summary = "Listar categorias por id", description = "Este endpoint permite listar todas las categorias del sistema por id")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Operación exitosa") })
	@GetMapping("/{id}")
	public ResponseEntity<CategoriaDTO> findById(@PathVariable long id) {

		return ResponseEntity.ok(categoriaService.findById(id));
	}

	/**
	 * Guardar categoria
	 * 
	 * @param dto
	 * @return ResponseEntity<CategoriaDTO>
	 **/
	@Operation(summary = "Guardar categoria", description = "Este endpoint permite guardar una categoria en el sistema")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Operación exitosa") })
	@PostMapping("/guardar")
	public ResponseEntity<CategoriaDTO> save(@RequestBody @Valid CategoriaDTO dto) {
		return ResponseEntity.ok(categoriaService.save(dto));
	}

	/**
	 * Actualizar categoria
	 * 
	 * @param dto
	 * @return ResponseEntity<CategoriaDTO>
	 **/
	@Operation(summary = "Actualizar categoria", description = "Este endpoint permite actualizar una categoria en el sistema")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Operación exitosa") })
	@PostMapping("/actualizar")
	public ResponseEntity<CategoriaDTO> update(@RequestBody @Valid CategoriaDTO dto) {
		return ResponseEntity.ok(categoriaService.update(dto));
	}

	/**
	 * Eliminar categoria
	 * 
	 * @param id
	 * @return ResponseEntity<?>
	 **/
	@Operation(summary = "Eliminar categoria", description = "Este endpoint permite eliminar una categoria en el sistema")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Operación exitosa") })
	@PostMapping("/eliminar/{id}")
	public ResponseEntity<?> deleteById(@PathVariable long id) {
		categoriaService.deleteById(id);
		return ResponseEntity.ok().body("Categoria eliminada");
	}

}
