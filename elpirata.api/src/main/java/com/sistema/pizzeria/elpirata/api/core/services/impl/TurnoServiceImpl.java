package com.sistema.pizzeria.elpirata.api.core.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sistema.pizzeria.elpirata.api.core.entities.Turno;
import com.sistema.pizzeria.elpirata.api.core.exceptions.CustomException;
import com.sistema.pizzeria.elpirata.api.core.mappers.TurnoMapper;
import com.sistema.pizzeria.elpirata.api.core.repositories.TurnoRepository;
import com.sistema.pizzeria.elpirata.api.core.services.TurnoService;
import com.sistema.pizzeria.elpirata.api.web.dto.TurnoDTO;

@Service
public class TurnoServiceImpl implements TurnoService {

	private final TurnoRepository repository;
	private final TurnoMapper mapper;
	
	/**
	 * Constructor de la clase TurnoServiceImpl
	 * 
	 * @param repository el repositorio de turnos
	 * @param mapper     el mapeador de turnos
	 */
	public TurnoServiceImpl(TurnoRepository repository, TurnoMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}
	
	/**
	 * Obtiene todos los turnos.
	 * 
	 * @return una lista de objetos TurnoDTO
	 */
	@Override
	public List<TurnoDTO> findAll() {
		
		return mapper.toDTOList(repository.findAll());
	}

	/**
	 * Obtiene un turno por su ID.
	 * 
	 * @param id el ID del turno
	 * @return el objeto TurnoDTO correspondiente al ID
	 */
	@Override
	public TurnoDTO findById(Long id) {
		
		return repository.findById(id).map(mapper::toDTO)
				.orElseThrow(() -> new CustomException("No se encontró el turno con id: " + id));
	}

	@Override
	public TurnoDTO save(TurnoDTO dto) {
		if (repository.existsByNombre(dto.getNombre())) {
			throw new CustomException("Ya existe un turno con el nombre especificado");
		}
		return mapper.toDTO(repository.save(mapper.toEntity(dto)));
	}

	@Override
	public TurnoDTO update(TurnoDTO dto) {
		if (repository.existsByHoraInicioAndHoraFinAndNombre(dto.getHoraInicio(), dto.getHoraFin(), dto.getNombre())) {
			throw new CustomException("Ya existe un turno con la hora de inicio y fin especificadas");
		}
		return mapper.toDTO(repository.save(mapper.toEntity(dto)));
	}

	@Override
	public void deleteById(Long id) {
		if (!repository.existsById(id)) {
			throw new CustomException("No se encontró el turno con id: " + id);
		}
		repository.deleteById(id);

	}

	@Override
	public TurnoDTO updateTurnoActivo(Long id, boolean activo) {
	    Turno turno = repository.findById(id)
	        .orElseThrow(() -> new CustomException("No se encontró el turno con id: " + id));

	    turno.setActivo(activo);
	    Turno actualizado = repository.save(turno);

	    return mapper.toDTO(actualizado);
	}

	@Override
	public List<TurnoDTO> getAllActivos() {
		
		return mapper.toDTOList(repository.findAllByActivoTrue());
	}

}
