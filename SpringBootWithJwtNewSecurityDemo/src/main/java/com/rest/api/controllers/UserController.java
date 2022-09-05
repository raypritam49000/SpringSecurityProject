package com.rest.api.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.api.entity.payload.UserDto;
import com.rest.api.entity.validation.ParameterValidator;
import com.rest.api.services.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private ParameterValidator validator;

	@PostMapping("/")
	public ResponseEntity<?> createUser(@RequestBody UserDto userDto) {
		try {

			Boolean isValid = validator.validationForUser(userDto);

			if (isValid) {
				UserDto savedUser = this.userService.createUser(userDto);
				return ResponseEntity
						.ok(Map.of("message", "User Created", "statusCode", 201, "isSucess", true, "body", savedUser));
			} else {
				return ResponseEntity
						.ok(Map.of("message", "All fields are required ", "statusCode", 400, "isSucess", false));
			}
		} catch (Exception e) {
			return ResponseEntity.ok(Map.of("message", e.getMessage(), "statusCode", 501, "isSucess", false));
		}
	}

	@GetMapping("/")
	public ResponseEntity<?> getAllUsers() {
		try {
			List<UserDto> userList = this.userService.getAllUsers();

			if (userList.size() > 0 && userList != null) {
				return ResponseEntity
						.ok(Map.of("message", "Users List", "statusCode", 200, "isSucess", true, "body", userList));
			} else {
				return ResponseEntity.ok(Map.of("message", "User Not Founds", "statusCode", 404, "isSucess", false));
			}
		} catch (Exception e) {
			return ResponseEntity.ok(Map.of("message", e.getMessage(), "statusCode", 501, "isSucess", false));
		}
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<?> getUser(@PathVariable("userId") Integer userId) {
		try {
			UserDto user = this.userService.getUser(userId);
			Boolean isValid = validator.validationForUser(user);

			if (isValid) {
				return ResponseEntity
						.ok(Map.of("message", "Users List", "statusCode", 200, "isSucess", true, "body", user));
			} else {
				return ResponseEntity.ok(Map.of("message", "User Not Founds", "statusCode", 404, "isSucess", false));
			}
		} catch (Exception e) {
			return ResponseEntity.ok(Map.of("message", e.getMessage(), "statusCode", 501, "isSucess", false));
		}
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable("userId") Integer userId) {
		try {
			Boolean isDeleted = userService.deleteUser(userId);
			if (isDeleted) {
				return ResponseEntity
						.ok(Map.of("message", "User Deleted", "statusCode", 204, "isSucess", true));
			} else {
				return ResponseEntity.ok(Map.of("message", "Error Occure while User are deleted", "statusCode", 400, "isSucess", false));
			}
		} catch (Exception e) {
			return ResponseEntity.ok(Map.of("message", e.getMessage(), "statusCode", 501, "isSucess", false));
		}
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<?> updatedUser(@PathVariable("userId") Integer userId,@RequestBody UserDto userDto) {
		try {

			Boolean isValid = validator.validationForUser(userDto);

			if (isValid && userId!=null) {
				UserDto updatedUser = this.userService.updateUser(userId, userDto);
				return ResponseEntity
						.ok(Map.of("message", "User Updated", "statusCode", 203, "isSucess", true, "body", updatedUser));
			} else {
				return ResponseEntity
						.ok(Map.of("message", "All fields are required ", "statusCode", 400, "isSucess", false));
			}
		} catch (Exception e) {
			return ResponseEntity.ok(Map.of("message", e.getMessage(), "statusCode", 501, "isSucess", false));
		}
	}
}
