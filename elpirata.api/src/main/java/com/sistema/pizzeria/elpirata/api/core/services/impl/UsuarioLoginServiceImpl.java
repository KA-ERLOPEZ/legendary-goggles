package com.sistema.pizzeria.elpirata.api.core.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sistema.pizzeria.elpirata.api.core.entities.Usuario;
import com.sistema.pizzeria.elpirata.api.core.entities.UsuarioLogin;
import com.sistema.pizzeria.elpirata.api.core.exceptions.CustomException;
import com.sistema.pizzeria.elpirata.api.core.mappers.UsuarioLoginMapper;
import com.sistema.pizzeria.elpirata.api.core.repositories.UsuarioLoginRepository;
import com.sistema.pizzeria.elpirata.api.core.repositories.UsuarioRepository;
import com.sistema.pizzeria.elpirata.api.core.services.UsuarioLoginService;
import com.sistema.pizzeria.elpirata.api.web.dto.UsuarioLoginDTO;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UsuarioLoginServiceImpl implements UsuarioLoginService {

    private final UsuarioLoginRepository usuarioLoginRepository;
    private final UsuarioRepository usuarioRepository;
    private final UsuarioLoginMapper usuarioLoginMapper;

  
    public UsuarioLoginServiceImpl(UsuarioLoginRepository usuarioLoginRepository, 
                                    UsuarioRepository usuarioRepository, 
                                    UsuarioLoginMapper usuarioLoginMapper) {
        this.usuarioLoginRepository = usuarioLoginRepository;
        this.usuarioRepository = usuarioRepository;
        this.usuarioLoginMapper = usuarioLoginMapper;
    }

    @Override
    public List<UsuarioLoginDTO> findAll() {
        List<UsuarioLogin> logins = usuarioLoginRepository.findAll();
        return logins.stream()
                     .map(usuarioLoginMapper::toDTO)
                     .collect(Collectors.toList());
    }

    @Override
    public UsuarioLoginDTO findById(Long id) {
        UsuarioLogin login = usuarioLoginRepository.findById(id)
                .orElseThrow(() -> new CustomException("Login no encontrado con ID: " + id));
        return usuarioLoginMapper.toDTO(login);
    }

    @Override
    public UsuarioLoginDTO save(UsuarioLoginDTO dto) {
        UsuarioLogin login = usuarioLoginMapper.toEntity(dto);

        if (dto.getUsuarioId() != 0) {
            Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                    .orElseThrow(() -> new CustomException("Usuario no encontrado con ID: " + dto.getUsuarioId()));
            login.setUsuario(usuario);
        }

        UsuarioLogin savedLogin = usuarioLoginRepository.save(login);
        return usuarioLoginMapper.toDTO(savedLogin);
    }

    @Override
    public UsuarioLoginDTO update(UsuarioLoginDTO dto) {
        if (dto.getLoginId() == 0) {
            throw new IllegalStateException("El ID del login no puede ser 0 para la actualización.");
        }

        UsuarioLogin existingLogin = usuarioLoginRepository.findById(dto.getLoginId())
                .orElseThrow(() -> new CustomException("Login no encontrado con ID: " + dto.getLoginId()));

        existingLogin.setLoginDevice(dto.getLoginDevice());
        existingLogin.setLoginFailureReason(dto.getLoginFailureReason());
        existingLogin.setLoginIp(dto.getLoginIp());
        existingLogin.setLoginSuccess(dto.isLoginSuccess());
        existingLogin.setLoginTimestamp(dto.getLoginTimestamp());

        if (dto.getUsuarioId() != 0) {
            Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                    .orElseThrow(() -> new CustomException("Usuario no encontrado con ID: " + dto.getUsuarioId()));
            existingLogin.setUsuario(usuario);
        }

        UsuarioLogin updatedLogin = usuarioLoginRepository.save(existingLogin);
        return usuarioLoginMapper.toDTO(updatedLogin);
    }

    @Override
    public void deleteById(Long id) {
        if (!usuarioLoginRepository.existsById(id)) {
            throw new CustomException("Login no encontrado con ID: " + id);
        }
        usuarioLoginRepository.deleteById(id);
    }

    // Método para encontrar logins exitosos
    public List<UsuarioLoginDTO> findSuccessfulLogins() {
        List<UsuarioLogin> successfulLogins = usuarioLoginRepository.findByLoginSuccess(true);
        return successfulLogins.stream()
                               .map(usuarioLoginMapper::toDTO)
                               .collect(Collectors.toList());
    }

    // Método para encontrar logins fallidos
    public List<UsuarioLoginDTO> findFailedLogins() {
        List<UsuarioLogin> failedLogins = usuarioLoginRepository.findByLoginSuccess(false);
        return failedLogins.stream()
                           .map(usuarioLoginMapper::toDTO)
                           .collect(Collectors.toList());
    }

    // Método para filtrar logins por IP
    public List<UsuarioLoginDTO> findByIp(String ip) {
        List<UsuarioLogin> logins = usuarioLoginRepository.findByLoginIp(ip);
        return logins.stream()
                     .map(usuarioLoginMapper::toDTO)
                     .collect(Collectors.toList());
    }

	@Override
	public List<UsuarioLoginDTO> findByTimestampBetween(Date startDate, Date endDate) {
		List<UsuarioLogin> logins = usuarioLoginRepository.findByTimestampBetween(startDate, endDate);
	    if (logins.isEmpty()) {
	        throw new CustomException("No se encontraron registros de login en el rango de fechas especificado.");
	    }
	    return logins.stream()
	            .map(usuarioLoginMapper::toDTO)
	            .collect(Collectors.toList());
	}
}
