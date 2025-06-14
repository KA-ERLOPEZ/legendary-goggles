package com.sistema.pizzeria.elpirata.api.core.services;



import com.sistema.pizzeria.elpirata.api.core.services.generics.GenericService;
import com.sistema.pizzeria.elpirata.api.web.dto.PageResponseDTO;
import com.sistema.pizzeria.elpirata.api.web.dto.UsuarioSesionDTO;

public interface UsuarioSesionService extends GenericService<UsuarioSesionDTO, Long> {

	/**
	 * Busca todas las sesiones.
	 * @param page
	 * @param size
	 * @return paginación de sesiones 
	 */
	PageResponseDTO<UsuarioSesionDTO> findAllPagination(int page, int size); // Para paginación

	/**
	 * Busca todas las sesiones activas.
	 *
	 * @param page
	 * @param size
	 * @return
	 */
	PageResponseDTO<UsuarioSesionDTO> findActiveSessions(int page, int size); // Sesiones activas
	
	
	/**
	 * Busca todas las sesiones activas por usuario.
	 * 
	 * @param usuarioId
	 * @param page
	 * @param size
	 * @return paginación de sesiones activas por usuario
	 */
	PageResponseDTO<UsuarioSesionDTO> findByUsuarioIdAndSessionActive(int page, int size, long usuarioId); // Sesiones por usuario
	
	/**
	 * Filtra las sesiones activas por usuario.
	 * @param sessionId
	 * @return void
	 */
	void endSession(Long sessionId) ;
	
	/**
	 * Busca una sesión por su token y estado activo.
	 * 
	 * @param sessionToken
	 * @return sesión activa
	 */
	UsuarioSesionDTO findBySessionToken(String sessionToken);
	
	/**
	 * Listar sesiones activas por usuario.
	 * @param page
	 * @param size
	 * @param usuarioId
	 * @return paginación de sesiones activas por usuario
	 */
	PageResponseDTO<UsuarioSesionDTO> findActiveSessionsByUserId(int page, int size, long usuarioId);
	
	/**
	 * Desactivar todas las sesiones activas por usuario.
	 * @param usuarioId
	 * @return void
	 */
	void deactivateActiveSessionsByUserId(long usuarioId);
	
	/**
	 * Busca una sesión por su IP.
	 * 
	 * @param page
	 * @param size
	 * @param sessionIp
	 * @return sesión por IP
	 */
	PageResponseDTO<UsuarioSesionDTO> findBySessionIp(int page, int size, String sessionIp);

}
