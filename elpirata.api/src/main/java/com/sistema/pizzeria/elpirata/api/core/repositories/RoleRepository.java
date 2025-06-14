package com.sistema.pizzeria.elpirata.api.core.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.pizzeria.elpirata.api.core.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	List<Role> findByRoleEnabled(boolean roleEnabled);
	Optional<Role> findByRoleDescription(String roleDescripcion);
}
