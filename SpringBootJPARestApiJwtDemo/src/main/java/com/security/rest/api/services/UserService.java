package com.security.rest.api.services;

import com.security.rest.api.entity.User;

public interface UserService {
	public abstract User findByUsername(String username);
	public abstract User saveUser(User user);
}
