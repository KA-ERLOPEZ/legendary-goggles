package com.sistema.pizzeria.elpirata.api.core.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sistema.pizzeria.elpirata.api.core.exceptions.CustomException;
import com.sistema.pizzeria.elpirata.api.core.mappers.ConceptoMapper;
import com.sistema.pizzeria.elpirata.api.core.repositories.ConceptoRepository;
import com.sistema.pizzeria.elpirata.api.core.services.ConceptoService;
import com.sistema.pizzeria.elpirata.api.web.dto.ConceptoDTO;

@Service
public class ConceptoServiceImpl implements ConceptoService {

	// Aquí se inyectarían los repositorios y mappers necesarios
	private final ConceptoRepository conceptoRepository;
	private final ConceptoMapper conceptoMapper;
	
	public ConceptoServiceImpl(ConceptoRepository conceptoRepository, ConceptoMapper conceptoMapper) {
		this.conceptoRepository = conceptoRepository;
		this.conceptoMapper = conceptoMapper;
	}

	@Override
	public List<ConceptoDTO> findAll() {
		// TODO Auto-generated method stub
		return conceptoRepository.findAll().stream().map(conceptoMapper::toDTO).toList();
	}

	@Override
	public ConceptoDTO findById(Long id) {
		
		return conceptoRepository.findById(id).map(conceptoMapper::toDTO)
				.orElseThrow(() -> new CustomException("Concepto no encontrado con id: " + id));
	}

	@Override
	public ConceptoDTO save(ConceptoDTO dto) {
		
		if (conceptoRepository.existsByNombre(dto.getNombre())) {
			throw new CustomException("Ya existe un concepto con el nombre: " + dto.getNombre());
		}
		

		return conceptoMapper.toDTO(conceptoRepository.save(conceptoMapper.toEntity(dto)));
	}

	@Override
	public ConceptoDTO update(ConceptoDTO dto) {
		
		ConceptoDTO existingConcepto = conceptoRepository.findByNombre(dto.getNombre()).map(conceptoMapper:: toDTO).orElse(null);
		
		if (existingConcepto != null && existingConcepto.getId() == dto.getId()) {
			throw new CustomException("Ya existe un concepto con el nombre: " + dto.getNombre());
		}
		
	
		
		return conceptoMapper.toDTO(conceptoRepository.save(conceptoMapper.toEntity(dto)));
	}

	@Override
	public void deleteById(Long id) {
		
		if (!conceptoRepository.existsById(id)) {
			throw new CustomException("Concepto no encontrado con id: " + id);
		}

		conceptoRepository.deleteById(id);
	}

	@Override
	public ConceptoDTO getByNombre(String nombre) {
		
		return conceptoRepository.findByNombre(nombre).map(conceptoMapper::toDTO)
				.orElseThrow(() -> new CustomException("Concepto no encontrado con nombre: " + nombre));
	}

	@Override
	public List<ConceptoDTO> getByActivoTrue() {
		return conceptoRepository.findByActivoTrue().stream().map(conceptoMapper::toDTO).toList();
	}

	@Override
	public List<ConceptoDTO> getByDominioIdAndActivoTrue(long dominioId) {
		
		return conceptoRepository.findByDominioDominioIdAndActivoTrue(dominioId).stream().map(conceptoMapper::toDTO)
				.toList();
	}

}
