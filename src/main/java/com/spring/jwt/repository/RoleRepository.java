package com.spring.jwt.repository;


import com.spring.jwt.model.Role;
import com.spring.jwt.model.enumerated.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(RoleName roleName);
}