package com.employee.management.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.management.system.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	public abstract User findByUsername(String username);
	public abstract User findByEmail(String email);
}
