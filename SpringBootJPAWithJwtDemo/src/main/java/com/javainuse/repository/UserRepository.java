package com.javainuse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javainuse.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	public abstract User findByUsername(String username);

}