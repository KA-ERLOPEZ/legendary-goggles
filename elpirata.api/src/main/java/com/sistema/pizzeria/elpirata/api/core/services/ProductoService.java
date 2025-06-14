package com.sistema.pizzeria.elpirata.api.core.services;

import com.sistema.pizzeria.elpirata.api.core.services.generics.GenericService;
import com.sistema.pizzeria.elpirata.api.web.dto.PageResponseDTO;
import com.sistema.pizzeria.elpirata.api.web.dto.ProductoDTO;

public interface ProductoService extends GenericService<ProductoDTO, Long> {

    /**
     * Método para obtener un producto por su nombre.
     *
     * @param nombre Nombre del producto a buscar.
     * @return ProductoDTO que representa el producto encontrado.
     */
    ProductoDTO findByNombre(String nombre);

    /**
     * Método para obtener productos por categoría.
     *
     * @param categoria Nombre de la categoría a buscar.
     * @param page Número de página.
     * @param size Cantidad de elementos por página.
     * @return PageResponse con los productos encontrados por categoría.
     */
    PageResponseDTO<ProductoDTO> findByCategoria(long catId, int page, int size);

    /**
     * Método para obtener productos por marca.
     *
     * @param marca Nombre de la marca a buscar.
     * @param page Número de página.
     * @param size Cantidad de elementos por página.
     * @return PageResponse con los productos encontrados por marca.
     */
    PageResponseDTO<ProductoDTO> findByMarca(long marId, int page, int size);

    /**
     * Método para obtener productos por rango de precio de compra.
     *
     * @param precioMinimo Precio mínimo.
     * @param precioMaximo Precio máximo.
     * @param page Número de página.
     * @param size Cantidad de elementos por página.
     * @return PageResponse con los productos encontrados.
     */
    PageResponseDTO<ProductoDTO> findByRangoPrecioCompra(Double precioMinimo, Double precioMaximo, int page, int size);

    /**
     * Método para obtener productos por rango de precio de venta.
     *
     * @param precioMinimo Precio mínimo.
     * @param precioMaximo Precio máximo.
     * @param page Número de página.
     * @param size Cantidad de elementos por página.
     * @return PageResponse con los productos encontrados.
     */
    PageResponseDTO<ProductoDTO> findByRangoPrecioVenta(Double precioMinimo, Double precioMaximo, int page, int size);

    /**
     * Método para buscar productos por coincidencia parcial del nombre (ignorando mayúsculas y minúsculas).
     *
     * @param nombre Nombre parcial del producto.
     * @param page Número de página.
     * @param size Cantidad de elementos por página.
     * @return PageResponse con los productos que coincidan.
     */
    PageResponseDTO<ProductoDTO> findByProdNombreContainingIgnoreCase(String nombre, int page, int size);

    /**
     * Método para obtener un producto por su código.
     *
     * @param codigo Código del producto a buscar.
     * @return ProductoDTO que representa el producto encontrado.
     */
    ProductoDTO findByCodigo(String codigo);

    /**
     * Método para listar todos los productos por estado.
     *
     * @param estado ID del estado a buscar.
     * @param page Número de página.
     * @param size Cantidad de elementos por página.
     * @return PageResponse con los productos encontrados por estado.
     */
    PageResponseDTO<ProductoDTO> findByEstado(long estado, int page, int size);

    /**
     * Método para listar todos los productos paginados.
     *
     * @param page Número de página.
     * @param size Cantidad de elementos por página.
     * @return PageResponse con los productos encontrados.
     */
    PageResponseDTO<ProductoDTO> findAll(int page, int size);
    
	/**
	 * Método para listar todos los productos por producción y estado.
	 *
	 * @param prodProduccion Indica si el producto está en producción.
	 * @param estadoId       ID del estado a buscar.
	 * @param page           Número de página.
	 * @param size           Cantidad de elementos por página.
	 * @return PageResponse con los productos encontrados por producción y estado.
	 */
     PageResponseDTO<ProductoDTO> findByProdProduccionAndEstadoId(boolean prodProduccion, long estadoId, int page, int size);
}
