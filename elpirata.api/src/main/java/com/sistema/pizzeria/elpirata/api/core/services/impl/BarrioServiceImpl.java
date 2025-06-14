package com.sistema.pizzeria.elpirata.api.core.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistema.pizzeria.elpirata.api.core.entities.Barrio;
import com.sistema.pizzeria.elpirata.api.core.entities.Ciudad;
import com.sistema.pizzeria.elpirata.api.core.entities.Estado;
import com.sistema.pizzeria.elpirata.api.core.exceptions.CustomException;
import com.sistema.pizzeria.elpirata.api.core.mappers.BarrioMapper;
import com.sistema.pizzeria.elpirata.api.core.repositories.BarrioRepository;
import com.sistema.pizzeria.elpirata.api.core.repositories.CiudadRepository;
import com.sistema.pizzeria.elpirata.api.core.repositories.EstadoRepository;
import com.sistema.pizzeria.elpirata.api.core.services.BarrioService;
import com.sistema.pizzeria.elpirata.api.web.dto.BarrioDTO;

@Service
@Transactional
public class BarrioServiceImpl implements BarrioService {

	private final BarrioRepository repository;
	private final BarrioMapper mapper;
	private final CiudadRepository ciudadRepository;
	private final EstadoRepository estadoRepository;

	public BarrioServiceImpl(BarrioRepository repository, BarrioMapper mapper, CiudadRepository ciudadRepository,
			EstadoRepository estadoRepository) {
		this.repository = repository;
		this.mapper = mapper;
		this.ciudadRepository = ciudadRepository;
		this.estadoRepository = estadoRepository;
	}

	@Override
	public List<BarrioDTO> findAll() {
		return mapper.toDTOList(repository.findAll());
	}

	@Override
	public BarrioDTO findById(Long id) {
		Barrio barrio = repository.findById(id)
				.orElseThrow(() -> new CustomException("Barrio con ID " + id + " no encontrado."));
		return mapper.toDTO(barrio);
	}

	@Override
	public BarrioDTO save(BarrioDTO dto) {
		if (repository.existsByBarNombreAndCiudad_CiuId(dto.getBarNombre(), dto.getCiudadId())) {
			throw new CustomException("El barrio con nombre '" + dto.getBarNombre() + "' ya existe en la ciudad.");
		}

		Ciudad ciudad = ciudadRepository.findById(dto.getCiudadId())
				.orElseThrow(() -> new CustomException("Ciudad con ID " + dto.getCiudadId() + " no encontrada."));

		Estado estado = estadoRepository.findById(dto.getEstadoId())
				.orElseThrow(() -> new CustomException("Estado con ID " + dto.getEstadoId() + " no encontrado."));

		Barrio barrio = mapper.toEntity(dto);
		barrio.setCiudad(ciudad);
		barrio.setEstado(estado);

		Barrio savedBarrio = repository.save(barrio);
		return mapper.toDTO(savedBarrio);
	}

	@Override
	public BarrioDTO update(BarrioDTO dto) {
		Barrio existingBarrio = repository.findById(dto.getBarId())
				.orElseThrow(() -> new CustomException("Barrio con ID " + dto.getBarId() + " no encontrado."));

		if (!existingBarrio.getBarNombre().equals(dto.getBarNombre())
				&& repository.existsByBarNombreAndCiudad_CiuId(dto.getBarNombre(), dto.getCiudadId())) {
			throw new CustomException("El barrio con nombre '" + dto.getBarNombre() + "' ya existe en la ciudad.");
		}

		Ciudad ciudad = ciudadRepository.findById(dto.getCiudadId())
				.orElseThrow(() -> new CustomException("Ciudad con ID " + dto.getCiudadId() + " no encontrada."));

		Estado estado = estadoRepository.findById(dto.getEstadoId())
				.orElseThrow(() -> new CustomException("Estado con ID " + dto.getEstadoId() + " no encontrado."));

		existingBarrio.setBarNombre(dto.getBarNombre());
		existingBarrio.setCiudad(ciudad);
		existingBarrio.setEstado(estado);

		Barrio updatedBarrio = repository.save(existingBarrio);
		return mapper.toDTO(updatedBarrio);
	}

	@Override
	public void deleteById(Long id) {
		if (!repository.existsById(id)) {
			throw new CustomException("Barrio con ID " + id + " no encontrado.");
		}
		repository.deleteById(id);
	}

	@Override
	public boolean existsByBarNombreAndCiudad_CiuId(String barNombre, long ciuId) {
		return repository.existsByBarNombreAndCiudad_CiuId(barNombre, ciuId);
	}

	@Override
	public List<BarrioDTO> findByCiudad_CiuId(long ciuId) {
		return mapper.toDTOList(repository.findByCiudad_CiuId(ciuId));

	}
}
