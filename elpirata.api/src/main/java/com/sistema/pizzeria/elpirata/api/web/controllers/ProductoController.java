package com.sistema.pizzeria.elpirata.api.web.controllers;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.pizzeria.elpirata.api.core.services.ProductoService;
import com.sistema.pizzeria.elpirata.api.web.dto.ProductoDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/productos")
public class ProductoController {

	private final ProductoService productoService;
	
	/**
	 * Constructor de la clase ProductoController.
	 * 
	 * @param productoService Servicio de productos.
	 */
	public ProductoController(ProductoService productoService) {
		this.productoService = productoService;
	}
	
	/**
	 * Método para obtener un producto por su ID.
	 *
	 * @param id ID del producto a buscar.
	 * @return respuesta con el producto encontrado.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<ProductoDTO> findById(@PathVariable long id) {
		ProductoDTO producto = productoService.findById(id);
		return ResponseEntity.ok(producto);
	}
	
	/**
	 * Método para listar todos los productos sin paginación.
	 * 
	 * @return respuesta con todos los productos.
	 */
	@GetMapping("/all/no-paginated")
	public ResponseEntity<?> findAll() {
		return ResponseEntity.ok(productoService.findAll());
	}
	
	/**
	 * Método para guardar un producto.
	 * 
	 * @param producto Producto a guardar.
	 * @return respuesta con el producto guardado.
	 */
	@Operation(summary = "Guardar producto", description = "Guarda un nuevo producto en el sistema")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Producto guardado correctamente"),
			@ApiResponse(responseCode = "400", description = "Error al guardar el producto"),
			@ApiResponse(responseCode = "404", description = "Producto no encontrado") 
	})
	@PostMapping("/guardar")
	public ResponseEntity<?> guardar(@RequestBody @Valid ProductoDTO producto) {
		return ResponseEntity.ok(productoService.save(producto));
	}
	
	/**
	 * Método para eliminar un producto por su ID.
	 *
	 * @param id ID del producto a eliminar.
	 * @return respuesta con el producto eliminado.
	 */
	@Operation(summary = "Eliminar producto", description = "Elimina un producto del sistema")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Producto eliminado correctamente"),
			@ApiResponse(responseCode = "400", description = "Error al eliminar el producto"),
			@ApiResponse(responseCode = "404", description = "Producto no encontrado") })
	@PostMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminar(@PathVariable long id) {
		productoService.deleteById(id);
		return ResponseEntity.ok(Map.of("message", "Producto eliminado"));
	}

	/**
	 * Método para actualizar un producto.
	 *
	 * @param id       ID del producto a actualizar.
	 * @param producto Producto con los nuevos datos.
	 * @return respuesta con el producto actualizado.
	 */
	@Operation(summary = "Actualizar producto", description = "Actualiza un producto en el sistema")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Producto actualizado correctamente"),
			@ApiResponse(responseCode = "400", description = "Error al actualizar el producto"),
			@ApiResponse(responseCode = "404", description = "Producto no encontrado") })
	@PostMapping("/actualizar")
	public ResponseEntity<?> actualizar( @RequestBody @Valid ProductoDTO producto) {
		return ResponseEntity.ok(productoService.update( producto));
	}
	
	/**
	 * Método para obtener un producto por su nombre.
	 *
	 * @param nombre Nombre del producto a buscar.
	 * @return respuesta con el producto encontrado.
	 */
	@Operation(summary = "Buscar producto por nombre", description = "Busca un producto por su nombre")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Producto encontrado"),
			@ApiResponse(responseCode = "404", description = "Producto no encontrado") })
	@GetMapping("/nombre/{nombre}")
	public ResponseEntity<ProductoDTO> findByNombre(@PathVariable String nombre) {
		ProductoDTO producto = productoService.findByNombre(nombre);
		return ResponseEntity.ok(producto);
	}
	
	/**
	 * Método para obtener productos por categoría.
	 *
	 * @param catId ID de la categoría a buscar.
	 * @param page Número de página.
	 * @param size Cantidad de elementos por página.
	 * @return respuesta con los productos encontrados por categoría.
	 */
	@Operation(summary = "Buscar producto por categoría", description = "Busca productos por su categoría")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Productos encontrados"),
			@ApiResponse(responseCode = "404", description = "Categoría no encontrada") })
	@GetMapping("/categoria")
	public ResponseEntity<?> findByCategoria(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam int catId) {
		return ResponseEntity.ok(productoService.findByCategoria(catId, page, size));
	}
	
	/**
	 * Método para obtener productos por marca.
	 *
	 * @param marId ID de la marca a buscar.
	 * @param page Número de página.
	 * @param size Cantidad de elementos por página.
	 * @return respuesta con los productos encontrados por marca.
	 */
	@Operation(summary = "Buscar producto por marca", description = "Busca productos por su marca")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Productos encontrados"),
			@ApiResponse(responseCode = "404", description = "Marca no encontrada") })
	@GetMapping("/marca")
	public ResponseEntity<?> findByMarca(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam int marId) {
		return ResponseEntity.ok(productoService.findByMarca(marId, page, size));
	}
	
	/**
	 * Método para obtener productos por rango de precio de compra.
	 *
	 * @param precioMinimo Precio mínimo.
	 * @param precioMaximo Precio máximo.
	 * @param page         Número de página.
	 * @param size         Cantidad de elementos por página.
	 * @return respuesta con los productos encontrados.
	 */
	@Operation(summary = "Buscar producto por rango de precio de compra", description = "Busca productos por su rango de precio de compra")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Productos encontrados"),
			@ApiResponse(responseCode = "404", description = "Rango de precio no encontrado") })
	@GetMapping("/rango-precio-compra")
	public ResponseEntity<?> getByRangoPrecioCompra(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam Double precioMinimo,
			@RequestParam Double precioMaximo) {
		return ResponseEntity.ok(productoService.findByRangoPrecioCompra(precioMinimo, precioMaximo, page, size));
	}
	
	/**
	 * Método para obtener productos por rango de precio de venta.
	 *
	 * @param precioMinimo Precio mínimo.
	 * @param precioMaximo Precio máximo.
	 * @param page         Número de página.
	 * @param size         Cantidad de elementos por página.
	 * @return respuesta con los productos encontrados.
	 */
	@Operation(summary = "Buscar producto por rango de precio de venta", description = "Busca productos por su rango de precio de venta")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Productos encontrados"),
			@ApiResponse(responseCode = "404", description = "Rango de precio no encontrado") })
	@GetMapping("/rango-precio-venta")
	public ResponseEntity<?> getByRangoPrecioVenta(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam Double precioMinimo,
			@RequestParam Double precioMaximo) {
		return ResponseEntity.ok(productoService.findByRangoPrecioVenta(precioMinimo, precioMaximo, page, size));
	}
	
	/**
	 * Método para buscar productos por coincidencia parcial del nombre (ignorando
	 * mayúsculas y minúsculas).
	 *
	 * @param nombre Nombre parcial del producto.
	 * @param page   Número de página.
	 * @param size   Cantidad de elementos por página.
	 * @return respuesta con los productos que coincidan.
	 */
	@Operation(summary = "Buscar producto por coincidencia parcial del nombre", description = "Busca productos por coincidencia parcial de su nombre")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Productos encontrados"),
			@ApiResponse(responseCode = "404", description = "Productos no encontrados") })
	@GetMapping("/nombre/contiene")
	public ResponseEntity<?> findByNombreContainingIgnoreCase(@RequestParam String nombre,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		return ResponseEntity.ok(productoService.findByProdNombreContainingIgnoreCase(nombre, page, size));
	}
	
	/**
	 * Método para obtener productos por estado.
	 *
	 * @param estado Estado del producto a buscar.
	 * @param page   Número de página.
	 * @param size   Cantidad de elementos por página.
	 * @return respuesta con los productos encontrados por estado.
	 */
	@Operation(summary = "Buscar producto por estado", description = "Busca productos por su estado")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Productos encontrados"),
			@ApiResponse(responseCode = "404", description = "Estado no encontrado") })
	@GetMapping("/estado")
	public ResponseEntity<?> findByEstado(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam Integer estado) {
		return ResponseEntity.ok(productoService.findByEstado(estado, page, size));
	}
	/**
	 * Método para listar todos los productos paginados.
	 *
	 * @param page Número de página.
	 * @param size Cantidad de elementos por página.
	 * @return respuesta con los productos encontrados.
	 */
	@Operation(summary = "Listar todos los productos paginados", description = "Lista todos los productos en el sistema")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Productos encontrados"),
			@ApiResponse(responseCode = "404", description = "Productos no encontrados") })
	@GetMapping("/all")
	public ResponseEntity<?> findAll(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		return ResponseEntity.ok(productoService.findAll(page, size));
	}	
	/**
	 * Método para obtener un producto por su código.
	 *
	 * @param codigo Código del producto a buscar.
	 * @return respuesta con el producto encontrado.
	 */
	@Operation(summary = "Buscar producto por código", description = "Busca un producto por su código")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Producto encontrado"),
			@ApiResponse(responseCode = "404", description = "Producto no encontrado") })
	@GetMapping("/codigo/{codigo}")
	public ResponseEntity<ProductoDTO> findByCodigo(@PathVariable String codigo) {
        ProductoDTO producto = productoService.findByCodigo(codigo);
        return ResponseEntity.ok(producto);
	}
	/**
	 * Método para obtener productos por prodProduccion y estadoId.
	 *
	 * @param prodProduccion prodProduccion del producto a buscar.
	 * @param estadoId       estadoId del producto a buscar.
	 * @param page           Número de página.
	 * @param size           Cantidad de elementos por página.
	 * @return respuesta con los productos encontrados por prodProduccion y
	 *         estadoId.
	 */
	@Operation(summary = "Buscar producto por prodProduccion y estadoId", description = "Busca productos por su prodProduccion y estadoId")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Productos encontrados"),
			@ApiResponse(responseCode = "404", description = "prodProduccion o estadoId no encontrado") })
	@GetMapping("/prodProduccion")
	public ResponseEntity<?> findByProdProduccionAndEstadoId(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size, @RequestParam boolean prodProduccion,
            @RequestParam Integer estadoId) {
        return ResponseEntity.ok(productoService.findByProdProduccionAndEstadoId(prodProduccion, estadoId, page, size));
            }
	
	
}
