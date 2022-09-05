package com.security.jwt.rest.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.security.jwt.rest.api.models.Pack;


@Repository
public interface PackRepository extends JpaRepository<Pack, Integer> {
	}
