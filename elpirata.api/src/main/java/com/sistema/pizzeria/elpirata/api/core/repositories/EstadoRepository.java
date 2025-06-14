package com.sistema.pizzeria.elpirata.api.core.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.pizzeria.elpirata.api.core.entities.Dominio;
import com.sistema.pizzeria.elpirata.api.core.entities.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Long> {

	List<Estado> findByDominio(Dominio dominio);
}
