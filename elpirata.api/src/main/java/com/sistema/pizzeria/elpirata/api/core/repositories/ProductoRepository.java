package com.sistema.pizzeria.elpirata.api.core.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.pizzeria.elpirata.api.core.entities.Producto;



public interface ProductoRepository extends JpaRepository<Producto, Long> {

	/**
	 * Busca un producto por su nombre.
	 * 
	 * @param nombre Nombre del producto a buscar.
	 * @return Producto encontrado o null si no existe.
	 */
	Optional<Producto> findByProdNombre(String nombre);
	
	/**
	 * Busca productos por su categoria.
	 * 
	 * @param id de laCategoria del producto a buscar.
	 * @return Lista de productos encontrados o una lista vacía si no existen.
	 */
	Page<Producto> findByCategoria_catId(long catId, Pageable pageable);
	
	/**
	 * Lista todos los productos por Marca.
	 * 
	 * @param id de la Marca del producto a buscar.
	 * 
	 * @return Lista de productos encontrados o una lista vacía si no existen.
	 * 
	 */
	Page<Producto> findByMarca_marId(long marId, Pageable pageable);
	
	/**
	 * Buscar productos que esten en un rango de precios compra.
	 * 
	 * @param precioMinimo Precio mínimo del producto a buscar.
	 * @param precioMaximo Precio máximo del producto a buscar.
	 * @return Pagina de productos encontrados o una lista vacía si no existen.
	 */
	Page<Producto> findByProdPrecioCompraBetween(Double precioMinimo, Double precioMaximo, Pageable pageable);
	
	/**
	 * Buscar productos que esten en un rango de precio venta.
	 * 
	 * @param precioMinimo Precio mínimo del producto a buscar.
	 * @param precioMaximo Precio máximo del producto a buscar.
	 * @return Lista de productos encontrados o una lista vacía si no existen.
	 */
	Page<Producto> findByProdPrecioVentaBetween(Double precioMinimo, Double precioMaximo, Pageable pageable);
	
	/**
	 * Buscar productos por coincidencia parcial de nombre.
	 * 
     * @param nombre Nombre del producto a buscar.
     * @param pageable Información de paginación.
     * @return Lista de productos encontrados o una lista vacía si no existen.
	 */
	Page<Producto> findByProdNombreContainingIgnoreCase(String nombre, Pageable pageable);
	
	/**
	 * Buscar producto por codigo.
	 * 
	 * @param codigo Codigo del producto a buscar.
	 * @return Optional<Producto> encontrado o null si no existe.
	 */
	
	Optional<Producto> findByProdCodigo(String codigo);
	
	/**
	 * Listar productos por estado.
	 * 
	 * @param estado Estado del producto a buscar.
	 * @return Lista de productos encontrados o una lista vacía si no existen.
	 */
	
	Page<Producto> findByEstado_EstadoId(long estado, Pageable pageable);

	/**
	 * Listar productos por prodProduccion y e estadoId.
	 * 
	 * @param prodProduccion prodProduccion del producto a buscar.
	 * @param estadoId       estadoId del producto a buscar.
	 * @return Lista de productos encontrados o una lista vacía si no existen.
	 */
	 Page<Producto> findByProdProduccionAndEstado_EstadoId(boolean prodProduccion, long estadoId, Pageable pageable);
}
