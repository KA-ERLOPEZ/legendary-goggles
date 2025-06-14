package com.sistema.pizzeria.elpirata.api.core.services.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.sistema.pizzeria.elpirata.api.core.entities.LiquidacionSalarial;
import com.sistema.pizzeria.elpirata.api.core.exceptions.CustomException;
import com.sistema.pizzeria.elpirata.api.core.mappers.LiquidacionSalarialMapper;
import com.sistema.pizzeria.elpirata.api.core.repositories.ContratoRepository;
import com.sistema.pizzeria.elpirata.api.core.repositories.LiquidacionSalarialRepository;
import com.sistema.pizzeria.elpirata.api.core.services.LiquidacionSalarialService;
import com.sistema.pizzeria.elpirata.api.web.dto.LiquidacionSalarialDTO;
import com.sistema.pizzeria.elpirata.api.web.dto.PageResponseDTO;

public class LiquidacionSalarialServiceImpl implements LiquidacionSalarialService {

	private final LiquidacionSalarialRepository liquidacionSalarialRepository;

	private final LiquidacionSalarialMapper liquidacionSalarialMapper;

	private final ContratoRepository contratoRepository;

	/**
	 * Constructor for LiquidacionSalarialServiceImpl.
	 *
	 * @param liquidacionSalarialRepository the repository for LiquidacionSalarial
	 * @param liquidacionSalarialMapper     the mapper for LiquidacionSalarial
	 * @param contratoRepository            the repository for Contrato
	 */
	public LiquidacionSalarialServiceImpl(LiquidacionSalarialRepository liquidacionSalarialRepository,
			LiquidacionSalarialMapper liquidacionSalarialMapper, ContratoRepository contratoRepository) {
		this.liquidacionSalarialRepository = liquidacionSalarialRepository;
		this.liquidacionSalarialMapper = liquidacionSalarialMapper;
		this.contratoRepository = contratoRepository;
	}

	@Override
	public List<LiquidacionSalarialDTO> findAll() {

		return liquidacionSalarialRepository.findAll().stream().map(liquidacionSalarialMapper::toDTO).toList();
	}

	@Override
	public LiquidacionSalarialDTO findById(Long id) {

		return liquidacionSalarialRepository.findById(id).map(liquidacionSalarialMapper::toDTO)
				.orElseThrow(() -> new CustomException("LiquidacionSalarial no encontrado con id: " + id));
	}

	@Override
	public LiquidacionSalarialDTO save(LiquidacionSalarialDTO dto) {

		validar(dto, false);
		return liquidacionSalarialMapper
				.toDTO(liquidacionSalarialRepository.save(liquidacionSalarialMapper.toEntity(dto)));
	}

	@Override
	public LiquidacionSalarialDTO update(LiquidacionSalarialDTO dto) {

		validar(dto, true);

		return liquidacionSalarialMapper
				.toDTO(liquidacionSalarialRepository.save(liquidacionSalarialMapper.toEntity(dto)));
	}

	@Override
	public void deleteById(Long id) {
		if (!liquidacionSalarialRepository.existsById(id)) {
			throw new CustomException("LiquidacionSalarial no encontrado con id: " + id);
		}
		liquidacionSalarialRepository.deleteById(id);

	}

	@Override
	public PageResponseDTO<LiquidacionSalarialDTO> getAllByContratoId(Long contratoId, int page, int size) {
		validarContratoId(contratoId);

		Pageable pageable = PageRequest.of(page, size);

		Page<LiquidacionSalarial> pageResult = liquidacionSalarialRepository.findAllByContratoId(contratoId, pageable);

		return new PageResponseDTO<>(pageResult.getContent().stream().map(liquidacionSalarialMapper::toDTO).toList(),
				pageResult.getNumber(), pageResult.getSize(), pageResult.getTotalElements(), pageResult.getTotalPages(),
				pageResult.isLast());
	}

	@Override
	public PageResponseDTO<LiquidacionSalarialDTO> getAllByContratoIdAndAnio(Long contratoId, Integer anio, int page,
			int size) {
		validarContratoId(contratoId);

		Pageable pageable = PageRequest.of(page, size);
		Page<LiquidacionSalarial> pageResult = liquidacionSalarialRepository.findAllByContratoIdAndAnio(contratoId,
				anio, pageable);

		return new PageResponseDTO<>(pageResult.getContent().stream().map(liquidacionSalarialMapper::toDTO).toList(),
				pageResult.getNumber(), pageResult.getSize(), pageResult.getTotalElements(), pageResult.getTotalPages(),
				pageResult.isLast());
	}

	@Override
	public LiquidacionSalarialDTO getByContratoIdAndAnioAndMes(Long contratoId, Integer anio, Integer mes) {

		validarContratoId(contratoId);

		return liquidacionSalarialRepository.findByContratoIdAndAnioAndMes(contratoId, anio, mes)
				.map(liquidacionSalarialMapper::toDTO)
				.orElseThrow(() -> new CustomException("LiquidacionSalarial no encontrada para el contrato con id: "
						+ contratoId + ", año: " + anio + " y mes: " + mes));
	}

	@Override
	public PageResponseDTO<LiquidacionSalarialDTO> getAllByContratoIdAndMes(Long contratoId, Integer mes, int page,
			int size) {
		validarContratoId(contratoId);
		Pageable pageable = PageRequest.of(page, size);
		Page<LiquidacionSalarial> pageResult = liquidacionSalarialRepository.findAllByContratoIdAndMes(contratoId, mes,
				pageable);
		return new PageResponseDTO<>(pageResult.getContent().stream().map(liquidacionSalarialMapper::toDTO).toList(),
				pageResult.getNumber(), pageResult.getSize(), pageResult.getTotalElements(), pageResult.getTotalPages(),
				pageResult.isLast());
	}

	@Override
	public PageResponseDTO<LiquidacionSalarialDTO> getAllByAnio(Integer anio, int page, int size) {
		
		Pageable pageable = PageRequest.of(page, size);
		Page<LiquidacionSalarial> pageResult = liquidacionSalarialRepository.findAllByAnio(anio, pageable);
		
		return new PageResponseDTO<>(pageResult.getContent().stream().map(liquidacionSalarialMapper::toDTO).toList(),
				pageResult.getNumber(), pageResult.getSize(), pageResult.getTotalElements(), pageResult.getTotalPages(),
				pageResult.isLast());
	}

	@Override
	public PageResponseDTO<LiquidacionSalarialDTO> getByAnioAndMes(Integer anio, Integer mes, int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<LiquidacionSalarial> pageResult = liquidacionSalarialRepository.findByAnioAndMes(anio, mes, pageable);
		
		return new PageResponseDTO<>(pageResult.getContent().stream().map(liquidacionSalarialMapper::toDTO).toList(),
				pageResult.getNumber(), pageResult.getSize(), pageResult.getTotalElements(), pageResult.getTotalPages(),
				pageResult.isLast());
	}

	@Override
	public PageResponseDTO<LiquidacionSalarialDTO> getAllByContratoIdAndFechaLiquidacionBetween(Long contratoId,
			String fechaLiquidacionInicio, String fechaLiquidacionFin, int page, int size) {
		validarContratoId(contratoId);
		Pageable pageable = PageRequest.of(page, size);
		Page<LiquidacionSalarial> pageResult = liquidacionSalarialRepository
				.findAllByContratoIdAndFechaLiquidacionBetween(LocalDate.parse(fechaLiquidacionInicio), 
						LocalDate.parse(fechaLiquidacionFin), contratoId, pageable);
		return new PageResponseDTO<>(pageResult.getContent().stream().map(liquidacionSalarialMapper::toDTO).toList(),
				pageResult.getNumber(), pageResult.getSize(), pageResult.getTotalElements(), pageResult.getTotalPages(),
				pageResult.isLast());
	}

	@Override
	public PageResponseDTO<LiquidacionSalarialDTO> getAllByAnioAndFechaLiquidacionBetween(Integer anio,
			String fechaLiquidacionInicio, String fechaLiquidacionFin, int page, int size) {
		
		
		Pageable pageable = PageRequest.of(page, size);
		Page<LiquidacionSalarial> pageResult = liquidacionSalarialRepository.findAllByAnioAndFechaLiquidacionBetween(
				anio, LocalDate.parse(fechaLiquidacionInicio), LocalDate.parse(fechaLiquidacionFin), pageable);
		
		return new PageResponseDTO<>(pageResult.getContent().stream().map(liquidacionSalarialMapper::toDTO).toList(),
				pageResult.getNumber(), pageResult.getSize(), pageResult.getTotalElements(), pageResult.getTotalPages(),
				pageResult.isLast());
	}

	@Override
	public PageResponseDTO<LiquidacionSalarialDTO> getAllByMesAndFechaLiquidacionBetween(Integer mes,
			String fechaLiquidacionInicio, String fechaLiquidacionFin, int page, int size) {
		
		Pageable pageable = PageRequest.of(page, size);
		Page<LiquidacionSalarial> pageResult = liquidacionSalarialRepository.findAllByMesAndFechaLiquidacionBetween(mes,
				LocalDate.parse(fechaLiquidacionInicio), LocalDate.parse(fechaLiquidacionFin), pageable);
	
		return new PageResponseDTO<>(pageResult.getContent().stream().map(liquidacionSalarialMapper::toDTO).toList(),
				pageResult.getNumber(), pageResult.getSize(), pageResult.getTotalElements(), pageResult.getTotalPages(),
				pageResult.isLast());
	}

	@Override
	public PageResponseDTO<LiquidacionSalarialDTO> getAllByFechaLiquidacionBetween(String fechaLiquidacionInicio,
			String fechaLiquidacionFin, int page, int size) {
		
		Pageable pageable = PageRequest.of(page, size);
		Page<LiquidacionSalarial> pageResult = liquidacionSalarialRepository.findAllByFechaLiquidacionBetween(
				LocalDate.parse(fechaLiquidacionInicio), LocalDate.parse(fechaLiquidacionFin), pageable);
		
		return new PageResponseDTO<>(pageResult.getContent().stream().map(liquidacionSalarialMapper::toDTO).toList(),
				pageResult.getNumber(), pageResult.getSize(), pageResult.getTotalElements(), pageResult.getTotalPages(),
				pageResult.isLast());
	}

	private void validar(LiquidacionSalarialDTO dto, boolean isUpdate) {

		LiquidacionSalarial liquidacionSalarial = liquidacionSalarialRepository
				.findByContratoIdAndAnioAndMes(dto.getContratoId(), dto.getAnio(), dto.getMes()).orElse(null);

		if (!liquidacionSalarialRepository.existsById(dto.getId()) && isUpdate) {

			throw new RuntimeException("LiquidacionSalarial no encontrado con id: " + dto.getId());
		}

		if (liquidacionSalarial != null && !isUpdate) {
			throw new RuntimeException("Ya existe una liquidacion salarial para el contrato con id: "
					+ dto.getContratoId() + " en el año: " + dto.getAnio() + " y mes: " + dto.getMes());
		}

		if (!contratoRepository.existsById(dto.getContratoId())) {
			throw new RuntimeException("Contrato no encontrado con id: " + dto.getContratoId());
		}

	}

	private void validarContratoId(Long contratoId) {
		if (!contratoRepository.existsById(contratoId)) {
			throw new CustomException("Contrato no encontrado con id: " + contratoId);
		}

	}

	@Override
	public PageResponseDTO<LiquidacionSalarialDTO> getAll(int page, int size) {
		
		Pageable pageable = PageRequest.of(page, size);
		Page<LiquidacionSalarial> pageResult = liquidacionSalarialRepository.findAll(pageable);
		
		return new PageResponseDTO<>(pageResult.getContent().stream().map(liquidacionSalarialMapper::toDTO).toList(),
				pageResult.getNumber(), pageResult.getSize(), pageResult.getTotalElements(), pageResult.getTotalPages(),
				pageResult.isLast());
	}

}
