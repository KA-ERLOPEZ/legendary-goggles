package com.sistema.pizzeria.elpirata.api.core.services;

import com.sistema.pizzeria.elpirata.api.core.services.generics.GenericService;
import com.sistema.pizzeria.elpirata.api.web.dto.PageResponseDTO;
import com.sistema.pizzeria.elpirata.api.web.dto.PersonaFisicaDTO;

public interface PersonaFisicaService extends GenericService<PersonaFisicaDTO, Long> {

		
	/**
	 * Listar todas las personas
	 * @param page
	 * @param size
	 * @return Lista de personas
	 */
	PageResponseDTO<PersonaFisicaDTO> getAllbyPage(int page, int size);
	
	/**
	 * Listar todas las personas filtradas por nombre
	 * 
	 * @param page
	 * @param size
	 * @param perNombre
	 * @return Lista de personas filtradas por nombre
	 */
	PageResponseDTO<PersonaFisicaDTO> getAllbyPageAndFilterByPerNombre(int page, int size, String perNombre);
	
	/**
	 * Busca una persona por su cédula.
	 * @param perCedula
	 * @return PersonaDTO
	 */
	PersonaFisicaDTO getByPerCedula(String perCedula);
}
