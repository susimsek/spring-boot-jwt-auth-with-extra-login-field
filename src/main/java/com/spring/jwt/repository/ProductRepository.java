package com.spring.jwt.repository;


import com.spring.jwt.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, String> {
    Optional<Product> findByName(String name);
    Boolean existsByName(String name);
}