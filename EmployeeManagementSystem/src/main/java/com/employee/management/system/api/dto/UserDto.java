package com.employee.management.system.api.dto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	
	private Long userId;
	
	@NotEmpty(message = "UserName is required !!!")
	@Size(min=4, message = "UserName should be atleast 4 characters")
	@Size(max=35, message = "UserName should not be greater than 30 characters")
	private String username;
	
	@NotEmpty(message = "Password is required !!!")
	@Size(min=4, message = "Password should be atleast 4 characters")
	@Size(max=35, message = "Password should not be greater than 30 characters")
	private String password;
	
	@NotEmpty(message = "Email is required  !!!")
	@Email(message = "Email address is not valid !!", regexp = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\\.[a-zA-Z.]{2,5}")
	private String email;
	
	@NotEmpty(message = "Roles is required  !!!")
	private List<String> roles;
}