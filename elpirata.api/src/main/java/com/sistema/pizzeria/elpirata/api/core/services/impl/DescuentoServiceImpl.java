package com.sistema.pizzeria.elpirata.api.core.services.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sistema.pizzeria.elpirata.api.core.entities.Descuento;
import com.sistema.pizzeria.elpirata.api.core.exceptions.CustomException;
import com.sistema.pizzeria.elpirata.api.core.mappers.DescuentoMapper;
import com.sistema.pizzeria.elpirata.api.core.repositories.ConceptoRepository;
import com.sistema.pizzeria.elpirata.api.core.repositories.ContratoRepository;
import com.sistema.pizzeria.elpirata.api.core.repositories.DescuentoRepository;
import com.sistema.pizzeria.elpirata.api.core.services.DescuentoService;
import com.sistema.pizzeria.elpirata.api.web.dto.DescuentoDTO;
import com.sistema.pizzeria.elpirata.api.web.dto.PageResponseDTO;

@Service
public class DescuentoServiceImpl implements DescuentoService {
	
	private DescuentoRepository descuentoRepository;
	private ConceptoRepository conceptoRepository;
	private ContratoRepository contratoRepository;
	private DescuentoMapper descuentoMapper;
	
	public DescuentoServiceImpl(DescuentoRepository descuentoRepository, ConceptoRepository conceptoRepository,
			ContratoRepository contratoRepository, DescuentoMapper descuentoMapper) {
		this.descuentoRepository = descuentoRepository;
		this.conceptoRepository = conceptoRepository;
		this.contratoRepository = contratoRepository;
		this.descuentoMapper = descuentoMapper;
	}
	
	@Override
	public List<DescuentoDTO> findAll() {
		
		return descuentoRepository.findAll().stream().map(descuentoMapper::toDTO).toList();
	}

	@Override
	public DescuentoDTO findById(Long id) {
		
		return descuentoRepository.findById(id).map(descuentoMapper::toDTO)
				.orElseThrow(() -> new CustomException("Descuento no encontrado con id: " + id));
	}

	@Override
	public DescuentoDTO save(DescuentoDTO dto) {
		validar(dto, false);
		return descuentoMapper.toDTO(descuentoRepository.save(descuentoMapper.toEntity(dto)));
	}

	@Override
	public DescuentoDTO update(DescuentoDTO dto) {
		
		validar(dto, true);
		return descuentoMapper.toDTO(descuentoRepository.save(descuentoMapper.toEntity(dto)));
	}

	@Override
	public void deleteById(Long id) {
		if (!descuentoRepository.existsById(id)) {
			throw new CustomException("Descuento no encontrado con id: " + id);
		}
		descuentoRepository.deleteById(id);

	}

	@Override
	public PageResponseDTO<DescuentoDTO> getAllByContratoId(long contratoId, int page, int size) {
		if (!contratoRepository.existsById(contratoId)) {
			
			throw new CustomException("Contrato no encontrado con id: " + contratoId);
		}
		
		Pageable pageable = PageRequest.of(page, size);
		
		Page<Descuento> descuentosPage = descuentoRepository.findAllByContratoId(contratoId, pageable);
		
		
		return new PageResponseDTO<>(descuentosPage.getContent().stream().map(descuentoMapper::toDTO).toList(),
				descuentosPage.getNumber(), descuentosPage.getSize(), descuentosPage.getTotalElements(),
				descuentosPage.getTotalPages(),
				descuentosPage.isLast()
				);
	}

	@Override
	public PageResponseDTO<DescuentoDTO> getAllByConceptoId(long conceptoId, int page, int size) {
		if (!conceptoRepository.existsById(conceptoId)) {
			throw new CustomException("Concepto no encontrado con id: " + conceptoId);
		}
		Pageable pageable = PageRequest.of(page, size);
		Page<Descuento> descuentosPage = descuentoRepository.findAllByConceptoId(conceptoId, pageable);
		return new PageResponseDTO<>(descuentosPage.getContent().stream().map(descuentoMapper::toDTO).toList(),
				descuentosPage.getNumber(), descuentosPage.getSize(), descuentosPage.getTotalElements(),
				descuentosPage.getTotalPages(), descuentosPage.isLast());
	}

	@Override
	public PageResponseDTO<DescuentoDTO> getAllByConceptoIdAndEstado(long conceptoId, String estado, int page,
			int size) {
		if (!conceptoRepository.existsById(conceptoId)) {
			throw new CustomException("Concepto no encontrado con id: " + conceptoId);
		}
		Pageable pageable = PageRequest.of(page, size);
		Page<Descuento> descuentosPage = descuentoRepository.findAllByConceptoIdAndEstado(conceptoId, estado, pageable);	
		
		return new PageResponseDTO<>(descuentosPage.getContent().stream().map(descuentoMapper::toDTO).toList(),
				descuentosPage.getNumber(), descuentosPage.getSize(), descuentosPage.getTotalElements(),
				descuentosPage.getTotalPages(), descuentosPage.isLast());
	}

	@Override
	public DescuentoDTO getByContratoIdAndConceptoIdAndFecha(long contratoId, long conceptoId, String fecha) {
		if (!contratoRepository.existsById(contratoId)) {
			throw new CustomException("Contrato no encontrado con id: " + contratoId);
		}
		if (!conceptoRepository.existsById(conceptoId)) {
			throw new CustomException("Concepto no encontrado con id: " + conceptoId);
		}
		
		return descuentoRepository.findByContratoIdAndConceptoIdAndFecha(contratoId, conceptoId, LocalDate.parse(fecha))
				.map(descuentoMapper::toDTO)
				.orElseThrow(() -> new CustomException("Descuento no encontrado para contrato: " + contratoId
						+ ", concepto: " + conceptoId + ", fecha: " + fecha));
	}

	@Override
	public PageResponseDTO<DescuentoDTO> getAllByFechaBetween(String startDate, String endDate, int page, int size) {
		
		Pageable pageable = PageRequest.of(page, size);
		Page<Descuento> descuentosPage = descuentoRepository.findAllByFechaBetween(LocalDate.parse(startDate), LocalDate.parse(endDate), pageable);
		
		return new PageResponseDTO<>(descuentosPage.getContent().stream().map(descuentoMapper::toDTO).toList(),
				descuentosPage.getNumber(), descuentosPage.getSize(), descuentosPage.getTotalElements(),
				descuentosPage.getTotalPages(), descuentosPage.isLast());
	}

	@Override
	public PageResponseDTO<DescuentoDTO> getAllByEstado(String estado, int page, int size) {
		
		Pageable pageable = PageRequest.of(page, size);
		Page<Descuento> descuentosPage = descuentoRepository.findAllByEstado(estado, pageable);
		
		return new PageResponseDTO<>(descuentosPage.getContent().stream().map(descuentoMapper::toDTO).toList(),
				descuentosPage.getNumber(), descuentosPage.getSize(), descuentosPage.getTotalElements(),
				descuentosPage.getTotalPages(), descuentosPage.isLast());
	}

	@Override
	public PageResponseDTO<DescuentoDTO> getAllByContratoIdAndEstado(long contratoId, String estado, int page,
			int size) {
		if (!contratoRepository.existsById(contratoId)) {
			throw new CustomException("Contrato no encontrado con id: " + contratoId);
		}
		Pageable pageable = PageRequest.of(page, size);
		Page<Descuento> descuentosPage = descuentoRepository.findAllByContratoIdAndEstado(contratoId, estado, pageable);
		
		return new PageResponseDTO<>(descuentosPage.getContent().stream().map(descuentoMapper::toDTO).toList(),
				descuentosPage.getNumber(), descuentosPage.getSize(), descuentosPage.getTotalElements(),
				descuentosPage.getTotalPages(), descuentosPage.isLast());
	}
	
	private void validar(DescuentoDTO dto,  boolean isUpdate) {
		
		if (isUpdate && dto.getId() > 0) {
			throw new CustomException("El id del descuento es requerido para actualizar.");
		}
		Descuento descuento = descuentoRepository.findByContratoIdAndConceptoIdAndFecha(
				dto.getContrato().getId(), dto.getConceptoId(), dto.getFecha()).orElse(null);
		if (descuento != null && dto.getId() != descuento.getId()) {
			throw new CustomException("Ya existe un descuento con el mismo concepto, contrato y fecha.");
			
		}
		if ( !conceptoRepository.existsById(dto.getConceptoId())) {
			throw new CustomException("Concepto no encontrado con id: " + dto.getConceptoId());
		}
		if ( !contratoRepository.existsById(dto.getContrato().getId())) {
			throw new CustomException("Contrato no encontrado con id: " + dto.getContrato().getId());
		}
		
	}

}
