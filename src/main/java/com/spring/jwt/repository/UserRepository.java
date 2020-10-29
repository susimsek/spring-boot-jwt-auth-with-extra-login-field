package com.spring.jwt.repository;


import com.spring.jwt.model.User;
import com.spring.jwt.model.ids.UserId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, UserId> {
	Optional<User> findByUsernameAndProvider_Name(String username, String providerName);
	Optional<User> findById_UserIdAndProvider_Name(String id, String providerName);
	Boolean existsByUsernameAndProvider_Name(String username, String  providerName);
}