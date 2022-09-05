package net.corejava.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Order(2)
public class CustomerSecurityConfig {

	@SuppressWarnings("deprecation")
	@Bean
	public PasswordEncoder passwordEncoder2() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Bean
	public UserDetailsService customerDetailsService() {
		return new CustomCustomerDetailsService();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider2() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(customerDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder2());
		return authenticationProvider;
	}

	@Bean
	public SecurityFilterChain filterChain2(HttpSecurity http) throws Exception {

		http.authenticationProvider(authenticationProvider2());

		http.authorizeHttpRequests().antMatchers("/").permitAll();

		http.antMatcher("/customer/**").authorizeHttpRequests().anyRequest().authenticated().and().formLogin()
				.loginPage("/customer/login").usernameParameter("email").loginProcessingUrl("/customer/login")
				.defaultSuccessUrl("/customer/home").permitAll().and().logout().logoutUrl("/customer/logout")
				.logoutSuccessUrl("/");

		return http.build();
	}
}
