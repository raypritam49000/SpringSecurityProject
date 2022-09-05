package com.security.rest.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.security.rest.api.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	public abstract User findByUsername(String username);
}
