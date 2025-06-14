package com.sistema.pizzeria.elpirata.api.core.services;

import java.util.Date;
import java.util.List;

import com.sistema.pizzeria.elpirata.api.core.services.generics.GenericService;
import com.sistema.pizzeria.elpirata.api.web.dto.UsuarioLoginDTO;

public interface UsuarioLoginService extends GenericService<UsuarioLoginDTO, Long>{

	/**
	 * Busca todos los logins exitosos.
	 * @param usuarioLoginDTO
	 * @return
	 */
	public List<UsuarioLoginDTO> findSuccessfulLogins();
	
	/**
	 * Busca todos los logins fallidos.
	 * 
	 * @param usuarioLoginDTO
	 * @return
	 */
	public List<UsuarioLoginDTO> findFailedLogins();
	
	/**
	 * Busca todos los logins por ip especifica.
	 * @param ip
	 * @return Lista de logins por ip
	 */
	public List<UsuarioLoginDTO> findByIp(String ip);
	
	/**
	 * Busar todos los logins por rango de fechas.
	 * @param startDate
	 * @param endDate
	 * @return Lista de logins por rango de fechas
	 */
	List<UsuarioLoginDTO> findByTimestampBetween(Date startDate, Date endDate);
}
