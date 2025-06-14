package com.sistema.pizzeria.elpirata.api.core.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistema.pizzeria.elpirata.api.core.entities.Uri;
import com.sistema.pizzeria.elpirata.api.core.exceptions.CustomException;
import com.sistema.pizzeria.elpirata.api.core.mappers.UriMapper;
import com.sistema.pizzeria.elpirata.api.core.repositories.UriRepository;
import com.sistema.pizzeria.elpirata.api.core.services.UriService;
import com.sistema.pizzeria.elpirata.api.web.dto.UriDTO;

@Service
@Transactional
public class UriServiceImpl implements UriService {

    @Autowired
    private UriRepository uriRepository;

    @Autowired
    private UriMapper uriMapper;

    @Override
    public List<UriDTO> findAll() {
        return uriRepository.findAll()
                .stream()
                .map(uriMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UriDTO findById(Long id) {
        return uriRepository.findById(id)
                .map(uriMapper::toDTO)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró la URI con ID: " + id));
    }

    @Override
    public UriDTO save(UriDTO dto) {
        if (uriRepository.existsByUriPath(dto.getUriPath())) {
            throw new CustomException("La URI ya existe: " + dto.getUriPath());
        }

        Uri uri = uriMapper.toEntity(dto);
        Uri savedUri = uriRepository.save(uri);
        return uriMapper.toDTO(savedUri);
    }

    @Override
    public UriDTO update(UriDTO dto) {
        Optional<Uri> existingUri = uriRepository.findById(dto.getUriId());
        if (existingUri.isEmpty()) {
            throw new CustomException("No se encontró la URI con ID: " + dto.getUriId());
        }

        Uri uri = existingUri.get();
        uri.setUriDescription(dto.getUriDescription());
        uri.setUriPath(dto.getUriPath());

        Uri updatedUri = uriRepository.save(uri);
        return uriMapper.toDTO(updatedUri);
    }

    @Override
    public void deleteById(Long id) {
        if (!uriRepository.existsById(id)) {
            throw new CustomException("No se encontró la URI con ID: " + id);
        }
        uriRepository.deleteById(id);
    }
}

