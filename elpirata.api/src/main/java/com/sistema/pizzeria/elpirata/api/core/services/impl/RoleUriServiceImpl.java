package com.sistema.pizzeria.elpirata.api.core.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sistema.pizzeria.elpirata.api.core.entities.RoleUri;
import com.sistema.pizzeria.elpirata.api.core.entities.RoleUriPK;
import com.sistema.pizzeria.elpirata.api.core.exceptions.CustomException;
import com.sistema.pizzeria.elpirata.api.core.mappers.RoleUriMapper;
import com.sistema.pizzeria.elpirata.api.core.repositories.RoleUriRepository;
import com.sistema.pizzeria.elpirata.api.core.services.RoleUriService;
import com.sistema.pizzeria.elpirata.api.web.dto.RoleUriDTO;

@Service
public class RoleUriServiceImpl implements RoleUriService {

    private final RoleUriRepository roleUriRepository;
    private final RoleUriMapper roleUriMapper;

   
    /**
     * Constructor donde se inyectan el repositorio y el mapper
     * @param roleUriRepository
     * @param roleUriMapper
     */
    public RoleUriServiceImpl(RoleUriRepository roleUriRepository, RoleUriMapper roleUriMapper) {
        this.roleUriRepository = roleUriRepository;
        this.roleUriMapper = roleUriMapper;
    }

    @Override
    public List<RoleUriDTO> findAll() {
        return roleUriRepository.findAll()
                .stream()
                .map(roleUriMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RoleUriDTO findById(RoleUriPK id) {
        RoleUri roleUri = roleUriRepository.findById(id)
                .orElseThrow(() -> new CustomException("RoleUri no encontrado con ID: " + id));
        return roleUriMapper.toDTO(roleUri);
    }

    @Override
    public RoleUriDTO save(RoleUriDTO dto) {
        RoleUri roleUri = roleUriMapper.toEntity(dto);
        roleUri.setId(new RoleUriPK(dto.getRole().getRoleId(), dto.getUri().getUriId(),dto.getHttpMethod()));
        RoleUri savedRoleUri = roleUriRepository.save(roleUri);
        return roleUriMapper.toDTO(savedRoleUri);
    }

    @Override
    public RoleUriDTO update(RoleUriDTO dto) {
        RoleUriPK id = new RoleUriPK(dto.getRole().getRoleId(), dto.getUri().getUriId(),dto.getHttpMethod());
        RoleUri existingRoleUri = roleUriRepository.findById(id)
                .orElseThrow(() -> new CustomException("RoleUri no encontrado con ID: " + id));

        RoleUri updatedRoleUri = roleUriRepository.save(existingRoleUri);
        return roleUriMapper.toDTO(updatedRoleUri);
    }

    @Override
    public void deleteById(RoleUriPK id) {
        if (!roleUriRepository.existsById(id)) {
            throw new CustomException("RoleUri no encontrado con ID: " + id);
        }
        roleUriRepository.deleteById(id);
    }
}

