package com.sistema.pizzeria.elpirata.api.core.repositories;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.pizzeria.elpirata.api.core.entities.Sabor;

public interface SaborRepository extends JpaRepository<Sabor, Long> {

	boolean existsBySabNombre(String sabNombre);

	Optional<Sabor> findBySabNombre(String nombre);

	List<Sabor> findAllByEstado_EstadoId(Integer estado);

	
}
