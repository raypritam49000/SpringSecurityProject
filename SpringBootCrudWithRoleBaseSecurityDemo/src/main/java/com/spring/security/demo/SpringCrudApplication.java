package com.spring.security.demo;

import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.spring.security.demo.model.Role;
import com.spring.security.demo.model.User;
import com.spring.security.demo.repository.UserRepository;

@SpringBootApplication
public class SpringCrudApplication {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	

	public static void main(String[] args) {
		SpringApplication.run(SpringCrudApplication.class, args);
	}
	
	//@PostConstruct
	public void init() {
		Role role1 = new Role("ADMIN");
		Role role2 = new Role("CREATOR");
		Role role3 = new Role("EDITOR");
		Role role4 = new Role("USER");
		
		User user1 = new User("admin",this.passwordEncoder.encode("admin"),true,Set.of(role1));
		User user2 = new User("creator",this.passwordEncoder.encode("creator"),true,Set.of(role2));
		User user3 = new User("editor",this.passwordEncoder.encode("editor"),true,Set.of(role3));
		User user4 = new User("user",this.passwordEncoder.encode("user"),true,Set.of(role4));
		
		this.userRepository.saveAll(List.of(user1,user2,user3,user4));
	}

}
