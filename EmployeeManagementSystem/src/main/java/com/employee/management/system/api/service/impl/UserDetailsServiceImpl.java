package com.employee.management.system.api.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.employee.management.system.api.exceptions.ResourceNotFoundException;
import com.employee.management.system.api.repo.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	public UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username){
		com.employee.management.system.api.entity.User userEntity = userRepository.findByUsername(username)
				.orElseThrow(() -> new ResourceNotFoundException("User", " Username ", username));
		
		List<GrantedAuthority> authorities = userEntity.getRoles().stream().map(role -> new SimpleGrantedAuthority(role))
				.collect(Collectors.toList());

		return new User(userEntity.getUsername(),userEntity.getPassword(),authorities);
	}
}