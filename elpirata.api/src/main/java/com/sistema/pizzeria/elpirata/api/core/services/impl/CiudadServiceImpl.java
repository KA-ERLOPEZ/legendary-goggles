package com.sistema.pizzeria.elpirata.api.core.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistema.pizzeria.elpirata.api.core.entities.Ciudad;
import com.sistema.pizzeria.elpirata.api.core.entities.Estado;
import com.sistema.pizzeria.elpirata.api.core.entities.Pais;
import com.sistema.pizzeria.elpirata.api.core.exceptions.CustomException;
import com.sistema.pizzeria.elpirata.api.core.mappers.CiudadMapper;
import com.sistema.pizzeria.elpirata.api.core.repositories.CiudadRepository;
import com.sistema.pizzeria.elpirata.api.core.repositories.EstadoRepository;
import com.sistema.pizzeria.elpirata.api.core.repositories.PaisRepository;
import com.sistema.pizzeria.elpirata.api.core.services.CiudadService;
import com.sistema.pizzeria.elpirata.api.web.dto.CiudadDTO;

@Service
@Transactional
public class CiudadServiceImpl implements CiudadService {

	private final CiudadRepository repository;
	private final CiudadMapper mapper;
	private final EstadoRepository estadoRepository;
	private final PaisRepository paisRepository;

	public CiudadServiceImpl(CiudadRepository repository, CiudadMapper mapper, EstadoRepository estadoRepository,
			PaisRepository paisRepository) {
		this.repository = repository;
		this.mapper = mapper;
		this.estadoRepository = estadoRepository;
		this.paisRepository = paisRepository;
	}

	@Override
	public List<CiudadDTO> findAll() {
		return mapper.toDTOList(repository.findAll());
	}

	@Override
	public CiudadDTO findById(Long id) {
		Ciudad ciudad = repository.findById(id)
				.orElseThrow(() -> new CustomException("Ciudad con ID " + id + " no encontrada."));
		return mapper.toDTO(ciudad);
	}

	@Override
	public CiudadDTO save(CiudadDTO dto) {
		if (repository.existsByCiuNombre(dto.getCiuNombre())) {
			throw new CustomException("La ciudad con nombre '" + dto.getCiuNombre() + "' ya existe.");
		}

		Estado estado = estadoRepository.findById(dto.getEstadoId()).orElseThrow(
				() -> new CustomException("Estado con ID " + dto.getEstadoId() + " no encontrado."));

		Pais pais = paisRepository.findById(dto.getPaisId())
				.orElseThrow(() -> new CustomException("País con ID " + dto.getPaisId() + " no encontrado."));

		Ciudad ciudad = mapper.toEntity(dto);
		ciudad.setEstado(estado);
		ciudad.setPais(pais);

		Ciudad savedCiudad = repository.save(ciudad);
		return mapper.toDTO(savedCiudad);
	}

	@Override
	public CiudadDTO update(CiudadDTO dto) {
		Ciudad existingCiudad = repository.findById(dto.getCiuId())
				.orElseThrow(() -> new CustomException("Ciudad con ID " + dto.getCiuId() + " no encontrada."));

		if (!existingCiudad.getCiuNombre().equals(dto.getCiuNombre())
				&& repository.existsByCiuNombre(dto.getCiuNombre())) {
			throw new CustomException("La ciudad con nombre '" + dto.getCiuNombre() + "' ya existe.");
		}

		Estado estado = estadoRepository.findById(dto.getEstadoId()).orElseThrow(
				() -> new CustomException("Estado con ID " + dto.getEstadoId() + " no encontrado."));

		Pais pais = paisRepository.findById(dto.getPaisId())
				.orElseThrow(() -> new CustomException("País con ID " + dto.getPaisId() + " no encontrado."));

		existingCiudad.setCiuNombre(dto.getCiuNombre());
		existingCiudad.setEstado(estado);
		existingCiudad.setPais(pais);

		Ciudad updatedCiudad = repository.save(existingCiudad);
		return mapper.toDTO(updatedCiudad);
	}

	@Override
	public void deleteById(Long id) {
		if (!repository.existsById(id)) {
			throw new CustomException("Ciudad con ID " + id + " no encontrada.");
		}
		repository.deleteById(id);
	}

	@Override
	public List<CiudadDTO> findAllByPaisId(Long paisId) {
		return mapper.toDTOList(repository.findAllByPaisPaisId(paisId));
    }

	@Override
	public List<CiudadDTO> findAllByEstadoId(Long estadoId) {
		
		return repository.findAllByEstadoEstadoId(estadoId).stream().map(mapper::toDTO).toList();
	}

	
}
