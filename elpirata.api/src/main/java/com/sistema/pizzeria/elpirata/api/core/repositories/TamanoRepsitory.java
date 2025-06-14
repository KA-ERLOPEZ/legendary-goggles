package com.sistema.pizzeria.elpirata.api.core.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.pizzeria.elpirata.api.core.entities.Tamano;

public interface TamanoRepsitory extends JpaRepository<Tamano, Long> {
	
	List<Tamano> findByEstado_estadoId(Integer estadoId);
	
	Optional<Tamano> findByTamDescripcion(String tamDescripcion);

}
