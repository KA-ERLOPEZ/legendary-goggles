package com.sistema.pizzeria.elpirata.api.core.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sistema.pizzeria.elpirata.api.core.exceptions.CustomException;
import com.sistema.pizzeria.elpirata.api.core.mappers.ProveedorMapper;
import com.sistema.pizzeria.elpirata.api.core.repositories.ProveedorRepository;
import com.sistema.pizzeria.elpirata.api.core.services.ProveedorService;
import com.sistema.pizzeria.elpirata.api.web.dto.ProveedorDTO;

@Service
public class ProveedorServiceImpl implements ProveedorService {

	private final ProveedorRepository proveedorRepository;
	private final ProveedorMapper proveedorMapper;
	
	/**
	 * Constructor for ProveedorServiceImpl.
	 *
	 * @param proveedorRepository the ProveedorRepository to use
	 * @param proveedorMapper     the ProveedorMapper to use
	 */
	public ProveedorServiceImpl(ProveedorRepository proveedorRepository, ProveedorMapper proveedorMapper) {
		this.proveedorRepository = proveedorRepository;
		this.proveedorMapper = proveedorMapper;
	}
	
	
	@Override
	public List<ProveedorDTO> findAll() {
	
		return proveedorRepository.findAll().stream().map(proveedorMapper::toDTO).toList();
	}

	@Override
	public ProveedorDTO findById(Long id) {
		
		return proveedorRepository.findById(id).map(proveedorMapper::toDTO)
				.orElseThrow(() -> new CustomException("Proveedor not found with id: " + id));
	}

	@Override
	public ProveedorDTO save(ProveedorDTO dto) {
		if (existsByProvRazonSocial(dto.getProvRazonSocial())) {
			throw new CustomException("Ya exite Proveedor con Razòn social: " + dto.getProvRazonSocial());
		}
		return null;
	}

	@Override
	public ProveedorDTO update(ProveedorDTO dto) {
		
		ProveedorDTO existingProveedor = findById(dto.getProvId());
		
		if (dto.getProvRazonSocial().equals(existingProveedor.getProvRazonSocial()) && dto.getProvId() != existingProveedor.getProvId()) {
			throw new CustomException("Ya exite Proveedor con Razòn social: " + dto.getProvRazonSocial());
		}
		if (dto.getProvRuc().equals(existingProveedor.getProvRuc())
				&& dto.getProvId() != existingProveedor.getProvId()) {
			throw new CustomException("Ya exite Proveedor con RUC: " + dto.getProvRuc());
		}
		if (dto.getProvTelefono().equals(existingProveedor.getProvTelefono())
				&& dto.getProvId() != existingProveedor.getProvId()) {
			throw new CustomException("Ya exite Proveedor con Teléfono: " + dto.getProvTelefono());
		}
		if (dto.getProvEmail().equals(existingProveedor.getProvEmail())
				&& dto.getProvId() != existingProveedor.getProvId()) {
			throw new CustomException("Ya exite Proveedor con Email: " + dto.getProvEmail());
		}
	
		return proveedorMapper.toDTO(proveedorRepository.save(proveedorMapper.toEntity(dto)));
	}

	@Override
	public void deleteById(Long id) {
		
		ProveedorDTO existingProveedor = findById(id);

		if (existingProveedor.getEstadoId() == 1) {
			throw new CustomException("No se puede eliminar el Proveedor con id: " + id);
		}

		proveedorRepository.deleteById(id);

	}

	@Override
	public ProveedorDTO findByProvRuc(String ruc) {
		
		return proveedorRepository.findByProvRuc(ruc).map(proveedorMapper::toDTO)
				.orElseThrow(() -> new CustomException("Proveedor not found with RUC: " + ruc));
	}

	@Override
	public List<ProveedorDTO> findByEstado_EstadoId(long estadoId) {
		
		return proveedorRepository.findByEstado_EstadoId(estadoId).stream().map(proveedorMapper::toDTO).toList();
	}

	@Override
	public boolean existsByProvRazonSocial(String provRazonSocial) {
		// TODO Auto-generated method stub
		return proveedorRepository.existsByProvRazonSocial(provRazonSocial);
	}


	@Override
	public List<ProveedorDTO> findByProvRazonSocialContainsIgnoreCase(String provRazonSocial) {
		
		return proveedorRepository.findByProvRazonSocialContainsIgnoreCase(provRazonSocial).stream()
				.map(proveedorMapper::toDTO).toList();
	}


	@Override
	public List<ProveedorDTO> findByProvRucContainsIgnoreCase(String provRuc) {
		
		return proveedorRepository.findByProvRucContainsIgnoreCase(provRuc).stream().map(proveedorMapper::toDTO)
				.toList();
	}

}
