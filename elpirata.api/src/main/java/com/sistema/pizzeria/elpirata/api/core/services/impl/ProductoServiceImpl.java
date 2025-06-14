package com.sistema.pizzeria.elpirata.api.core.services.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sistema.pizzeria.elpirata.api.core.entities.Producto;
import com.sistema.pizzeria.elpirata.api.core.exceptions.CustomException;
import com.sistema.pizzeria.elpirata.api.core.mappers.ProductoMapper;
import com.sistema.pizzeria.elpirata.api.core.repositories.CategoriaRepository;
import com.sistema.pizzeria.elpirata.api.core.repositories.MarcaRepository;
import com.sistema.pizzeria.elpirata.api.core.repositories.ProductoRepository;
import com.sistema.pizzeria.elpirata.api.core.services.ProductoService;
import com.sistema.pizzeria.elpirata.api.web.dto.PageResponseDTO;
import com.sistema.pizzeria.elpirata.api.web.dto.ProductoDTO;

@Service
public class ProductoServiceImpl implements ProductoService {
	
	private final ProductoRepository productoRepository;
	private final CategoriaRepository categoriaRepository;
	private final MarcaRepository marcaRepository;
	private final ProductoMapper productoMapper;
	
	/**
	 * Constructor de la clase ProductoServiceImpl.
	 * 
	 * @param productoRepository Repositorio de productos.
	 * @param productoMapper     Mapeador de productos.
	 */
	public ProductoServiceImpl(ProductoRepository productoRepository, ProductoMapper productoMapper,
			CategoriaRepository categoriaRepository, MarcaRepository marcaRepository) {
		this.productoRepository = productoRepository; // Inicializar con la implementación correspondiente
		this.productoMapper = productoMapper;	 // Inicializar con la implementación correspondiente
		this.categoriaRepository = categoriaRepository; // Inicializar con la implementación correspondiente
		this.marcaRepository = marcaRepository; // Inicializar con la implementación correspondiente
	}

	/**
	 * Método para obtener todos los productos.
	 * 
	 * @return Lista de productos.
	 */
	@Override
	public List<ProductoDTO> findAll() {
		
		return productoRepository.findAll().stream().map(productoMapper::toDTO).toList();
	}
	
	@Override
	public ProductoDTO findById(Long id) {
		
		return productoRepository.findById(id).map(productoMapper::toDTO).orElseThrow(()-> new CustomException("Producto no encontrado"));
	}

	@Override
	public ProductoDTO save(ProductoDTO dto) {
		// Validar si el producto ya existe
		if (productoRepository.findByProdNombre(dto.getProdNombre()).isPresent()) {
			throw new CustomException("El producto ya existe");
		}
		
		return productoMapper.toDTO(productoRepository.save(productoMapper.toEntity(dto)));
	}

	@Override
	public ProductoDTO update(ProductoDTO dto) {
		
		// Validar si el producto existe
		if (!productoRepository.findById(dto.getProdId()).isPresent()) {
			throw new CustomException("El producto no existe");
		}
		// Validar si el nombre del producto ya existe y si pertenece a otro producto
		ProductoDTO productoExistente = productoRepository.findByProdNombre(dto.getProdNombre())
				.map(productoMapper::toDTO).orElse(null);
		if (Objects.nonNull(productoExistente) && !(productoExistente.getProdId() == dto.getProdId())) {
			throw new CustomException("El nombre del producto ya existe");
		}
		
		
		return productoMapper.toDTO(productoRepository.save(productoMapper.toEntity(dto)));
	}

	@Override
	public void deleteById(Long id) {
		
		// Validar si el producto existe
		if (!productoRepository.findById(id).isPresent()) {
			throw new CustomException("El producto no existe");
		}

		// Eliminar el producto
		productoRepository.deleteById(id);

		// Validar si el producto fue eliminado
		if (productoRepository.findById(id).isPresent()) {
			throw new CustomException("Error al eliminar el producto");
		}

	}

	@Override
	public ProductoDTO findByNombre(String nombre) {
		
		return productoRepository.findByProdNombre(nombre).map(productoMapper::toDTO)
				.orElseThrow(() -> new CustomException("Producto no encontrado"));
	}

	@Override
	public PageResponseDTO<ProductoDTO> findByCategoria(long catId, int page, int size) {
		
		// Validar si la categoría existe
		if (!categoriaRepository.existsById(catId)) {
			throw new CustomException("La categoría no existe");
		}
		//Crear un objeto Pageable
		Pageable pageable =  PageRequest.of(page, size);
		
		// Obtener los productos por categoría
		Page<Producto> productos = productoRepository.findByCategoria_catId(catId, pageable);
		// Validar si existen productos
		if (productos.isEmpty()) {
			throw new CustomException("No existen productos en esta categoría");
		}
		
		// Mapear los productos a DTO y retornar la respuesta paginada
		return new PageResponseDTO<>(productos.getContent().stream().map(productoMapper::toDTO).toList(),
				productos.getNumber(), productos.getSize(), productos.getTotalElements(), productos.getTotalPages(), productos.isLast());
	}

	@Override
	public PageResponseDTO<ProductoDTO> findByMarca(long marId, int page, int size) {
		// Validar si la marca existe
		if (!marcaRepository.existsById(marId)) {
			throw new CustomException("La marca no existe");
		}
		// Crear un objeto Pageable
		Pageable pageable = PageRequest.of(page, size);
		
		// Obtener los productos por marca
		Page<Producto> productos = productoRepository.findByMarca_marId(marId, pageable);
		
		// Validar si existen productos
		if (productos.isEmpty()) {
			throw new CustomException("No existen productos en esta marca");
		}
		
		// Mapear los productos a DTO y retornar la respuesta paginada
		return new PageResponseDTO<>(productos.getContent().stream().map(productoMapper::toDTO).toList(),
				productos.getNumber(), productos.getSize(), productos.getTotalElements(), productos.getTotalPages(),
				productos.isLast());
	}

	@Override
	public PageResponseDTO<ProductoDTO> findByRangoPrecioCompra(Double precioMinimo, Double precioMaximo, int page,
			int size) {
		
		Pageable pageable = PageRequest.of(page, size);
		
		// Obtener los productos por rango de precio de compra
		Page<Producto> productos = productoRepository.findByProdPrecioCompraBetween(precioMinimo, precioMaximo, pageable);
		
		// Validar si existen productos
		if (productos.isEmpty()) {
			throw new CustomException("No existen productos en este rango de precio");
		}
		
		// Mapear los productos a DTO y retornar la respuesta paginada
		return new PageResponseDTO<>(productos.getContent().stream().map(productoMapper::toDTO).toList(),
				productos.getNumber(), productos.getSize(), productos.getTotalElements(), productos.getTotalPages(),
				productos.isLast());
	}

	@Override
	public PageResponseDTO<ProductoDTO> findByRangoPrecioVenta(Double precioMinimo, Double precioMaximo, int page,
			int size) {
		Pageable pageable = PageRequest.of(page, size);
		
		// Obtener los productos por rango de precio de venta
		Page<Producto> productos = productoRepository.findByProdPrecioVentaBetween(precioMinimo, precioMaximo, pageable);
		
		// Validar si existen productos
		if (productos.isEmpty()) {
			throw new CustomException("No existen productos en este rango de precio");
		}
		
		// Mapear los productos a DTO y retornar la respuesta paginada
		return new PageResponseDTO<>(productos.getContent().stream().map(productoMapper::toDTO).toList(),
				productos.getNumber(), productos.getSize(), productos.getTotalElements(), productos.getTotalPages(),
				productos.isLast());
	}

	@Override
	public PageResponseDTO<ProductoDTO> findByProdNombreContainingIgnoreCase(String nombre, int page, int size) {
		
		Pageable pageable = PageRequest.of(page, size);
		
		// Obtener los productos por nombre
		Page<Producto> productos = productoRepository.findByProdNombreContainingIgnoreCase(nombre, pageable);
		
		// Validar si existen productos
		if (productos.isEmpty()) {
			throw new CustomException("No existen productos con ese nombre");
		}
		
		// Mapear los productos a DTO y retornar la respuesta paginada
		return new PageResponseDTO<>(productos.getContent().stream().map(productoMapper::toDTO).toList(),
				productos.getNumber(), productos.getSize(), productos.getTotalElements(), productos.getTotalPages(),
				productos.isLast());
	}

	@Override
	public ProductoDTO findByCodigo(String codigo) {
		
		// Validar si el producto existe	
		return productoRepository.findByProdCodigo(codigo).map(productoMapper::toDTO)
				.orElseThrow(() -> new CustomException("Producto no encontrado con el código: " + codigo));
	}

	@Override
	public PageResponseDTO<ProductoDTO> findByEstado(long estado, int page, int size) {
		
		// Crear un objeto Pageable
		Pageable pageable = PageRequest.of(page, size);
		
		// Obtener los productos por estado
		Page<Producto> productos = productoRepository.findByEstado_EstadoId(estado, pageable);
		
		// Validar si existen productos
		if (productos.isEmpty()) {
			throw new CustomException("No existen productos en este estado");
		}
		
		// Mapear los productos a DTO y retornar la respuesta paginada
		return new PageResponseDTO<>(productos.getContent().stream().map(productoMapper::toDTO).toList(),
				productos.getNumber(), productos.getSize(), productos.getTotalElements(), productos.getTotalPages(),
				productos.isLast());
	}

	@Override
	public PageResponseDTO<ProductoDTO> findAll(int page, int size) {
		// Crear un objeto Pageable
		Pageable pageable = PageRequest.of(page, size);
		
		// Obtener todos los productos
		Page<Producto> productos = productoRepository.findAll(pageable);
		
		// Validar si existen productos
		if (productos.isEmpty()) {
			throw new CustomException("No existen productos");
		}
		// Mapear los productos a DTO y retornar la respuesta paginada
		return new PageResponseDTO<>(productos.getContent().stream().map(productoMapper::toDTO).toList(),
				productos.getNumber(), productos.getSize(), productos.getTotalElements(), productos.getTotalPages(),
				productos.isLast());
	}

	@Override
	public PageResponseDTO<ProductoDTO> findByProdProduccionAndEstadoId(boolean prodProduccion, long estadoId, int page,
			int size) {
		
		// Crear un objeto Pageable
		Pageable pageable = PageRequest.of(page, size);
		
		// Obtener los productos por producción y estado
		Page<Producto> productos = productoRepository.findByProdProduccionAndEstado_EstadoId(prodProduccion, estadoId,
				pageable);
		
		// Validar si existen productos
		if (productos.isEmpty()) {
			throw new CustomException("No existen productos en este estado");
		}
		
		// Mapear los productos a DTO y retornar la respuesta paginada
		return new PageResponseDTO<>(productos.getContent().stream().map(productoMapper::toDTO).toList(),
				productos.getNumber(), productos.getSize(), productos.getTotalElements(), productos.getTotalPages(),
				productos.isLast());
	}

}
