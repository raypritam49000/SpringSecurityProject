package com.employee.management.system;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.employee.management.system.entity.User;
import com.employee.management.system.repository.UserRepository;

@SpringBootApplication
public class EmployeeManagementSystemProjectApplication {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementSystemProjectApplication.class, args);
	}
	
	//@PostConstruct
	public void init() {
		User user = new User();
		user.setEmail("admin@gmail.com");
		user.setRole("admin");
		user.setPassword(passwordEncoder.encode("admin"));
		user.setUsername("admin");
		this.userRepository.save(user);
	}

}
