package com.sistema.pizzeria.elpirata.api.core.services.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.sistema.pizzeria.elpirata.api.core.exceptions.CustomException;
import com.sistema.pizzeria.elpirata.api.core.mappers.CategoriaMapper;
import com.sistema.pizzeria.elpirata.api.core.repositories.CategoriaRepository;
import com.sistema.pizzeria.elpirata.api.core.services.CategoriaService;
import com.sistema.pizzeria.elpirata.api.web.dto.CategoriaDTO;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	
	private final CategoriaRepository categoriaRepository;
	
	private final CategoriaMapper categoriaMapper;
	
	/**
	 * Constructor de la clase CategoriaServiceImpl
	 * 
	 * @param categoriaRepository
	 * @param categoriaMapper
	 */
	public CategoriaServiceImpl(CategoriaRepository categoriaRepository, CategoriaMapper categoriaMapper) {
		this.categoriaRepository = categoriaRepository;
		this.categoriaMapper = categoriaMapper;
	}
	
	@Override
	public List<CategoriaDTO> findAll() {
		
		return categoriaRepository.findAll().stream().map(categoriaMapper::toDTO).toList();
	}

	@Override
	public CategoriaDTO findById(Long id) {
		
		CategoriaDTO categoriaDTO = categoriaMapper.toDTO(
				categoriaRepository.findById(id).orElseThrow(() ->new CustomException("Categoria no encontrada con id: " + id)));
		
		return categoriaDTO;
	}

	@Override
	public CategoriaDTO save(CategoriaDTO dto) {
		if (dto.getCatNombre() == null || dto.getCatNombre().isEmpty()) {
			throw new CustomException("El nombre de la categoria no puede estar vacio");
		}
		if (dto.getEstadoId() <= 0) {
			throw new CustomException("El id del estado no puede ser menor o igual a cero");
		}
		
		return categoriaMapper.toDTO(categoriaRepository.save(categoriaMapper.toEntity(dto)));
	}

	@Override
	public CategoriaDTO update(CategoriaDTO dto) {
		if (Objects.isNull(dto)) {
			throw new CustomException("El objeto CategoriaDTO no puede ser nulo");
		}
		CategoriaDTO existingCategoria = findById(dto.getCatId());
		
		if (dto.getCatNombre() == existingCategoria.getCatNombre() && dto.getCatId() != existingCategoria.getCatId()) {
			throw new CustomException("El nombre de la categoria ya existe");
		}
		return categoriaMapper.toDTO(categoriaRepository.save(categoriaMapper.toEntity(dto)));
	}

	@Override
	public void deleteById(Long id) {
		
		if (id == null) {
			throw new CustomException("El id no puede ser nulo");
		}

		CategoriaDTO existingCategoria = findById(id);

		if (existingCategoria == null) {
			throw new CustomException("No se encontro la categoria con id: " + id);
		}

		categoriaRepository.deleteById(id);
	}

	@Override
	public CategoriaDTO findByCatNombre(String nombre) {
		
		if (nombre == null || nombre.isEmpty()) {
			throw new CustomException("El nombre de la categoria no puede estar vacio");
		}

		CategoriaDTO categoriaDTO = categoriaMapper.toDTO(categoriaRepository.findByCatNombre(nombre)
				.orElseThrow(() -> new CustomException("Categoria no encontrada con nombre: " + nombre)));

		return categoriaDTO;
	}

	@Override
	public List<CategoriaDTO> findByEstado_EstadoId(long id) {
		if (id <= 0) {
			throw new CustomException("El id del estado no puede ser menor o igual a cero");
		}

		return categoriaRepository.findByEstado_EstadoId(id).stream().map(categoriaMapper::toDTO).toList();
	}

}
