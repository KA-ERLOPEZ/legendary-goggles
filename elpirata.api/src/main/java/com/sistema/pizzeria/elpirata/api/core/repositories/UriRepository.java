package com.sistema.pizzeria.elpirata.api.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.pizzeria.elpirata.api.core.entities.Uri;

public interface UriRepository extends JpaRepository<Uri, Long> {
	boolean existsByUriPath(String uriPath);
}
