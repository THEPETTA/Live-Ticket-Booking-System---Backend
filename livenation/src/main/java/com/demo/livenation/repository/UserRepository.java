package com.demo.livenation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.demo.livenation.entities.User;


public interface UserRepository extends JpaRepository<User, Long> {
	boolean existsByEmail(String email);
}
