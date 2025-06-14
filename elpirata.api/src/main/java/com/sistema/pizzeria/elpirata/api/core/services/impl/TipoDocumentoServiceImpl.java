package com.sistema.pizzeria.elpirata.api.core.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sistema.pizzeria.elpirata.api.core.exceptions.CustomException;
import com.sistema.pizzeria.elpirata.api.core.mappers.TipoDocumentoMapper;
import com.sistema.pizzeria.elpirata.api.core.repositories.TipoDocumentoRepository;
import com.sistema.pizzeria.elpirata.api.core.services.TipoDocumentoService;
import com.sistema.pizzeria.elpirata.api.web.dto.TipoDocumentoDTO;

@Service
public class TipoDocumentoServiceImpl implements TipoDocumentoService {

	
	private TipoDocumentoRepository tipoDocumentoRepository;
	private TipoDocumentoMapper tipoDocumentoMapper;
	
	/**
	 * Constructor de la clase TipoDocumentoServiceImpl
	 * 
	 * @param tipoDocumentoRepository Repositorio de tipo de documento
	 * @param tipoDocumentoMapper     Mapper de tipo de documento
	 */
	public TipoDocumentoServiceImpl(TipoDocumentoRepository tipoDocumentoRepository,
			TipoDocumentoMapper tipoDocumentoMapper) {
		this.tipoDocumentoRepository = tipoDocumentoRepository;
		this.tipoDocumentoMapper = tipoDocumentoMapper;
	}
	
	/**
	 * Método para listar todos los tipos de documentos
	 * 
	 * 
	 */
	@Override
	public List<TipoDocumentoDTO> findAll() {
		
		return tipoDocumentoRepository.findAll().stream().map(tipoDocumentoMapper::toDTO).toList();
	}

	/**
	 * Método para buscar un tipo de documento por su id
	 * 
	 * @param id Id del tipo de documento
	 * @return TipoDocumentoDTO
	 */
	@Override
	public TipoDocumentoDTO findById(Long id) {
		
		return tipoDocumentoRepository
				.findById(id).map(tipoDocumentoMapper::toDTO).orElseThrow(() -> new CustomException("Tipo de documento no encontrado"));
	}

	@Override
	public TipoDocumentoDTO save(TipoDocumentoDTO dto) {
		if (tipoDocumentoRepository.existsByTipodocNombre(dto.getTipodocNombre())) {
			throw new CustomException("El tipo de documento ya existe");
		}
		return tipoDocumentoMapper.toDTO(tipoDocumentoRepository.save(tipoDocumentoMapper.toEntity(dto)));
	}

	@Override
	public TipoDocumentoDTO update(TipoDocumentoDTO dto) {
		
		// Validar si el tipo de documento existe
		TipoDocumentoDTO tipoDocumento = findById(dto.getTipodocId());
		if (dto.getTipodocId() != tipoDocumento.getTipodocId() && tipoDocumento.getTipodocNombre().equals(dto.getTipodocNombre())) {
			throw new CustomException("El tipo de documento ya existe");
		}
		
		// Actualizar el tipo de documento
		return tipoDocumentoMapper.toDTO(tipoDocumentoRepository.save(tipoDocumentoMapper.toEntity(dto)));
	}

	@Override
	public void deleteById(Long id) {
		// Validar si el tipo de documento existe
		TipoDocumentoDTO tipoDocumento = findById(id);

		// Eliminar el tipo de documento
		tipoDocumentoRepository.delete(tipoDocumentoMapper.toEntity(tipoDocumento));

	}

	@Override
	public TipoDocumentoDTO findByTipodocNombre(String tipodocNombre) {
		
		return tipoDocumentoRepository.findByTipodocNombre(tipodocNombre).map(tipoDocumentoMapper::toDTO)
				.orElseThrow(() -> new CustomException("Tipo de documento no encontrado"));
	}

	@Override
	public boolean existsByTipodocNombre(String tipodocNombre) {
		
		return tipoDocumentoRepository.existsByTipodocNombre(tipodocNombre);
	}

	@Override
	public List<TipoDocumentoDTO> findByEstadoId(int estadoId) {
		
		return tipoDocumentoRepository.findByEstadoEstadoId(estadoId).stream().map(tipoDocumentoMapper::toDTO).toList();
	}

	@Override
	public List<TipoDocumentoDTO> getByTipoDocNombreContainingIgnoreCase(String tipoDocNombre) {
		
		return tipoDocumentoRepository.findByTipodocNombreContainingIgnoreCase(tipoDocNombre).stream()
				.map(tipoDocumentoMapper::toDTO).toList();
	}

}
