package com.consume.rest.api.controllers;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.consume.rest.api.common.Response;
import com.consume.rest.api.vo.JwtRequest;
import com.consume.rest.api.vo.User;

@RestController
@RequestMapping("/rest/auth")
public class AuthController {

	@Autowired
	private RestTemplate restTemplate;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody JwtRequest jwtRequest, HttpSession httpSession) {
		Response response = null;
		try {
			final String baseUrl = "http://localhost:9999/rest/api/login";
			URI uri = new URI(baseUrl);

			HttpHeaders headers = new HttpHeaders();
			headers.set("X-COM-PERSIST", "true");
			headers.set("X-COM-LOCATION", "USA");

			HttpEntity<JwtRequest> requestEntity = new HttpEntity<JwtRequest>(jwtRequest, headers);

			ResponseEntity<Response> responseEntity = restTemplate.exchange(uri, HttpMethod.POST, requestEntity,
					Response.class);

			response = responseEntity.getBody();

			//httpSession.setAttribute("user", response);

			// System.out.println(httpSession.getAttribute("user"));
			
			//System.out.println(((LinkedHashMap<?, ?>)response.getBody().get(1)).get("jwtToken"));
			//return ResponseEntity.ok((LinkedHashMap<?, ?>)response.getBody().get(1));
			
			httpSession.setAttribute("token",((LinkedHashMap<?, ?>)response.getBody().get(1)).get("jwtToken"));
			return ResponseEntity.ok(response);

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return ResponseEntity.ok(Map.of("message",HttpStatus.INTERNAL_SERVER_ERROR,"success",false));
		}

	}
	
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody User user) {
		Response response = null;
		try {
			final String baseUrl = "http://localhost:9999/rest/api/register";
			URI uri = new URI(baseUrl);

			HttpHeaders headers = new HttpHeaders();
			headers.set("X-COM-PERSIST", "true");
			headers.set("X-COM-LOCATION", "USA");

			HttpEntity<User> requestEntity = new HttpEntity<User>(user, headers);

			ResponseEntity<Response> responseEntity = restTemplate.exchange(uri, HttpMethod.POST, requestEntity,
					Response.class);

			response = responseEntity.getBody();
			
			return ResponseEntity.ok(response);

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return ResponseEntity.ok(Map.of("message",HttpStatus.INTERNAL_SERVER_ERROR,"success",false));
		}

	}

}
