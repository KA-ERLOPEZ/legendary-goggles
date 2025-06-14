package com.sistema.pizzeria.elpirata.api.core.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.sistema.pizzeria.elpirata.api.core.commons.GenericMapper;
import com.sistema.pizzeria.elpirata.api.core.entities.Producto;
import com.sistema.pizzeria.elpirata.api.web.dto.ProductoDTO;

@Mapper(
	    componentModel = "spring",
	    uses = { CategoriaMapper.class, EstadoMapper.class, MarcaMapper.class, 
	             ProveedorMapper.class, SaborMapper.class, TamanoMapper.class },
	    unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE,
	    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
	)
	public interface ProductoMapper extends GenericMapper<Producto, ProductoDTO> {

	    @Mapping(target = "categoria.catId", source = "catId")
	    @Mapping(target = "categoria.catNombre", source = "catNombre")
	    @Mapping(target = "marca.marId", source = "marId")
	    @Mapping(target = "marca.marNombre", source = "marNombre")
	    @Mapping(target = "proveedor.provId", source = "provId")
	    @Mapping(target = "proveedor.provRazonSocial", source = "provRazonSocial")
	    @Mapping(target = "sabor.sabId", source = "sabId")
	    @Mapping(target = "sabor.sabNombre", source = "sabNombre")
	    @Mapping(target = "tamano.tamId", source = "tamId")
	    @Mapping(target = "tamano.tamDescripcion", source = "tamDescripcion")
	    @Mapping(target = "estado.estadoId", source = "estadoId")
	    @Mapping(target = "estado.estadoNombre", source = "estadoNombre")
	    Producto toEntity(ProductoDTO productoDTO);

	    @InheritInverseConfiguration
	    ProductoDTO toDTO(Producto producto);
	}

