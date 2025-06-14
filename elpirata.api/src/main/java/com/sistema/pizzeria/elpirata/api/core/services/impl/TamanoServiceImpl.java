package com.sistema.pizzeria.elpirata.api.core.services.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.sistema.pizzeria.elpirata.api.core.exceptions.CustomException;
import com.sistema.pizzeria.elpirata.api.core.mappers.TamanoMapper;
import com.sistema.pizzeria.elpirata.api.core.repositories.TamanoRepsitory;
import com.sistema.pizzeria.elpirata.api.core.services.TamanoService;
import com.sistema.pizzeria.elpirata.api.web.dto.TamanoDTO;

@Service
public class TamanoServiceImpl implements TamanoService {

	private final TamanoRepsitory tamanoRepsitory;
	private final TamanoMapper tamanoMapper;
	
	public TamanoServiceImpl(TamanoRepsitory tamanoRepsitory, TamanoMapper tamanoMapper) {
		this.tamanoRepsitory = tamanoRepsitory;
		this.tamanoMapper = tamanoMapper;
	}
	
	
	@Override
	public List<TamanoDTO> findAll() {

		return tamanoRepsitory.findAll().stream()
                .map(tamanoMapper::toDTO)
                .toList();
	}

	@Override
	public TamanoDTO findById(Long id) {
		// TODO Auto-generated method stub
		return tamanoRepsitory.findById(id).map(tamanoMapper::toDTO).orElseThrow(() -> new CustomException("Tamano not found"));
	}

	@Override
	public TamanoDTO save(TamanoDTO dto) {
		// Check if the dto is null
		if (dto == null) {
			throw new CustomException("Tamano cannot be null");
		}
		// Check if the tamano already exists
		if (tamanoRepsitory.findByTamDescripcion(dto.getTamDescripcion()).isPresent()) {
			throw new CustomException("Tamano already exists");
		}
		
		
		
		return tamanoMapper.toDTO(tamanoRepsitory.save(tamanoMapper.toEntity(dto)));
	}

	@Override
	public TamanoDTO update(TamanoDTO dto) {
		
		// Check if the dto is null
		if (dto == null) {
			throw new CustomException("Tamano cannot be null");
		}
		TamanoDTO existingTamano = findById(dto.getTamId());
		
		// Check if the tamano already exists
		if (Objects.nonNull(existingTamano) && existingTamano.getTamId() != dto.getTamId() ) {
			throw new CustomException("Tamano already exists");
		}
		
		
		return tamanoMapper.toDTO(tamanoRepsitory.save(tamanoMapper.toEntity(dto)));
	}

	@Override
	public void deleteById(Long id) {
		findById(id);
		tamanoRepsitory.deleteById(id);

	}

	@Override
	public TamanoDTO findByTamNombre(String tamNombre) {
		// TODO Auto-generated method stub
		return tamanoRepsitory.findByTamDescripcion(tamNombre).map(tamanoMapper::toDTO)
				.orElseThrow(() -> new CustomException("Tamano not found"));
	}

	@Override
	public List<TamanoDTO> findByEstado_estadoId(Integer estadoId) {
		// TODO Auto-generated method stub
		return tamanoRepsitory.findByEstado_estadoId(estadoId).stream().map(tamanoMapper::toDTO).toList();
	}

}
