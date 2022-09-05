package com.spring.security.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spring.security.demo.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	@Query("SELECT u from User u Where u.username = :username")
	public User getUserByUsername(@Param("username") String username);

}
