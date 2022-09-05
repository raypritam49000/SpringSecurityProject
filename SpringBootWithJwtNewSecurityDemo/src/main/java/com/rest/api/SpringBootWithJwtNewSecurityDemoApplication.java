package com.rest.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootWithJwtNewSecurityDemoApplication { // implements CommandLineRunner {

//	@Autowired
//	private PasswordEncoder passwordEncoder;
//
//	@Autowired
//	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWithJwtNewSecurityDemoApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		User user = new User();
//		user.setEmail("admin@gmail.com");
//		user.setName("admin");
//		user.setUsername("admin");
//		user.setPassword(this.passwordEncoder.encode("admin"));
//
//		this.userRepository.save(user);
//	}

}
