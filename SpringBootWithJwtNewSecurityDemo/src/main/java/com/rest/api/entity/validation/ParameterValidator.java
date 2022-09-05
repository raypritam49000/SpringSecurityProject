package com.rest.api.entity.validation;

import org.springframework.stereotype.Component;

import com.rest.api.entity.payload.UserDto;

@Component
public class ParameterValidator {

	public Boolean validationForUser(UserDto user) {
		if (user != null && !user.getName().equals("") && !user.getEmail().equals("") && !user.getPassword().equals("")
				&& !user.getUsername().equals("")) {
			return true;
		} else {
			return false;
		}
	}

	
}
