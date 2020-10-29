package com.spring.jwt.repository;

import com.spring.jwt.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProviderRepository extends JpaRepository<Provider, Long> {
	Optional<Provider> findByName(String name);
	Boolean existsByName(String name);
}
