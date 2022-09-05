package com.cookie.security.rest.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cookie.security.rest.api.repo.UserRepository;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SpringBootJpaCookieJwtDemoApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJpaCookieJwtDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		User user = new User();
//		user.setEmail("admin");
//		user.setUsername("admin");
//		user.setPassword(passwordEncoder.encode("admin"));
//
//		Role role1 = new Role();
//		role1.setName(ERole.ROLE_ADMIN);
//
//		Role role2 = new Role();
//		role2.setName(ERole.ROLE_ADMIN);
//
//		this.roleRepository.save(role1);
//		this.roleRepository.save(role2);
//
//		Set<Role> roles = new HashSet<Role>();
//		roles.add(role1);
//		roles.add(role2);
//
//		user.setRoles(roles);
//
//		userRepository.save(user);

//		User user = new User();
//		user.setEmail("admin");
//		user.setUsername("admin");
//		user.setPassword(passwordEncoder.encode("admin"));
//		user.getRoles().add(Role.ROLE_ADMIN);
//		userRepository.save(user);

	}

}
