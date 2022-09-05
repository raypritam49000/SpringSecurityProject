package com.employee.management.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.employee.management.system.entity.User;
import com.employee.management.system.repository.UserRepository;

public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = this.userRepository.findByUsername(username);
		
		if(user==null) {
			throw new UsernameNotFoundException("User Not Found for given username");
		}
		return new CustomerUserDetails(user);
	}

}
