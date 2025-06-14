package com.sistema.pizzeria.elpirata.api.core.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sistema.pizzeria.elpirata.api.core.entities.Sabor;
import com.sistema.pizzeria.elpirata.api.core.exceptions.CustomException;
import com.sistema.pizzeria.elpirata.api.core.mappers.SaborMapper;
import com.sistema.pizzeria.elpirata.api.core.repositories.SaborRepository;
import com.sistema.pizzeria.elpirata.api.core.services.SaborService;
import com.sistema.pizzeria.elpirata.api.web.dto.SaborDTO;

@Service
public class SaborSeviceImpl implements SaborService {


	private final SaborRepository saborRepository;
	private final SaborMapper saborMapper;
	
	public SaborSeviceImpl(SaborRepository saborRepository, SaborMapper saborMapper) {
		this.saborRepository = saborRepository;
		this.saborMapper = saborMapper;
	}

	/**
	 * Method to list all Sabores.
	 * 
	 * @return a list of Sabores
	 */
	@Override
	public List<SaborDTO> findAll() {
		
		return saborRepository.findAll().stream().map(saborMapper::toDTO).toList();
	}

	/**
	 * Method for find a Sabor by id.
	 * 
	 * @param id the id of the Sabor
	 * @return the Sabor
	 */
	@Override
	public SaborDTO findById(Long id) {
		
		SaborDTO saborDTO = saborMapper.toDTO(saborRepository.findById(id).orElse(null));
		if (saborDTO == null) {
			throw new CustomException("Sabor no encontrado con id: " + id);
		}
		return saborDTO;
	}

	/**
	 * Method to save a Sabor.
	 * 
	 * @param dto the SaborDTO
	 * @return the saved Sabor
	 */
	@Override
	public SaborDTO save(SaborDTO dto) {
		
		if (dto.getSabId() != 0) {
			throw new CustomException("El id no debe ser enviado al crear un nuevo Sabor");
		}
		if (saborRepository.existsBySabNombre(dto.getSabNombre())) {
			throw new CustomException("El nombre del Sabor ya existe");
		}
		
		return saborMapper.toDTO(saborRepository.save(saborMapper.toEntity(dto)));
	}

	@Override
	public SaborDTO update(SaborDTO dto) {
		if (dto.getSabId() == 0) {
			throw new CustomException("El id es requerido para actualizar un Sabor");
		}
		if (!saborRepository.existsById(dto.getSabId())) {
			throw new CustomException("El Sabor no existe");
		}
		if (saborRepository.existsBySabNombre(dto.getSabNombre())) {
			throw new CustomException("El nombre del Sabor ya existe");
		}
		
		return saborMapper.toDTO(saborRepository.save(saborMapper.toEntity(dto)));
	}

	@Override
	public void deleteById(Long id) {
		if (!saborRepository.existsById(id)) {
			throw new CustomException("El Sabor no existe");
		}
		saborRepository.deleteById(id);
		
	}

	@Override
	public SaborDTO findByNombre(String nombre) {
		
		Sabor sabor = saborRepository.findBySabNombre(nombre).orElseThrow(() -> new CustomException("Sabor no encontrado con nombre: " + nombre));
		return saborMapper.toDTO(sabor);
	}

	@Override
	public List<SaborDTO> findAllByEstado(Integer estado) {
		// TODO Auto-generated method stub
		return saborRepository.findAllByEstado_EstadoId(estado).stream().map(saborMapper::toDTO).toList();
	}
	
	
	

}
