package com.sistema.pizzeria.elpirata.api.core.services.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sistema.pizzeria.elpirata.api.core.entities.Bonificacion;
import com.sistema.pizzeria.elpirata.api.core.exceptions.CustomException;
import com.sistema.pizzeria.elpirata.api.core.mappers.BonificacionMapper;
import com.sistema.pizzeria.elpirata.api.core.repositories.BonificacionRepository;
import com.sistema.pizzeria.elpirata.api.core.repositories.ContratoRepository;
import com.sistema.pizzeria.elpirata.api.core.services.generics.BonificacionService;
import com.sistema.pizzeria.elpirata.api.web.dto.BonificacionDTO;
import com.sistema.pizzeria.elpirata.api.web.dto.PageResponseDTO;

@Service
public class BonificacionServiceImpl implements BonificacionService {

	private final BonificacionRepository repository;
	
	private final ContratoRepository contratoRepository;
	
	private final BonificacionMapper mapper;
	
	/**
	 * Constructor que recibe el repositorio y el mapper
	 * 
	 * @param repository
	 * @param mapper
	 */
	public BonificacionServiceImpl(BonificacionRepository repository, 
			BonificacionMapper mapper, ContratoRepository contratoRepository) {
		this.repository = repository;
		this.mapper = mapper;
		this.contratoRepository = contratoRepository;
	}
	
	@Override
	public List<BonificacionDTO> findAll() {
		
		return repository.findAll().stream().map(mapper::toDTO).toList();
	}

	@Override
	public BonificacionDTO findById(Long id) {
		
		return repository.findById(id).map(mapper::toDTO).orElseThrow(()-> new CustomException("Bonificacion no encontrada"));
	}

	@Override
	public BonificacionDTO save(BonificacionDTO dto) {
		
		if (repository.existsByContratoIdAndConceptoIdAndFecha(dto.getContratoId(), dto.getConceptoId(),
				LocalDate.now())) {
			throw new CustomException("Ya existe una bonificacion para este contrato y concepto");
		}
		return mapper.toDTO(repository.save(mapper.toEntity(dto)));
	}

	@Override
	public BonificacionDTO update(BonificacionDTO dto) {
		Bonificacion bonificacion = repository.findByContratoIdAndConceptoIdAndFecha(
				dto.getContratoId(), dto.getConceptoId(), dto.getFecha())
				.orElse(null);
		if (bonificacion != null) {
			if (bonificacion.getId() != dto.getId()) {
				throw new CustomException("Ya existe una bonificacion para este contrato y concepto");
			}
		}
		return mapper.toDTO(repository.save(mapper.toEntity(dto)));
	}

	@Override
	public void deleteById(Long id) {
		if (!repository.existsById(id)) {
			throw new CustomException("Bonificacion no encontrada");
		}
		repository.deleteById(id);

	}

	@Override
	public PageResponseDTO<BonificacionDTO> findByContratoId(int page, int size, long id) {
		
		// Validar que el contrato existe
		if (!contratoRepository.existsById(id)) {
			throw new CustomException("Contrato no encontrado");
		}
		
		
		Pageable pageable = Pageable.ofSize(size).withPage(page);
		
		Page<Bonificacion> bonificaciones = repository.findByContratoId(id, pageable);
		
		
		return new PageResponseDTO<>(bonificaciones.getContent().stream().map(mapper::toDTO).toList(),
				bonificaciones.getNumber(), bonificaciones.getSize(), bonificaciones.getTotalElements(),
				bonificaciones.getTotalPages(), bonificaciones.isLast());
	}

	@Override
	public PageResponseDTO<BonificacionDTO> findByNombreOrApellido(int page, int size, String nombre, String apellido) {
		
		Pageable pageable = Pageable.ofSize(size).withPage(page);
		Page<Bonificacion> bonificaciones = repository
				.findByContratoPersonaPerNombreIgnoreCaseContainingOrContratoPersonaPerApellidoIgnoreCaseContaining(
						nombre, apellido, pageable);
		
		return new PageResponseDTO<>(bonificaciones.getContent().stream().map(mapper::toDTO).toList(),
				bonificaciones.getNumber(), bonificaciones.getSize(), bonificaciones.getTotalElements(),
				bonificaciones.getTotalPages(), bonificaciones.isLast());
	}

	@Override
	public PageResponseDTO<BonificacionDTO> findByEstadoAndContratoId(int page, int size, String estado,
			long contratoId) {
		// Validar que el contrato existe
		if (!contratoRepository.existsById(contratoId)) {
			throw new CustomException("Contrato no encontrado");
		}
		Pageable pageable = Pageable.ofSize(size).withPage(page);
		Page<Bonificacion> bonificaciones = repository.findByContratoIdAndEstado(estado, contratoId, pageable);
		
		return new PageResponseDTO<>(bonificaciones.getContent().stream().map(mapper::toDTO).toList(),
				bonificaciones.getNumber(), bonificaciones.getSize(), bonificaciones.getTotalElements(),
				bonificaciones.getTotalPages(), bonificaciones.isLast());
	}

	@Override
	public BonificacionDTO getByContratoIdAndConceptoIdAndFecha(long contratoId, long conceptoId, String fecha) {
		
		// Validar que el contrato existe
		if (!contratoRepository.existsById(contratoId)) {
			throw new CustomException("Contrato no encontrado");
		}
		
		return repository.findByContratoIdAndConceptoIdAndFecha(contratoId, conceptoId, LocalDate.parse(fecha))
				.map(mapper::toDTO).orElseThrow(() -> new CustomException("Bonificacion no encontrada"));
	}

	@Override
	public PageResponseDTO<BonificacionDTO> findByContratoIdAndFecha(long contratoId, String fecha, int page,
			int size) {
		// Validar que el contrato existe
		if (!contratoRepository.existsById(contratoId)) {
			throw new CustomException("Contrato no encontrado");
		}
		Pageable pageable = Pageable.ofSize(size).withPage(page);
		Page<Bonificacion> bonificaciones = repository.findByContratoIdAndFecha(contratoId,
				LocalDate.parse(fecha), pageable);
		
		return new PageResponseDTO<>(bonificaciones.getContent().stream().map(mapper::toDTO).toList(),
				bonificaciones.getNumber(), bonificaciones.getSize(), bonificaciones.getTotalElements(),
				bonificaciones.getTotalPages(), bonificaciones.isLast());
	}

}
