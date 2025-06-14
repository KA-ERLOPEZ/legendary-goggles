package com.sistema.pizzeria.elpirata.api.core.services.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sistema.pizzeria.elpirata.api.core.exceptions.CustomException;
import com.sistema.pizzeria.elpirata.api.core.mappers.HoraExtraMapper;
import com.sistema.pizzeria.elpirata.api.core.repositories.HoraExtraRepository;
import com.sistema.pizzeria.elpirata.api.core.services.HoraExtraService;
import com.sistema.pizzeria.elpirata.api.web.dto.HoraExtraDTO;
import com.sistema.pizzeria.elpirata.api.web.dto.PageResponseDTO;

@Service
public class HoraExtraServiceImpl implements HoraExtraService {

	private final HoraExtraRepository repository;
	private final HoraExtraMapper mapper;

	/**
	 * Constructor de la clase HoraExtraServiceImpl
	 * 
	 * @param repository el repositorio de horas extras
	 * @param mapper     el mapeador de horas extras
	 */
	public HoraExtraServiceImpl(HoraExtraRepository repository, HoraExtraMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	@Override
	public List<HoraExtraDTO> findAll() {

		return repository.findAll().stream().map(mapper::toDTO).toList();
	}

	@Override
	public HoraExtraDTO findById(Long id) {

		return repository.findById(id).map(mapper::toDTO)
				.orElseThrow(() -> new CustomException("No se encontró la hora extra con id: " + id));
	}

	@Override
	public HoraExtraDTO save(HoraExtraDTO dto) {

		return mapper.toDTO(repository.save(mapper.toEntity(dto)));
	}

	@Override
	public HoraExtraDTO update(HoraExtraDTO dto) {

		if (repository.existsByContratoIdAndFecha(dto.getId(), dto.getFecha())) {
			throw new CustomException("Ya existe una hora extra con el contrato y la fecha especificada");
		}

		return mapper.toDTO(repository.save(mapper.toEntity(dto)));
	}

	@Override
	public void deleteById(Long id) {
		if (!repository.existsById(id)) {
			throw new CustomException("No se encontró la hora extra con id: " + id);
		}
		repository.deleteById(id);

	}

	@Override
	public PageResponseDTO<HoraExtraDTO> getByContratoId(long contratoId, int page, int size) {

		Pageable pageable = Pageable.ofSize(size).withPage(page);
		var pageResponse = repository.findByContratoId(contratoId, pageable);

		if (pageResponse.isEmpty()) {
			throw new CustomException("No se encontraron horas extras para el contrato con id: " + contratoId);
		}
		return new PageResponseDTO<>(pageResponse.getContent().stream().map(mapper::toDTO).toList(),
				pageResponse.getNumber(), pageResponse.getSize(), pageResponse.getTotalElements(),
				pageResponse.getTotalPages(), pageResponse.isLast());
	}

	@Override
	public PageResponseDTO<HoraExtraDTO> getByContratoIdAndEstado(long contratoId, String estado, int page, int size) {

		Pageable pageable = Pageable.ofSize(size).withPage(page);
		var pageResponse = repository.findByContratoIdAndEstado(contratoId, estado, pageable);

		if (pageResponse.isEmpty()) {
			throw new CustomException(
					"No se encontraron horas extras para el contrato con id: " + contratoId + " y estado: " + estado);
		}
		return new PageResponseDTO<>(pageResponse.getContent().stream().map(mapper::toDTO).toList(),
				pageResponse.getNumber(), pageResponse.getSize(), pageResponse.getTotalElements(),
				pageResponse.getTotalPages(), pageResponse.isLast());
	}

	public PageResponseDTO<HoraExtraDTO> getAllPageable(int page, int size) {
		Pageable pageable = Pageable.ofSize(size).withPage(page);
		var pageResponse = repository.findAll(pageable);

		if (pageResponse.isEmpty()) {
			throw new CustomException("No se encontraron horas extras");
		}
		return new PageResponseDTO<>(pageResponse.getContent().stream().map(mapper::toDTO).toList(),
				pageResponse.getNumber(), pageResponse.getSize(), pageResponse.getTotalElements(),
				pageResponse.getTotalPages(), pageResponse.isLast());
	}

	@Override
	public PageResponseDTO<HoraExtraDTO> findAllByEmpleadoId(int page, int size, Long empleadoId) {
		Pageable pageable = Pageable.ofSize(size).withPage(page);
		var pageResponse = repository.findByContratoPersonaPerId(empleadoId, pageable);
		if (pageResponse.isEmpty()) {
			throw new CustomException("No se encontraron horas extras para el empleado con id: " + empleadoId);
		}
		return new PageResponseDTO<>(pageResponse.getContent().stream().map(mapper::toDTO).toList(),
				pageResponse.getNumber(), pageResponse.getSize(), pageResponse.getTotalElements(),
				pageResponse.getTotalPages(), pageResponse.isLast());
	}

	@Override
	public HoraExtraDTO getByContratoIdAndFecha(long contratoId, LocalDate fecha) {
		if (!repository.existsByContratoIdAndFecha(contratoId, fecha)) {
			throw new CustomException("No se encontró la hora extra con el contrato y la fecha especificada");
		}
		return repository.findByContratoIdAndFecha(contratoId, fecha).map(mapper::toDTO).orElseThrow(
				() -> new CustomException("No se encontró la hora extra con el contrato y la fecha especificada"));
	}

	@Override
	public PageResponseDTO<HoraExtraDTO> getAll(int page, int size) {
		Pageable pageable = Pageable.ofSize(size).withPage(page);
		var pageResponse = repository.findAll(pageable);
		if (pageResponse.isEmpty()) {
			throw new CustomException("No se encontraron horas extras");
		}
		return new PageResponseDTO<>(pageResponse.getContent().stream().map(mapper::toDTO).toList(),
				pageResponse.getNumber(), pageResponse.getSize(), pageResponse.getTotalElements(),
				pageResponse.getTotalPages(), pageResponse.isLast());
	}

}
