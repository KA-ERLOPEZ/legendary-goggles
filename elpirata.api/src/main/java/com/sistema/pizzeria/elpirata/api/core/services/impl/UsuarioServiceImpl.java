package com.sistema.pizzeria.elpirata.api.core.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bastiaanjansen.otp.SecretGenerator;
import com.sistema.pizzeria.elpirata.api.core.entities.Estado;
import com.sistema.pizzeria.elpirata.api.core.entities.PersonaFisica;
import com.sistema.pizzeria.elpirata.api.core.entities.Role;
import com.sistema.pizzeria.elpirata.api.core.entities.Usuario;
import com.sistema.pizzeria.elpirata.api.core.exceptions.CustomException;
import com.sistema.pizzeria.elpirata.api.core.mappers.UsuarioMapper;
import com.sistema.pizzeria.elpirata.api.core.repositories.EstadoRepository;
import com.sistema.pizzeria.elpirata.api.core.repositories.PersonaFisicaRepository;
import com.sistema.pizzeria.elpirata.api.core.repositories.RoleRepository;
import com.sistema.pizzeria.elpirata.api.core.repositories.UsuarioRepository;
import com.sistema.pizzeria.elpirata.api.core.services.UsuarioService;
import com.sistema.pizzeria.elpirata.api.web.dto.UsuarioAdminDTO;
import com.sistema.pizzeria.elpirata.api.web.dto.UsuarioClienteDTO;

import io.micrometer.common.util.StringUtils;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

	private final UsuarioRepository repository;
	private final UsuarioMapper mapper;
	private final EstadoRepository estadoRepository;
	private final PersonaFisicaRepository personaRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;
	// private final BycryptPasswordEncoder passwordEncoder;

	public UsuarioServiceImpl(UsuarioRepository repository, UsuarioMapper mapper, EstadoRepository estadoRepository,
			PersonaFisicaRepository personaRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
		this.repository = repository;
		this.mapper = mapper;
		this.estadoRepository = estadoRepository;
		this.personaRepository = personaRepository;
		this.passwordEncoder = passwordEncoder;
		this.roleRepository = roleRepository;
	}

	@Override
	public List<UsuarioAdminDTO> findAll() {
		return mapper.toDTOList(repository.findAll());
	}

	@Override
	public UsuarioAdminDTO findById(Long id) {
		Usuario usuario = repository.findById(id)
				.orElseThrow(() -> new CustomException("Usuario con ID " + id + " no encontrado."));
		return mapper.toDTO(usuario);
	}

	@Override
	public UsuarioAdminDTO save(UsuarioAdminDTO dto) {
		if (repository.existsByUsuUsername(dto.getUsuUsername())) {
			throw new CustomException("El nombre de usuario '" + dto.getUsuUsername() + "' ya está registrado.");
		}

		Estado estado = estadoRepository.findById(dto.getEstado().getEstadoId()).orElseThrow(
				() -> new CustomException("Estado con ID " + dto.getEstado().getEstadoId() + " no encontrado."));

		PersonaFisica persona = personaRepository.findById(dto.getPersona().getPerId()).orElseThrow(
				() -> new CustomException("Persona con ID " + dto.getPersona().getPerApellido() + " no encontrada."));

		Usuario usuario = mapper.toEntity(dto);
		usuario.setEstado(estado);
		usuario.setUsuPassword(passwordEncoder.encode(dto.getUsuPassword()));
		usuario.setPersona(persona);

		Usuario savedUsuario = repository.save(usuario);
		return mapper.toDTO(savedUsuario);
	}

	@Override
	public UsuarioAdminDTO update(UsuarioAdminDTO dto) {
		Usuario existingUsuario = repository.findById(dto.getUsuId())
				.orElseThrow(() -> new CustomException("Usuario con ID " + dto.getUsuId() + " no encontrado."));

		if (!existingUsuario.getUsuUsername().equals(dto.getUsuUsername())
				&& repository.existsByUsuUsername(dto.getUsuUsername())) {
			throw new CustomException("El nombre de usuario '" + dto.getUsuUsername() + "' ya está registrado.");
		}

		Estado estado = estadoRepository.findById(dto.getEstado().getEstadoId()).orElseThrow(
				() -> new CustomException("Estado con ID " + dto.getEstado().getEstadoId() + " no encontrado."));

		PersonaFisica persona = personaRepository.findById(dto.getPersona().getPerId()).orElseThrow(
				() -> new CustomException("Persona con ID " + dto.getPersona().getPerId() + " no encontrada."));

		existingUsuario.setEstado(estado);
		existingUsuario.setPersona(persona);
		existingUsuario.setUsuUsername(dto.getUsuUsername());
		existingUsuario.setUsuTwoFactorEnabled(dto.isUsuTwoFactorEnabled());

		Usuario updatedUsuario = repository.save(existingUsuario);
		return mapper.toDTO(updatedUsuario);
	}

	@Override
	public void deleteById(Long id) {
		if (!repository.existsById(id)) {
			throw new CustomException("Usuario con ID " + id + " no encontrado.");
		}
		repository.deleteById(id);
	}

	@Override
	public UsuarioClienteDTO findByUsername(String username) {
		Usuario usuario = repository.findByUsuUsername(username)
				.orElseThrow(() -> new CustomException("Usuario con nombre '" + username + "' no encontrado."));
		return mapper.toUsuarioClienteDTO(usuario);
	}

	@Override
	public UsuarioClienteDTO registerClient(UsuarioClienteDTO usuario) {

		validarExistencia(usuario);

		// Obtiene el estado activo
		Estado estado = estadoRepository.findById(1L)
				.orElseThrow(() -> new CustomException("Estado con ID 1 no encontrado."));

		// Obtiene el rol CLIENTE, lo crea si no existe
		Role role = roleRepository.findByRoleDescription("CLIENTE")
				.orElseGet(() -> roleRepository.save(new Role("CLIENTE")));
		if(usuario.isUsuTwoFactorEnabled()) {
			usuario.setUsuTwoFactorSecret(SecretGenerator.generate().toString());
		}

		// Convierte el DTO a entidad y asigna los valores necesarios
		Usuario entity = mapper.toUsuario(usuario);
		entity.setEstado(estado);
		entity.setUsuPassword(passwordEncoder.encode(usuario.getUsuPassword()));
		entity.getRoles().add(role);
		entity.setUsuFecCreacion(new Date());

		// Guarda el usuario y devuelve el DTO
		return mapper.toUsuarioClienteDTO(repository.save(entity));
	}

	// Método auxiliar para evitar repetición de código en las validaciones
	private void validarExistencia(UsuarioClienteDTO usuario) {
		if (repository.existsByUsuUsername(usuario.getUsuUsername())) {
			throw new CustomException("El nombre de usuario '" + usuario.getUsuUsername() + "' ya está registrado.");
		}
		if (personaRepository.existsByPerNroCedula(usuario.getPersona().getPerNroCedula())) {
			throw new CustomException(
					"La cédula '" + usuario.getPersona().getPerNroRuc() + "' ya está registrada.");
		}
		if (personaRepository.existsByPerEmail(usuario.getPersona().getPerEmail())) {
			throw new CustomException("El correo '" + usuario.getPersona().getPerEmail() + "' ya está registrado.");
		}
		if (personaRepository.existsByPerTelefono(usuario.getPersona().getPerTelefono())) {
			throw new CustomException(
					"El teléfono '" + usuario.getPersona().getPerTelefono() + "' ya está registrado.");
		}
		if (StringUtils.isNotBlank(usuario.getPersona().getPerNroRuc())
				&& personaRepository.existsByPerNroRuc(usuario.getPersona().getPerNroRuc())) {
			throw new CustomException("El RUC '" + usuario.getPersona().getPerNroRuc() + "' ya está registrado.");
		}
	}

}
