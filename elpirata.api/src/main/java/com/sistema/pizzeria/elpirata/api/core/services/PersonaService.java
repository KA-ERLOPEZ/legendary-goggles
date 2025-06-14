package com.sistema.pizzeria.elpirata.api.core.services;

import com.sistema.pizzeria.elpirata.api.core.services.generics.GenericService;
import com.sistema.pizzeria.elpirata.api.web.dto.PageResponseDTO;
import com.sistema.pizzeria.elpirata.api.web.dto.PersonaDTO;

public interface PersonaService extends GenericService<PersonaDTO, Long> {

		
	/**
	 * Listar todas las personas
	 * @param page
	 * @param size
	 * @return Lista de personas
	 */
	PageResponseDTO<PersonaDTO> getAllbyPage(int page, int size);
	
	/**
	 * Listar todas las personas filtradas por nombre
	 * 
	 * @param page
	 * @param size
	 * @param perNombre
	 * @return Lista de personas filtradas por nombre
	 */
	PageResponseDTO<PersonaDTO> getAllbyPageAndFilterByPerNombre(int page, int size, String perNombre);
	
	/**
	 * Busca una persona por su cédula.
	 * @param perCedula
	 * @return PersonaDTO
	 */
	PersonaDTO getByPerCedula(String perCedula);
}
