package com.sistema.pizzeria.elpirata.api.core.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.pizzeria.elpirata.api.core.entities.Dominio;

public interface DominioRepository extends JpaRepository<Dominio, Long> {
	
	 Optional<Dominio> findByDominioCodigo(String dominioCodigo);

	boolean existsByDominioCodigo(String dominioCodigo);
	 
}
