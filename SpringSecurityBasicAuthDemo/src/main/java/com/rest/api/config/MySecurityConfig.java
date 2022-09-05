//package com.rest.api.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
//@Configuration
//@EnableWebSecurity
//public class MySecurityConfig {
//
//	@Bean
//	public UserDetailsService getUserService() {
//		InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
//		UserDetails userDetails = User.withUsername("ram").password("ram123").authorities("read").build();
//		userDetailsManager.createUser(userDetails);
//		return userDetailsManager;
//	}
//
//	@SuppressWarnings("deprecation")
//	@Bean
//	public PasswordEncoder getPassword() {
//		return NoOpPasswordEncoder.getInstance();
//	}
//
//}
