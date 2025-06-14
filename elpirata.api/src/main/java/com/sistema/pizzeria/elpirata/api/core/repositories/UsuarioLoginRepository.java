package com.sistema.pizzeria.elpirata.api.core.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sistema.pizzeria.elpirata.api.core.entities.UsuarioLogin;

public interface UsuarioLoginRepository extends JpaRepository<UsuarioLogin, Long> {

	
	List<UsuarioLogin> findByLoginSuccess(boolean success);

	List<UsuarioLogin> findByLoginIp(String ip);

	
	@Query("SELECT u FROM UsuarioLogin u WHERE u.loginTimestamp BETWEEN :start AND :end")
	List<UsuarioLogin> findByTimestampBetween(@Param("start") Date start, @Param("end") Date end);

}
