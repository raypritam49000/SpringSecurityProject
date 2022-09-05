package com.employee.management.system.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.employee.management.system.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
// Spring Boot 2.6.7
// Spring Boot 2.7.0
public class SpringWebSecurityConfig {

	@Autowired
	private CustomSuccessHandler customSuccessHandler;

	@Bean
	public CustomUserDetailsService customUserDetailsService() {
		return new CustomUserDetailsService();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(customUserDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authenticationProvider(authenticationProvider());

		http.csrf().disable().authorizeHttpRequests()
				.antMatchers("/webjars/**", "/error/**", "/resources/**", "/images/**").permitAll()
				.antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/user/**").hasRole("USER")
				.antMatchers("/**").authenticated()
				.and()
				.formLogin().loginPage("/login").loginProcessingUrl("/do_login")
				.successHandler(customSuccessHandler).permitAll()
				.and().exceptionHandling().accessDeniedPage("/Access_Denied");;

		return http.build();
	}


}
