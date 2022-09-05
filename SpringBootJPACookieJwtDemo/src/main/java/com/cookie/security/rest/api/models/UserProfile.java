package com.cookie.security.rest.api.models;

import java.util.ArrayList;
import java.util.List;

import com.cookie.security.rest.api.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile {
	private Long id;
	private String username;

	List<Role> roles = new ArrayList<>();

}
