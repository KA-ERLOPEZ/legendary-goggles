package com.sistema.pizzeria.elpirata.api.core.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.pizzeria.elpirata.api.core.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

	
	/**
	 * Busca una categoria por su nombre.
	 * 
	 * @param nombre Nombre de la categoria a buscar.
	 * @return Categoria encontrada o null si no existe.
	 */
	Optional<Categoria> findByCatNombre(String nombre);

	/**
	 * Listar categorias por estado.
	 * 
	 * @param id Id del estado a buscar.
	 * @return Categoria encontrada o null si no existe.
	 */
	List<Categoria> findByEstado_EstadoId(long id);
}
