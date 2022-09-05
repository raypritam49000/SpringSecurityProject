package com.security.jwt.rest.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.jwt.rest.api.models.Pack;
import com.security.jwt.rest.api.services.PackService;

@RestController
@RequestMapping("/rest/api")
@CrossOrigin(origins = "*", maxAge = 3600)
public class TestController {

	@Autowired
	PackService packService;

	@PostMapping("/addpack")
	public Pack addPack(@RequestBody Pack pack) {

		return packService.addPack(pack);
	}

	@GetMapping("/getpack")
	public List<Pack> getAllPacks() {
		return packService.getAllPacks();
	}

}
