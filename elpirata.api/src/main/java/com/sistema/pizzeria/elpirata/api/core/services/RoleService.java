package com.sistema.pizzeria.elpirata.api.core.services;

import java.util.List;

import com.sistema.pizzeria.elpirata.api.core.services.generics.GenericService;
import com.sistema.pizzeria.elpirata.api.web.dto.RoleDTO;

public interface RoleService extends GenericService<RoleDTO, Long> {
	
	/**
	 * Buscar roles por el estado de la propiedad roleEnabled
	 * 
	 * @param roleEnabled
	 * @return Lista de roles
	 */
	List<RoleDTO> buscarRolePorRoleEnabled(boolean roleEnabled);
	
	/**
	 * Buscar roles por el estado de la propiedad  roleDescripciòn
	 * @param descripcion
	 * @return RoleDTO
	 */
	RoleDTO buscarRolePorDescripcion(String descripcion);

}
