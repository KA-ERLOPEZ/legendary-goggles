package com.sistema.pizzeria.elpirata.api.core.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistema.pizzeria.elpirata.api.core.entities.Nacionalidad;
import com.sistema.pizzeria.elpirata.api.core.exceptions.CustomException;
import com.sistema.pizzeria.elpirata.api.core.mappers.NacionalidadMapper;
import com.sistema.pizzeria.elpirata.api.core.repositories.NacionalidadRepository;
import com.sistema.pizzeria.elpirata.api.core.services.NacionalidadService;
import com.sistema.pizzeria.elpirata.api.web.dto.NacionalidadDTO;

@Service
@Transactional
public class NacionalidadServiceImpl implements NacionalidadService {

    private final NacionalidadRepository repository;
    private final NacionalidadMapper mapper;

    public NacionalidadServiceImpl(NacionalidadRepository repository, NacionalidadMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<NacionalidadDTO> findAll() {
        return mapper.toDTOList(repository.findAll());
    }

    @Override
    public NacionalidadDTO findById(Long id) {
        Nacionalidad nacionalidad = repository.findById(id)
                .orElseThrow(() -> new CustomException("Nacionalidad con ID " + id + " no encontrada."));
        return mapper.toDTO(nacionalidad);
    }

    @Override
    public NacionalidadDTO save(NacionalidadDTO dto) {
        if (repository.existsByNacNombre(dto.getNacNombre())) {
            throw new CustomException("La nacionalidad con nombre '" + dto.getNacNombre() + "' ya existe.");
        }

        Nacionalidad nacionalidad = mapper.toEntity(dto);
        Nacionalidad savedNacionalidad = repository.save(nacionalidad);
        return mapper.toDTO(savedNacionalidad);
    }

    @Override
    public NacionalidadDTO update(NacionalidadDTO dto) {
        Nacionalidad existingNacionalidad = repository.findById(dto.getNacId())
                .orElseThrow(() -> new CustomException("Nacionalidad con ID " + dto.getNacId() + " no encontrada."));

        if (!existingNacionalidad.getNacNombre().equals(dto.getNacNombre()) &&
                repository.existsByNacNombre(dto.getNacNombre())) {
            throw new CustomException("La nacionalidad con nombre '" + dto.getNacNombre() + "' ya existe.");
        }

        existingNacionalidad.setNacNombre(dto.getNacNombre());
        existingNacionalidad.setNacEnabled(dto.isNacEnabled());

        Nacionalidad updatedNacionalidad = repository.save(existingNacionalidad);
        return mapper.toDTO(updatedNacionalidad);
    }

    @Override
    public void deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new CustomException("Nacionalidad con ID " + id + " no encontrada.");
        }
        repository.deleteById(id);
    }

	@Override
	public NacionalidadDTO findByNacNombre(String nacNombre) {
		
		return repository.findByNacNombre(nacNombre).map(mapper::toDTO)
				.orElseThrow(() -> new CustomException("Nacionalidad no encontrada"));
	}

	@Override
	public boolean existsByNacNombre(String nacNombre) {

		return repository.existsByNacNombre(nacNombre);
	}

	@Override
	public List<NacionalidadDTO> findByNacEnabledTrue() {
	
		return repository.findByNacEnabledTrue().stream().map(mapper::toDTO).toList();
	}
}

