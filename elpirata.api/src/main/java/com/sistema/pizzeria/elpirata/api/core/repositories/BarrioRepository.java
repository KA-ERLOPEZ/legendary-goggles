package com.sistema.pizzeria.elpirata.api.core.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.pizzeria.elpirata.api.core.entities.Barrio;

public interface BarrioRepository extends JpaRepository<Barrio, Long> {

	boolean existsByBarNombreAndCiudad_CiuId(String barNombre, long ciuId);
	List<Barrio> findByCiudad_CiuId(long ciuId);
}
