package com.sistema.pizzeria.elpirata.api.core.repositories;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sistema.pizzeria.elpirata.api.core.entities.Usuario;
import com.sistema.pizzeria.elpirata.api.core.entities.UsuarioSesion;



public interface UsuarioSesionRepository extends JpaRepository<UsuarioSesion, Long> {



    /**
     * Busca una sesión activa por su token.
     * @param sessionToken
     * @return una sesión activa si existe, o un Optional vacío si no existe.
     */
    Optional<UsuarioSesion> findBySessionToken(String sessionToken);

    /**
     * Busca todas las sesiones activas de los usuarios.
     * @param pageable
     * @return una lista paginada de sesiones activas.
	 */
    Page<UsuarioSesion> findBySessionActiveTrue(Pageable pageable);

	/**
	 * Busca todas las sesiones de un usuario por su ID.
	 * 
	 * @param usuarioId
	 * @param pageable
	 * @return una lista paginada de sesiones activas.
	 */
    Page<UsuarioSesion> findByUsuario(Usuario usuario, Pageable pageable);
    
    
    /**
     * Busca todas las sesiones activas de un usuario por su ID.
     * @param usuarioId
     * @param pageable
     * @return
     */
    Page<UsuarioSesion> findByUsuario_UsuIdAndSessionActiveTrue( long usuarioId, Pageable pageable);


	/**
	 * Busca una sesión activa por su token y si está activa.
	 * 
	 * @param sessionToken
	 * @param sessionActive
	 * @return una sesión activa si existe, o un Optional vacío si no existe.
	 */
    Optional<UsuarioSesion> findBySessionTokenAndSessionActiveIsTrue(String sessionToken);

    /**
     * Modifica una sesión activa por su ID.
     * @param usuId
     * @param endDate
     */
    @Modifying
    @Query("UPDATE UsuarioSesion u SET u.sessionActive = false, u.sessionEnd = :endDate WHERE u.usuario.usuId = :usuId AND u.sessionActive = true")
    void deactivateActiveSessionsByUsuId(@Param("usuId") long usuId, @Param("endDate") Date endDate);

	/**
	 * Busca todas las sesiones activas de un usuario por su ID.
	 * 
	 * @param usuarioId
	 * @return una lista de sesiones activas.
	 */
    Page<UsuarioSesion> findBySessionIp(String sessionIp, Pageable pageable);
}

