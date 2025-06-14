package com.sistema.pizzeria.elpirata.api.core.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistema.pizzeria.elpirata.api.core.entities.Dominio;
import com.sistema.pizzeria.elpirata.api.core.entities.Estado;
import com.sistema.pizzeria.elpirata.api.core.exceptions.CustomException;
import com.sistema.pizzeria.elpirata.api.core.mappers.EstadoMapper;
import com.sistema.pizzeria.elpirata.api.core.repositories.EstadoRepository;
import com.sistema.pizzeria.elpirata.api.core.services.EstadoService;
import com.sistema.pizzeria.elpirata.api.web.dto.DominioDTO;
import com.sistema.pizzeria.elpirata.api.web.dto.EstadoDTO;

@Service
@Transactional
public class EstadoServiceImpl implements EstadoService {

    private final EstadoRepository repository;
    private final EstadoMapper mapper;

    public EstadoServiceImpl(EstadoRepository repository, EstadoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<EstadoDTO> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EstadoDTO findById(Long id) {
        Estado estado = repository.findById(id)
                .orElseThrow(() -> new CustomException("Estado con ID " + id + " no encontrado."));
        return mapper.toDTO(estado);
    }

    @Override
    public EstadoDTO save(EstadoDTO dto) {
        Estado estado = mapper.toEntity(dto);
        Estado savedEstado = repository.save(estado);
        return mapper.toDTO(savedEstado);
    }

    @Override
    public EstadoDTO update(EstadoDTO dto) {
        long estadoId = dto.getEstadoId();
        if (!repository.existsById(estadoId)) {
            throw new CustomException("Estado con ID " + estadoId + " no encontrado.");
        }

        Estado estado = mapper.toEntity(dto);
        Estado updatedEstado = repository.save(estado);
        return mapper.toDTO(updatedEstado);
    }

    @Override
    public void deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new CustomException("Estado con ID " + id + " no encontrado.");
        }
        repository.deleteById(id);
    }

    @Override
    public List<EstadoDTO> buscarEstadoPorDominio(DominioDTO dominioDTO) {
        Dominio dominio = new Dominio(); 
        dominio.setDominioId(dominioDTO.getDominioId());
        return repository.findByDominio(dominio).stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
}
