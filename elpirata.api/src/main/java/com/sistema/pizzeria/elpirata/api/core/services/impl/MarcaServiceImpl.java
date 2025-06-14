package com.sistema.pizzeria.elpirata.api.core.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sistema.pizzeria.elpirata.api.core.exceptions.CustomException;
import com.sistema.pizzeria.elpirata.api.core.mappers.MarcaMapper;
import com.sistema.pizzeria.elpirata.api.core.repositories.MarcaRepository;
import com.sistema.pizzeria.elpirata.api.core.services.MarcaService;
import com.sistema.pizzeria.elpirata.api.web.dto.MarcaDTO;

@Service
public class MarcaServiceImpl implements MarcaService {

	private final MarcaRepository marcaRepository;
	private final MarcaMapper marcaMapper;
	
	/**
	 * Constructor de la clase MarcaServiceImpl.
	 * 
	 * @param marcaRepository el repositorio de marcas
	 * @param marcaMapper     el mapeador de marcas
	 */
	public MarcaServiceImpl(MarcaRepository marcaRepository, MarcaMapper marcaMapper) {
		this.marcaRepository = marcaRepository;
		this.marcaMapper = marcaMapper;
	}
	
	/**
	 * Método que obtiene todas las marcas.
	 * 
	 * @return una lista de objetos MarcaDTO
	 */
	@Override
	public List<MarcaDTO> findAll() {
		
		return marcaRepository.findAll().stream().map(marcaMapper::toDTO).toList();
	}

	/**
	 * Método que obtiene una marca por su ID.
	 * 
	 * @param id el ID de la marca
	 * @return un objeto MarcaDTO
	 */
	@Override
	public MarcaDTO findById(Long id) {
		
		return marcaRepository.findById(id).map(marcaMapper::toDTO).orElseThrow(() -> new CustomException("Marca no encontrada"));
	}

	/**
	 * Método que guarda una nueva marca.
	 * 
	 * @param dto el objeto MarcaDTO a guardar
	 * @return el objeto MarcaDTO guardado
	 */
	@Override
	public MarcaDTO save(MarcaDTO dto) {
		if (marcaRepository.existsByMarNombre(dto.getMarNombre())) {
			throw new CustomException("La marca ya existe");
		}
		if (dto.getMarId() == 0) {
			throw new CustomException("El ID no debe ser proporcionado");
		}
		if (dto.getMarNombre() == null || dto.getMarNombre().isEmpty()) {
			throw new CustomException("El nombre de la marca no puede estar vacío");
		}
		return marcaMapper.toDTO(marcaRepository.save(marcaMapper.toEntity(dto)));
	}

	/**
	 * Método que actualiza una marca existente.
	 * 
	 * @param dto el objeto MarcaDTO a actualizar
	 * @return el objeto MarcaDTO actualizado
	 */
	@Override
	public MarcaDTO update(MarcaDTO dto) {
		
		MarcaDTO existingMarca = findById(dto.getMarId());
		if (existingMarca == null) {
			throw new CustomException("Marca no encontrada");
		}
		
		if (dto.getMarNombre() == null || dto.getMarNombre().isEmpty()) {
			throw new CustomException("El nombre de la marca no puede estar vacío");
		}
		if (existingMarca.getMarNombre().equals(dto.getMarNombre()) && dto.getMarId() != existingMarca.getMarId()) {
			throw new CustomException("La marca ya existe");
		}
		return marcaMapper.toDTO(marcaRepository.save(marcaMapper.toEntity(dto)));
	}

	/**
	 * Método que elimina una marca por su ID.
	 * 
	 * @param id el ID de la marca
	 */
	@Override
	public void deleteById(Long id) {
		MarcaDTO existingMarca = findById(id);
		if (existingMarca == null) {
			throw new CustomException("Marca no encontrada");
		}
		marcaRepository.deleteById(id);

	}

	@Override
	public MarcaDTO findByMarNombre(String marNombre) {
		
		return marcaRepository.findByMarNombre(marNombre).map(marcaMapper::toDTO)
				.orElseThrow(() -> new CustomException("Marca no encontrada"));
	}
	
	/**
	 * Método que verifica si una marca existe por su nombre.
	 * 
	 * @param marNombre el nombre de la marca
	 * @return true si la marca existe, false en caso contrario
	 */

	@Override
	public boolean existsByMarNombre(String marNombre) {
		
		return marcaRepository.existsByMarNombre(marNombre);
	}

	/**
	 * Método que verifica si una marca existe por su ID.
	 * 
	 * @param id el ID de la marca
	 * @return true si la marca existe, false en caso contrario
	 */
	@Override
	public List<MarcaDTO> findByEstadoId(Integer estadoId) {
		
		return marcaRepository.findByEstado_EstadoId(estadoId).stream().map(marcaMapper::toDTO).toList();
	}

}
