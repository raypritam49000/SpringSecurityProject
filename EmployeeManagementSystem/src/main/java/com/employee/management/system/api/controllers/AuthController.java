package com.employee.management.system.api.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.management.system.api.dto.JwtResponse;
import com.employee.management.system.api.dto.LoginRequest;
import com.employee.management.system.api.dto.MessageResponse;
import com.employee.management.system.api.dto.UserDto;
import com.employee.management.system.api.entity.User;
import com.employee.management.system.api.repo.UserRepository;
import com.employee.management.system.api.utils.JwtUtils;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private ModelMapper modelMapper;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody UserDto userDto) {

		if (userRepository.existsByUsername(userDto.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Username is already taken!", false));
		}

		if (userRepository.existsByEmail(userDto.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Email is already in use!", false));
		}

		User user = this.modelMapper.map(userDto, User.class);

		user.setPassword(encoder.encode(userDto.getPassword()));

		User registerUser = userRepository.save(user);
		
		HashMap<Object, Object> registerResponse = new HashMap<>();
		registerResponse.put("message", "User Register Successfully");
		registerResponse.put("success", true);
		registerResponse.put( "user", registerUser);

		return ResponseEntity.ok(registerResponse);
	}
}