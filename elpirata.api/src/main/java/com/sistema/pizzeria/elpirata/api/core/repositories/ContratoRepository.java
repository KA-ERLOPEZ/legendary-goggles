package com.sistema.pizzeria.elpirata.api.core.repositories;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.pizzeria.elpirata.api.core.entities.Contrato;
import com.sistema.pizzeria.elpirata.api.core.entities.Persona;

public interface ContratoRepository extends JpaRepository<Contrato, Long> {
	
	/**
	 * Método para bucar un contrato por persona y fecha de inicio
	 * 
	 * @param persona Persona
	 * @param contFecInicio Fecha de inicio del contrato
	 * @return Contrato
	 */
	Optional<Contrato> findByPersonaAndContFecInicio(Persona persona, Date contFecInicio);
}
