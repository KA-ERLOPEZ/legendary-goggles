package com.sistema.pizzeria.elpirata.api.core.services;

import com.sistema.pizzeria.elpirata.api.core.services.generics.GenericService;
import com.sistema.pizzeria.elpirata.api.web.dto.UsuarioAdminDTO;
import com.sistema.pizzeria.elpirata.api.web.dto.UsuarioClienteDTO;

public interface UsuarioService extends GenericService<UsuarioAdminDTO, Long> {

	/**
	 * Busca un usuario por su username.
	 * 
	 * @param username
	 * @return
	 */
	UsuarioClienteDTO findByUsername(String username);
	
	/**
	 * Registra un nuevo cliente.
	 * El cliente se registra como usuario de la aplicacion.
	 * El cliente no tiene permisos de administrador.
	 * El cliente no tiene permisos de vendedor.
	 * El cliente no tiene permisos de cocina.
	 * El cliente no tiene permisos de delivery.
	 * El cliente no tiene permisos de cajero.
	 * El cliente no tiene permisos de supervisor.
	 * El cliente no tiene permisos de gerente.
	 * El cliente no tiene permisos de dueño.
	 * El cliente no ti
	 * @param usuario
	 * @return UsuarioClienteDTO
	 */
	UsuarioClienteDTO registerClient(UsuarioClienteDTO usuario);
}
