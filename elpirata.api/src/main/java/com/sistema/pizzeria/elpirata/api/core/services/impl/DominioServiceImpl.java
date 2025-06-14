package com.sistema.pizzeria.elpirata.api.core.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sistema.pizzeria.elpirata.api.core.entities.Dominio;
import com.sistema.pizzeria.elpirata.api.core.exceptions.CustomException;
import com.sistema.pizzeria.elpirata.api.core.mappers.DominioMapper;
import com.sistema.pizzeria.elpirata.api.core.repositories.DominioRepository;
import com.sistema.pizzeria.elpirata.api.core.services.DominioService;
import com.sistema.pizzeria.elpirata.api.web.dto.DominioDTO;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class DominioServiceImpl implements DominioService {

	private final DominioRepository dominioRepository;
	private final DominioMapper dominioMapper;

	public DominioServiceImpl(DominioRepository dominioRepository, DominioMapper dominioMapper) {
		this.dominioRepository = dominioRepository;
		this.dominioMapper = dominioMapper;
	}

	@Override
	public List<DominioDTO> findAll() {
		// TODO Auto-generated method stub
		List<Dominio> dominios = dominioRepository.findAll();
		if (dominios.isEmpty()) {
			throw new CustomException("No se encontraron dominios.");
		}
		return dominioMapper.toDTOList(dominios);
	}

	@Override
	public DominioDTO findById(Long id) {
		// TODO Auto-generated method stub
		Dominio dominio = dominioRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Dominio con ID " + id + " no encontrado."));
		return dominioMapper.toDTO(dominio);
	}

	@Override
	public DominioDTO save(DominioDTO dto) {
		// TODO Auto-generated method stub
		if (dominioRepository.existsByDominioCodigo(dto.getDominioCodigo())) {
			throw new CustomException("El dominio con el código " + dto.getDominioCodigo() + " ya existe.");
		}
		Dominio dominio = dominioMapper.toEntity(dto);
		Dominio savedDominio = dominioRepository.save(dominio);
		return dominioMapper.toDTO(savedDominio);
	}

	@Override
	public DominioDTO update(DominioDTO dto) {
		Dominio existingDominio = dominioRepository.findById(dto.getDominioId()).orElseThrow(
				() -> new EntityNotFoundException("Dominio con ID " + dto.getDominioId() + " no encontrado."));

		// Validar que el nuevo código no esté ya en uso por otro dominio
		if (dominioRepository.existsByDominioCodigo(dto.getDominioCodigo())
				&& !existingDominio.getDominioCodigo().equals(dto.getDominioCodigo())) {
			throw new CustomException("El código del dominio '" + dto.getDominioCodigo() + "' ya está en uso.");
		}

		// Actualizar los campos del dominio existente
		existingDominio.setDominioCodigo(dto.getDominioCodigo());
		existingDominio.setDominioNombre(dto.getDominioNombre());
		existingDominio.setDominioEnabled(dto.isDominioEnabled());

		// Guardar el dominio actualizado y devolverlo como DTO
		Dominio updatedDominio = dominioRepository.save(existingDominio);
		return dominioMapper.toDTO(updatedDominio);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		if (!dominioRepository.existsById(id)) {
			throw new EntityNotFoundException("Dominio con ID " + id + " no encontrado.");
		}
		dominioRepository.deleteById(id);

	}

	@Override
	public DominioDTO findByCodigo(String codigo) {
		// TODO Auto-generated method stub
		Dominio dominio = dominioRepository.findByDominioCodigo(codigo)
				.orElseThrow(() -> new EntityNotFoundException("Dominio con ID " + codigo + " no encontrado."));
		return dominioMapper.toDTO(dominio);
	}

}
