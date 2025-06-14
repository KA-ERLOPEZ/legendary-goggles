package com.sistema.pizzeria.elpirata.api.core.services.impl;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sistema.pizzeria.elpirata.api.core.entities.UsuarioSesion;
import com.sistema.pizzeria.elpirata.api.core.mappers.UsuarioSesionMapper;
import com.sistema.pizzeria.elpirata.api.core.repositories.UsuarioSesionRepository;
import com.sistema.pizzeria.elpirata.api.core.services.UsuarioSesionService;
import com.sistema.pizzeria.elpirata.api.web.dto.PageResponseDTO;
import com.sistema.pizzeria.elpirata.api.web.dto.UsuarioSesionDTO;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UsuarioSesionServiceImpl implements UsuarioSesionService {

	private final UsuarioSesionRepository repository;
	private final UsuarioSesionMapper mapper;

	public UsuarioSesionServiceImpl(UsuarioSesionRepository repository, UsuarioSesionMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	@Override
	public List<UsuarioSesionDTO> findAll() {
		
		return repository.findAll().stream().map(mapper::toDTO).toList();
	}

	@Override
	public UsuarioSesionDTO findById(Long id) {
		
		return repository.findById(id).map(mapper::toDTO)
				.orElseThrow(() -> new RuntimeException("UsuarioSesion con ID " + id + " no encontrado."));
	}

	@Override
	public UsuarioSesionDTO save(UsuarioSesionDTO dto) {
		
		return mapper.toDTO(repository.save(mapper.toEntity(dto))) ;
	}

	@Override
	public UsuarioSesionDTO update(UsuarioSesionDTO dto) {
		
		
		return mapper.toDTO(repository.save(mapper.toEntity(dto)));
	}

	@Override
	public void deleteById(Long id) {
		
		repository.deleteById(id);
		
	}

	@Override
	public PageResponseDTO<UsuarioSesionDTO> findAllPagination(int page, int size) {
		
		Pageable pageable = PageRequest.of(page, size);
		
		Page<UsuarioSesion> usuarioSesionPage = repository.findAll(pageable);
		
		return new PageResponseDTO<>(
				usuarioSesionPage.getContent().stream().map(mapper::toDTO).toList(),
				usuarioSesionPage.getNumber(),
				usuarioSesionPage.getSize(),
				usuarioSesionPage.getTotalElements(),
				usuarioSesionPage.getTotalPages(),
				usuarioSesionPage.isLast()
				);
	}

	@Override
	public PageResponseDTO<UsuarioSesionDTO> findActiveSessions(int page, int size) {
		
		Pageable pageable = PageRequest.of(page, size);
		Page<UsuarioSesion> usuarioSesionPage = repository.findBySessionActiveTrue(pageable);
		
		
		return new PageResponseDTO<>(usuarioSesionPage.getContent().stream().map(mapper::toDTO).toList(),
				usuarioSesionPage.getNumber(), usuarioSesionPage.getSize(), usuarioSesionPage.getTotalElements(),
				usuarioSesionPage.getTotalPages(), usuarioSesionPage.isLast());
	}


	@Override
	public void endSession(Long sessionId) {
		
		repository.findById(sessionId).ifPresent(usuarioSesion -> {
			usuarioSesion.setSessionActive(false);
			repository.save(usuarioSesion);
		});
		
	}

	@Override
	public UsuarioSesionDTO findBySessionToken(String sessionToken) {
		
		return repository.findBySessionTokenAndSessionActiveIsTrue(sessionToken).map(mapper::toDTO).orElseThrow(
				() -> new RuntimeException("No se encontró la sesión activa con el token: " + sessionToken));
	}

	@Override
	public PageResponseDTO<UsuarioSesionDTO> findActiveSessionsByUserId(int page, int size, long usuarioId) {
		
		Pageable pageable = PageRequest.of(page, size);
		Page<UsuarioSesion> usuarioSesionPage = repository.findByUsuario_UsuIdAndSessionActiveTrue(usuarioId, pageable);
		
		return new PageResponseDTO<>(usuarioSesionPage.getContent().stream().map(mapper::toDTO).toList(),
				usuarioSesionPage.getNumber(), usuarioSesionPage.getSize(), usuarioSesionPage.getTotalElements(),
				usuarioSesionPage.getTotalPages(), usuarioSesionPage.isLast());
	}

	@Override
	public void deactivateActiveSessionsByUserId(long usuarioId) {
		repository.deactivateActiveSessionsByUsuId(usuarioId, new java.util.Date());

	
		
	}

	@Override
	public PageResponseDTO<UsuarioSesionDTO> findBySessionIp(int page, int size, String sessionIp) {
		Pageable pageable = PageRequest.of(page, size);
		Page<UsuarioSesion> usuarioSesionPage = repository.findBySessionIp(sessionIp, pageable);
		
		return new PageResponseDTO<>(usuarioSesionPage.getContent().stream().map(mapper::toDTO).toList(),
				usuarioSesionPage.getNumber(), usuarioSesionPage.getSize(), usuarioSesionPage.getTotalElements(),
				usuarioSesionPage.getTotalPages(), usuarioSesionPage.isLast());
	}

	@Override
	public PageResponseDTO<UsuarioSesionDTO> findByUsuarioIdAndSessionActive(int page, int size, long usuarioId) {
		
		Pageable pageable = PageRequest.of(page, size);
		Page<UsuarioSesion> usuarioSesionPage = repository.findByUsuario_UsuIdAndSessionActiveTrue(usuarioId, pageable);
		
		
		return new PageResponseDTO<>(usuarioSesionPage.getContent().stream().map(mapper::toDTO).toList(),
				usuarioSesionPage.getNumber(), usuarioSesionPage.getSize(), usuarioSesionPage.getTotalElements(),
				usuarioSesionPage.getTotalPages(), usuarioSesionPage.isLast());
	}

	
}
