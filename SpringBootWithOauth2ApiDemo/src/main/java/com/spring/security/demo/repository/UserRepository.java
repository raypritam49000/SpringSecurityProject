package com.spring.security.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.security.demo.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	public abstract User findByLogin(String login);
}