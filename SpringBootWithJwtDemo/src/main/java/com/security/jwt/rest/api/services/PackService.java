package com.security.jwt.rest.api.services;

import java.util.List;

import com.security.jwt.rest.api.models.Pack;

public interface PackService {
	Pack addPack(Pack pack);

	List<Pack> getAllPacks();
}
