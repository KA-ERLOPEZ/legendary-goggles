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

import com.sistema.pizzeria.elpirata.api.core.services.ProveedorService;
import com.sistema.pizzeria.elpirata.api.web.dto.ProveedorDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {

	private final ProveedorService proveedorService;

	/**
	 * Constructor for ProveedorController.
	 *
	 * @param proveedorService the ProveedorService to use
	 */
	public ProveedorController(ProveedorService proveedorService) {
		this.proveedorService = proveedorService;
	}

	/**
	 * Obtener todos los proveedores.
	 *
	 * @return ResponseEntity<List<ProveedorDTO>> with all Proveedores
	 */
	@Operation(summary = "Listar proveedores", description = "Obtener todos los proveedores")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "404", description = "No encontrado") })
	@GetMapping("/list")
	public ResponseEntity<List<ProveedorDTO>> getAllProveedores() {

		return ResponseEntity.ok(proveedorService.findAll());

	}

	/**
	 * Obtenre proveedor por id.
	 *
	 * @param id del  Proveedor
	 * @return ResponseEntity<ProveedorDTO> with the Proveedor
	 */
	@Operation(summary = "Obtener proveedor por id", description = "Buscar proveedor por id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "404", description = "Not Found") })
	@GetMapping("/{id}")
	public ResponseEntity<ProveedorDTO> getProveedorById(long id) {

		return ResponseEntity.ok(proveedorService.findById(id));
	}

	/**
	 * Obtenre proveedor por RUC.
	 *
	 * @param ruc del proveedor Proveedor
	 * @return ResponseEntity<ProveedorDTO> with the Proveedor
	 */
	@Operation(summary = "Obtener un proveedor por RUC", description = "Get Proveedor by RUC")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "404", description = "Not Found") })
	@GetMapping("/ruc/{ruc}")
	public ResponseEntity<ProveedorDTO> getProveedorByRuc(String ruc) {

		return ResponseEntity.ok(proveedorService.findByProvRuc(ruc));
	}

	/**
	 * Listar Proveedor por su estoadoId.
	 *
	 * @param estadoId del  Proveedor
	 * @return ResponseEntity<List<ProveedorDTO>> con los proveedores
	 */
	@Operation(summary = "Listar proveedores por estado", description = "Obtener proveedores por estado")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "404", description = "Not Found") })
	@GetMapping("/estado/{estadoId}")
	public ResponseEntity<List<ProveedorDTO>> getProveedorByEstadoId(long estadoId) {

		return ResponseEntity.ok(proveedorService.findByEstado_EstadoId(estadoId));
	}

	/**
	 * Filtrar Proveedor por razon social
	 *
	 * @param provRazonSocial the razon social of the Proveedor
	 * @return ResponseEntity<List<ProveedorDTO>> with the Proveedor
	 */	
	@Operation(summary = "Filtrar Proveedor por razon social", description = "Filtrar Proveedor por razon social")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "404", description = "Not Found") })
	@GetMapping("/razonSocialContains/{provRazonSocial}")
	public ResponseEntity<List<ProveedorDTO>> getProveedorByRazonSocialContains(@PathVariable String provRazonSocial) {

		return ResponseEntity.ok(proveedorService.findByProvRazonSocialContainsIgnoreCase(provRazonSocial));
	}
	
	/**
	 * FIltrar Proveedor por coincidencia parcial de RUC
	 *
	 * @param provRuc the RUC of the Proveedor
	 * @return ResponseEntity<List<ProveedorDTO>> with the Proveedor
	 */
	@Operation(summary = "Filtrar Proveedor por coincidencia parcial de RUC", description = "Filtrar Proveedor por coincidencia parcial de RUC")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "404", description = "Not Found") })
	@GetMapping("/rucContains/{provRuc}")
	public ResponseEntity<List<ProveedorDTO>> getProveedorByRucContains(@PathVariable String provRuc) {

		return ResponseEntity.ok(proveedorService.findByProvRucContainsIgnoreCase(provRuc));
	}
	
	/**
	 * Guardar Proveedor.
	 *
	 * @param proveedorDTO the ProveedorDTO to save
	 * @return ResponseEntity<ProveedorDTO> with the saved Proveedor
	 */
	@Operation(summary = "Guardar proveedor", description = "Guardar proveedor")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "404", description = "Not Found") })
	@PostMapping("/guardar")
	public ResponseEntity<ProveedorDTO> saveProveedor(@RequestBody @Valid ProveedorDTO proveedorDTO) {
		return ResponseEntity.ok(proveedorService.save(proveedorDTO));
	}
	
	/**
	 * Actualizar Proveedor.
	 *
	 * @param id           the id of the Proveedor to update
	 * @param proveedorDTO the ProveedorDTO to update
	 * @return ResponseEntity<ProveedorDTO> with the updated Proveedor
	 */
	@Operation(summary = "Actualizar proveedor", description = "Actualizar proveedor")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not Found") })
	@PostMapping("/actualizar")
	public ResponseEntity<ProveedorDTO> updateProveedor(@RequestBody @Valid ProveedorDTO proveedorDTO) {
		return ResponseEntity.ok(proveedorService.update( proveedorDTO));
	}

}
