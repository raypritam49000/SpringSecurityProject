package com.rest.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/surya/**").permitAll().anyRequest().authenticated().and().formLogin()
				.loginPage("/surya/login").loginProcessingUrl("/surya/show");

	}

	@Bean
	@Override
	public UserDetailsService userDetailsService() {

		// User Role
		UserDetails theUser = User.withUsername("surya")
				.passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder()::encode)
				.password("surya123").roles("USER").build();

		// Manager Role
		UserDetails theManager = User.withUsername("admin")
				.passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder()::encode)
				.password("admin123").roles("MANAGER").build();

		InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();

		userDetailsManager.createUser(theUser);
		userDetailsManager.createUser(theManager);

		return userDetailsManager;

	}

}
