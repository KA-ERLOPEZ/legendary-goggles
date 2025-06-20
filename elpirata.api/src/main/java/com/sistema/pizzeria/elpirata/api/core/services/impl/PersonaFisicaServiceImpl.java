package com.sistema.pizzeria.elpirata.api.core.services.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistema.pizzeria.elpirata.api.core.entities.PersonaFisica;
import com.sistema.pizzeria.elpirata.api.core.exceptions.CustomException;
import com.sistema.pizzeria.elpirata.api.core.mappers.PersonaFisicaMapper;
import com.sistema.pizzeria.elpirata.api.core.repositories.BarrioRepository;
import com.sistema.pizzeria.elpirata.api.core.repositories.NacionalidadRepository;
import com.sistema.pizzeria.elpirata.api.core.repositories.PersonaFisicaRepository;
import com.sistema.pizzeria.elpirata.api.core.services.PersonaFisicaService;
import com.sistema.pizzeria.elpirata.api.web.dto.PageResponseDTO;
import com.sistema.pizzeria.elpirata.api.web.dto.PersonaFisicaDTO;

@Service
@Transactional
public class PersonaFisicaServiceImpl implements PersonaFisicaService {

	private final PersonaFisicaRepository repository;
	private final PersonaFisicaMapper mapper;
	private final BarrioRepository barrioRepository;
	private final NacionalidadRepository nacionalidadRepository;

	public PersonaFisicaServiceImpl(PersonaFisicaRepository repository, PersonaFisicaMapper mapper, BarrioRepository barrioRepository,
			NacionalidadRepository nacionalidadRepository) {
		this.repository = repository;
		this.mapper = mapper;
		this.barrioRepository = barrioRepository;
		this.nacionalidadRepository = nacionalidadRepository;
	}

	@Override
	public List<PersonaFisicaDTO> findAll() {
		return mapper.toDTOList(repository.findAll());
	}

	@Override
	public PersonaFisicaDTO findById(Long id) {
		PersonaFisica persona = repository.findById(id)
				.orElseThrow(() -> new CustomException("Persona con ID " + id + " no encontrada."));
		return mapper.toDTO(persona);
	}

	@Override
	public PersonaFisicaDTO save(PersonaFisicaDTO dto) {
		validar(dto);
		return mapper.toDTO(repository.save(mapper.toEntity(dto)));
	}

	@Override
	public PersonaFisicaDTO update(PersonaFisicaDTO dto) {

		validar(dto);
		return mapper.toDTO(repository.save(mapper.toEntity(dto)));
	}

	@Override
	public void deleteById(Long id) {
		if (!repository.existsById(id)) {
			throw new CustomException("Persona con ID " + id + " no encontrada.");
		}
		repository.deleteById(id);
	}

	@Override
	public PageResponseDTO<PersonaFisicaDTO> getAllbyPage(int page, int size) {

		Pageable pageable = PageRequest.of(page, size);
		Page<PersonaFisica> personaPage = repository.findAll(pageable);
		return new PageResponseDTO<>(mapper.toDTOList(personaPage.getContent()), personaPage.getNumber(),
				personaPage.getSize(), personaPage.getTotalElements(), personaPage.getTotalPages(),
				personaPage.isLast());
	}

	@Override
	public PageResponseDTO<PersonaFisicaDTO> getAllbyPageAndFilterByPerNombre(int page, int size, String perNombre) {

		Pageable pageable = PageRequest.of(page, size);

		Page<PersonaFisica> personaPage = repository.findByPerNombreContainingIgnoreCase(perNombre, pageable);

		if (personaPage.isEmpty()) {
			throw new CustomException("No se encontraron resultados para el nombre: " + perNombre);
		}

		return new PageResponseDTO<>(mapper.toDTOList(personaPage.getContent()), personaPage.getNumber(),
				personaPage.getSize(), personaPage.getTotalElements(), personaPage.getTotalPages(),
				personaPage.isLast());
	}

	@Override
	public PersonaFisicaDTO getByPerCedula(String perCedula) {
		// TODO Auto-generated method stub
		return repository.findByPerNroCedula(perCedula).map(mapper::toDTO)
				.orElseThrow(() -> new CustomException("Persona con cédula " + perCedula + " no encontrada."));
	}

	private void validar(PersonaFisicaDTO dto) {
		
		PersonaFisica existingPersona = repository.findById(dto.getPerId())
				.orElse(null);
		
		if(Objects.isNull(existingPersona) && dto.getPerId() > 0) {
			throw new CustomException("Persona con ID " + dto.getPerId() + " no encontrada.");
		}
		
		if(existingPersona != null) {
			if (!existingPersona.getPerNroCedula().equals(dto.getPerNroCedula())
					&& repository.existsByPerNroCedula(dto.getPerNroCedula())) {
				throw new CustomException("El número de documento '" + dto.getPerNroCedula() + "' ya existe.");
			}

			if (dto.getPerEmail() != null && !existingPersona.getPerEmail().equals(dto.getPerEmail())
					&& repository.existsByPerEmail(dto.getPerEmail())) {
				throw new CustomException("El email '" + dto.getPerEmail() + "' ya está registrado.");
			}

			barrioRepository.findById(dto.getBarId())
					.orElseThrow(() -> new CustomException("Barrio con ID " + dto.getBarId() + " no encontrado."));

			nacionalidadRepository.findById(dto.getNacId()).orElseThrow(() -> new CustomException(
					"Nacionalidad con ID " + dto.getNacId() + " no encontrada."));
		}
		

		

	}

}
