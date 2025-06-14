package com.sistema.pizzeria.elpirata.api.core.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sistema.pizzeria.elpirata.api.core.entities.Estado;
import com.sistema.pizzeria.elpirata.api.core.entities.Pais;
import com.sistema.pizzeria.elpirata.api.core.exceptions.CustomException;
import com.sistema.pizzeria.elpirata.api.core.mappers.PaisMapper;
import com.sistema.pizzeria.elpirata.api.core.repositories.EstadoRepository;
import com.sistema.pizzeria.elpirata.api.core.repositories.PaisRepository;
import com.sistema.pizzeria.elpirata.api.core.services.PaisService;
import com.sistema.pizzeria.elpirata.api.web.dto.PaisDTO;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PaisServiceImpl implements PaisService {

	private final PaisRepository repository;
    private final PaisMapper mapper;
    private final EstadoRepository estadoRepository;

    
    public PaisServiceImpl(PaisRepository repository, PaisMapper mapper, EstadoRepository estadoRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.estadoRepository = estadoRepository;
    }

    @Override
    public List<PaisDTO> findAll() {
        return mapper.toDTOList(repository.findAll());
    }

    @Override
    public PaisDTO findById(Long id) {
        Pais pais = repository.findById(id)
                .orElseThrow(() -> new CustomException("País con ID " + id + " no encontrado."));
        return mapper.toDTO(pais);
    }

    @Override
    public PaisDTO save(PaisDTO dto) {
        if (repository.existsByPaisNombre(dto.getPaisNombre())) {
            throw new CustomException("El país con nombre '" + dto.getPaisNombre() + "' ya existe.");
        }

        Estado estado = estadoRepository.findById(dto.getEstado().getEstadoId())
                .orElseThrow(() -> new CustomException("Estado con ID " + dto.getEstado().getEstadoId() + " no encontrado."));

        Pais pais = mapper.toEntity(dto);
        pais.setEstado(estado); // Asegura que la relación con Estado sea válida
        Pais savedPais = repository.save(pais);

        return mapper.toDTO(savedPais);
    }

    @Override
    public PaisDTO update(PaisDTO dto) {
        Pais existingPais = repository.findById(dto.getPaisId())
                .orElseThrow(() -> new CustomException("País con ID " + dto.getPaisId() + " no encontrado."));

        if (!existingPais.getPaisNombre().equals(dto.getPaisNombre())
                && repository.existsByPaisNombre(dto.getPaisNombre())) {
            throw new CustomException("El país con nombre '" + dto.getPaisNombre() + "' ya existe.");
        }

        Estado estado = estadoRepository.findById(dto.getEstado().getEstadoId())
                .orElseThrow(() -> new CustomException("Estado con ID " + dto.getEstado().getEstadoId() + " no encontrado."));

        existingPais.setPaisNombre(dto.getPaisNombre());
        existingPais.setEstado(estado);

        Pais updatedPais = repository.save(existingPais);

        return mapper.toDTO(updatedPais);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

	@Override
	public PaisDTO findByNombre(String nombre) {
		
		return repository.findByPaisNombre(nombre).map(mapper::toDTO)
				.orElseThrow(() -> new CustomException("País con nombre " + nombre + " no encontrado."));
	}

	@Override
	public List<PaisDTO> getByEstadoId(long estadoId) {
		
		return repository.findByEstado_EstadoId(estadoId).stream().map(mapper::toDTO).toList();
	}

	@Override
	public boolean existsByNombre(String nombre) {
		// TODO Auto-generated method stub
		return repository.existsByPaisNombre(nombre);
	}
}
