package com.example.bookbook.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bookbook.domain.entity.UserEntity;


public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
	
	Optional<UserEntity> findByEmail(String email);
}