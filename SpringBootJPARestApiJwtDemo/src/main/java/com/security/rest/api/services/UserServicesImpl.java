package com.security.rest.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.security.rest.api.entity.User;
import com.security.rest.api.repositories.UserRepository;

@Service
public class UserServicesImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;

	@Cacheable(value = "userInfo",key = "#username")
	@Override
	public User findByUsername(String username) {
		return this.userRepository.findByUsername(username);
	}

	@Override
	public User saveUser(User user) {
		return this.userRepository.save(user);
	}

}
