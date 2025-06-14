 package com.sistema.pizzeria.elpirata.api.core.services.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sistema.pizzeria.elpirata.api.core.entities.Role;
import com.sistema.pizzeria.elpirata.api.core.exceptions.CustomException;
import com.sistema.pizzeria.elpirata.api.core.mappers.RoleMapper;
import com.sistema.pizzeria.elpirata.api.core.repositories.RoleRepository;
import com.sistema.pizzeria.elpirata.api.core.services.RoleService;
import com.sistema.pizzeria.elpirata.api.web.dto.RoleDTO;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepository repository;
    private final RoleMapper mapper;

    // Constructor con inyección de dependencias
    public RoleServiceImpl(RoleRepository repository, RoleMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<RoleDTO> findAll() {
        // Obtener todos los roles desde el repositorio y mapearlos a DTO
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RoleDTO findById(Long id) {
        // Buscar un role por ID, si no se encuentra, lanza una excepción
        Role role = repository.findById(id)
                .orElseThrow(() -> new CustomException("Role con ID " + id + " no encontrado."));
        return mapper.toDTO(role);
    }

    @Override
    public RoleDTO save(RoleDTO dto) {
    	
    	// Verificar si el nombre del rol ya existe en otro registro
        Role existingRoleWithDescription = repository.findByRoleDescription(dto.getRoleDescripcion())
                .orElse(null);
    	
    	// Si el rol con la misma descripción ya existe 
        if (Objects.nonNull(existingRoleWithDescription)) {
            throw new CustomException("Role con descripción '" + dto.getRoleDescripcion() + "' ya existe.");
        }
        // Convertir el DTO a la entidad y guardarlo en la base de datos
        Role role = mapper.toEntity(dto);
        Role savedRole = repository.save(role);
        return mapper.toDTO(savedRole);
    }

    @Override
    public RoleDTO update(RoleDTO dto) {
        // Verificar si el role existe antes de actualizar
        Role existingRole = repository.findById(dto.getRoleId())
                .orElseThrow(() -> new CustomException("Role con ID " + dto.getRoleId() + " no encontrado."));
        
        // Verificar si el nombre del rol ya existe en otro registro
        Role existingRoleWithDescription = repository.findByRoleDescription(dto.getRoleDescripcion())
                .orElse(null);

        // Si el rol con la misma descripción ya existe y no es el mismo rol que se está actualizando
        if (Objects.nonNull(existingRoleWithDescription) && existingRoleWithDescription.getRoleId() != dto.getRoleId()) {
            throw new CustomException("Role con descripción '" + dto.getRoleDescripcion() + "' ya existe.");
        }

        // Actualizar el role con la nueva información del DTO
        existingRole.setRoleDescription(dto.getRoleDescripcion());
        existingRole.setRoleEnabled(dto.isRoleEnabled());

        // Guardar la entidad actualizada y devolver el DTO
        Role updatedRole = repository.save(existingRole);
        return mapper.toDTO(updatedRole);
    }

    @Override
    public void deleteById(Long id) {
        // Verificar si el role existe antes de eliminar
        if (!repository.existsById(id)) {
            throw new CustomException("Role con ID " + id + " no encontrado.");
        }
        // Eliminar el role por ID
        repository.deleteById(id);
    }

    @Override
    public List<RoleDTO> buscarRolePorRoleEnabled(boolean roleEnabled) {
        // Buscar roles por el campo "roleEnabled" y devolver la lista de DTOs
        List<Role> roles = repository.findByRoleEnabled(roleEnabled);
        return roles.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RoleDTO buscarRolePorDescripcion(String descripcion) {
        // Buscar un role por su descripción y devolver el DTO
        Role role = repository.findByRoleDescription(descripcion)
                .orElseThrow(() -> new CustomException("Role con descripción " + descripcion + " no encontrado."));
        return mapper.toDTO(role);
    }
}