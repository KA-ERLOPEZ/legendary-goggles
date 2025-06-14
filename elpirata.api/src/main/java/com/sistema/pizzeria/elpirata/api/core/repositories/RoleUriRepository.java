package com.sistema.pizzeria.elpirata.api.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.pizzeria.elpirata.api.core.entities.RoleUri;
import com.sistema.pizzeria.elpirata.api.core.entities.RoleUriPK;

public interface RoleUriRepository extends JpaRepository<RoleUri, RoleUriPK> {

}
