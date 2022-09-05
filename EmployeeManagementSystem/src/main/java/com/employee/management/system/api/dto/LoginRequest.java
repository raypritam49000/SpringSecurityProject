package com.employee.management.system.api.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
	
	@NotEmpty(message = "UserName is required !!!")
	@Size(min=4, message = "UserName should be atleast 4 characters")
	@Size(max=35, message = "UserName should not be greater than 30 characters")
	private String username;
	
	@NotEmpty(message = "Password is required !!!")
	@Size(min=4, message = "Password should be atleast 4 characters")
	@Size(max=35, message = "Password should not be greater than 30 characters")
	private String password;
	
}
