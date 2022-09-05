package com.rest.api.controllers;

import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.api.config.CustomAuthenticationManager;
import com.rest.api.entity.User;
import com.rest.api.entity.payload.JWTRequest;
import com.rest.api.entity.payload.UserDto;
import com.rest.api.repository.UserRepository;
import com.rest.api.services.impl.CustomUserDetailsService;
import com.rest.api.utils.JwtTokenUtil;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private JwtTokenUtil jwtTokenUtils;

	@Autowired
	private CustomAuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private ModelMapper modelMapper;

	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody JWTRequest jwtRequest) {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));

			final UserDetails userDetails = customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());

			final String token = jwtTokenUtils.generateToken(userDetails);

			if (token != null) {

				com.rest.api.entity.User user = userRepository.findByUsername(jwtRequest.getUsername()).get();

				return ResponseEntity.ok(Map.of("message", "Login suceessfully", "body", user, "token", token, "type",
						"Bearer", "statusCode", 200, "isSuccess", true));
			}

			return ResponseEntity.ok(Map.of("message", "failed login", "isSuccess", false, "statusCode", 400));

		} catch (BadCredentialsException e) {
			return ResponseEntity.ok(Map.of("message", e.getMessage(), "isSuccess", false, "statusCode", 501));
		}
	}
	
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody UserDto userDto) {
		
		try {
			
			userDto.setPassword(this.passwordEncoder.encode(userDto.getPassword()));
			User user = this.modelMapper.map(userDto, User.class);
			User registerUser = this.userRepository.save(user);
			
			if (null != registerUser  && registerUser.getId()>0) {
				return ResponseEntity.ok(Map.of("message", "Register suceessfully", "body", registerUser,"statusCode", 201, "isSuccess", true));
			}
			else {
				return ResponseEntity.ok(Map.of("message", "Bad Request", "isSuccess", false, "statusCode", 400));
			}
			
		} catch (Exception e) {
			return ResponseEntity.ok(Map.of("message", e.getMessage(), "isSuccess", false, "statusCode", 501));
		}
		
	}

}