package com.security.jwt.rest.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.security.jwt.rest.api.models.Pack;
import com.security.jwt.rest.api.repo.PackRepository;

@Service
public class PackServiceImpl implements PackService {
	@Autowired
	private PackRepository repository;

	@Override
	public Pack addPack(Pack user) {
		return repository.save(user);
	}

	@Override
	public List<Pack> getAllPacks() {
		return repository.findAll();
	}
}
