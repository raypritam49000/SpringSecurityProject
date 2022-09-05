package com.rest.api.entity.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
	private int id;
	private String name;
	private String username;
	private String password;
	private String email;
}
