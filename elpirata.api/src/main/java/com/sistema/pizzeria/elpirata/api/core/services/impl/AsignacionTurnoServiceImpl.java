package com.sistema.pizzeria.elpirata.api.core.services.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sistema.pizzeria.elpirata.api.core.entities.AsignacionTurno;
import com.sistema.pizzeria.elpirata.api.core.entities.Contrato;
import com.sistema.pizzeria.elpirata.api.core.entities.Turno;
import com.sistema.pizzeria.elpirata.api.core.exceptions.CustomException;
import com.sistema.pizzeria.elpirata.api.core.mappers.AsignacionTurnoMapper;
import com.sistema.pizzeria.elpirata.api.core.mappers.ContratoMapper;
import com.sistema.pizzeria.elpirata.api.core.mappers.TurnoMapper;
import com.sistema.pizzeria.elpirata.api.core.repositories.AsignacionTurnoRepository;
import com.sistema.pizzeria.elpirata.api.core.repositories.ContratoRepository;
import com.sistema.pizzeria.elpirata.api.core.repositories.TurnoRepository;
import com.sistema.pizzeria.elpirata.api.core.services.AsignacionTurnoService;
import com.sistema.pizzeria.elpirata.api.web.dto.AsignacionTurnoDTO;
import com.sistema.pizzeria.elpirata.api.web.dto.PageResponseDTO;

@Service
public class AsignacionTurnoServiceImpl implements AsignacionTurnoService {

	private final AsignacionTurnoRepository asignacionRepository;
	private final TurnoRepository turnoRepository;
	private final ContratoRepository contratoRepository;
	private final AsignacionTurnoMapper mapper;
	private final ContratoMapper contratoMapper;

	/**
	 * Constructor que inyecta los repositorios y el mapper necesarios para el
	 * servicio.
	 *
	 * @param asignacionRepository Repositorio de asignaciones de turno
	 * @param turnoRepository      Repositorio de turnos
	 * @param contratoRepository   Repositorio de contratos
	 * @param mapper               Mapper para convertir entre entidad y DTO de
	 *                             Asignación de Turno
	 */
	public AsignacionTurnoServiceImpl(AsignacionTurnoRepository asigancionRepository, TurnoRepository turnoRepository,
			ContratoRepository contratoRepository, AsignacionTurnoMapper mapper, TurnoMapper turnoMapper,
			ContratoMapper contratoMapper) {
		super();
		this.asignacionRepository = asigancionRepository;
		this.turnoRepository = turnoRepository;
		this.contratoRepository = contratoRepository;
		this.mapper = mapper;
		this.contratoMapper = contratoMapper;

	}

	@Override
	public List<AsignacionTurnoDTO> findAll() {

		return asignacionRepository.findAll().stream().map(mapper::toDTO).toList();
	}

	@Override
	public AsignacionTurnoDTO findById(Long id) {

		return asignacionRepository.findById(id).map(mapper::toDTO)
				.orElseThrow(() -> new CustomException("Asignacion no encontrado con id: " + id));
	}

	@Override
	public AsignacionTurnoDTO save(AsignacionTurnoDTO dto) {

		boolean isPresent = asignacionRepository
				.findByAndContratoAndFechaInicioAndTurno_Nombre(
						contratoMapper.toEntity(dto.getContrato()), 
						dto.getFechaInicio(), 
						dto.getTurno().getNombre()).isPresent();
		if(isPresent) {
			throw new CustomException("El contrato ya tiene el turno "
								+ dto.getTurno().getNombre()
								+ "con fecha inicio" + dto.getFechaInicio());
		}

		return mapper.toDTO(asignacionRepository.save(mapper.toEntity(dto)));
	}

	@Override
	public AsignacionTurnoDTO update(AsignacionTurnoDTO dto) {

		if (asignacionRepository.existsByIdAndContratoAndFechaInicioAndTurno_Nombre(dto.getId(),
				contratoMapper.toEntity( dto.getContrato()), dto.getFechaInicio(), dto.getTurno().getNombre())) {
			return mapper.toDTO(asignacionRepository.save(mapper.toEntity(dto)));

		}
		
		throw new CustomException("Asignaciòn no encontrada");
		
	}

	@Override
	public void deleteById(Long id) {

		asignacionRepository.deleteById(id);
		;

	}

	@Override
	public PageResponseDTO<AsignacionTurnoDTO> getAllPage(int page, int size) {

		Pageable pageable = PageRequest.of(page, size);

		Page<AsignacionTurno> asignacionPage = asignacionRepository.findAll(pageable);

		if (asignacionPage.isEmpty()) {
			throw new CustomException("No hay asignaciones en lista");
		}
		return new PageResponseDTO<AsignacionTurnoDTO>(mapper.toDTOList(asignacionPage.getContent()),
				asignacionPage.getNumber(), asignacionPage.getSize(), asignacionPage.getTotalElements(),
				asignacionPage.getTotalPages(), asignacionPage.isLast()

		);
	}

	@Override
	public PageResponseDTO<AsignacionTurnoDTO> getAllByActivoTrue(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);

		Page<AsignacionTurno> asignacionPage = asignacionRepository.findAllByActivoTrue(pageable);

		if (asignacionPage.isEmpty()) {
			throw new CustomException("No hay asignaciones en lista");
		}
		return new PageResponseDTO<AsignacionTurnoDTO>(mapper.toDTOList(asignacionPage.getContent()),
				asignacionPage.getNumber(), asignacionPage.getSize(), asignacionPage.getTotalElements(),
				asignacionPage.getTotalPages(), asignacionPage.isLast()

		);

	}

	@Override
	public PageResponseDTO<AsignacionTurnoDTO> getAllByContratoAndActivoTrue(int page, int size, long contratoId) {
		Contrato contrato = contratoRepository.findById(contratoId)
				.orElseThrow(()-> new CustomException("Contrato no encontrado con id:"));
		Pageable pageable = PageRequest.of(page, size);

		Page<AsignacionTurno> asignacionPage = asignacionRepository.findAllByContratoAndActivoTrue(contrato, pageable);

		if (asignacionPage.isEmpty()) {
			throw new CustomException("No hay asignaciones en lista");
		}
		return new PageResponseDTO<AsignacionTurnoDTO>(mapper.toDTOList(asignacionPage.getContent()),
				asignacionPage.getNumber(), asignacionPage.getSize(), asignacionPage.getTotalElements(),
				asignacionPage.getTotalPages(), asignacionPage.isLast()

		);
	}

	@Override
	public PageResponseDTO<AsignacionTurnoDTO> getAllByTurnoAndActivoTrue(int page, int size, long turnoId) {
		
		Turno turno = turnoRepository.findById(turnoId)
				.orElseThrow(()-> new CustomException("Turno no encontrado con id: "+ turnoId));
		Pageable pageable = PageRequest.of(page, size);
		Page<AsignacionTurno> asignacionPage = asignacionRepository.findAllByTurnoAndActivoTrue(turno, pageable);
		
		
		return new PageResponseDTO<AsignacionTurnoDTO>(
					
				asignacionPage.getContent().stream().map(mapper::toDTO).toList(),
				asignacionPage.getNumber(),
				asignacionPage.getSize(),
				asignacionPage.getTotalElements(),
				asignacionPage.getTotalPages(),
				asignacionPage.isLast()
				);
	}

	@Override
	public AsignacionTurnoDTO update(long id, AsignacionTurnoDTO asignacionTurnoDTO) {
		 
		if(asignacionRepository.existsByIdAndContratoAndFechaInicioAndTurno_Nombre(
				id, contratoMapper.toEntity(asignacionTurnoDTO.getContrato()),
				asignacionTurnoDTO.getFechaInicio(),
				asignacionTurnoDTO.getTurno().getNombre()
				)) {
			
			return mapper.toDTO(asignacionRepository.save(mapper.toEntity(asignacionTurnoDTO)));
			
		}
		
		throw new CustomException("Asignaciòn no encontrada");
		
	}

}
