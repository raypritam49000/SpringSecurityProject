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
@Order(1)
public class AdminSecurityConfig {

	@Bean
	public UserDetailsService adminDetailsService() {
		return new CustomAdminDetailsService();
	}

	@SuppressWarnings("deprecation")
	@Bean
	public PasswordEncoder passwordEncoder1() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider1() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(adminDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder1());
		return authenticationProvider;
	}

	@Bean
	public SecurityFilterChain filterChain1(HttpSecurity http) throws Exception {
		
		http.authenticationProvider(authenticationProvider1());
		
		http.authorizeHttpRequests().antMatchers("/").permitAll();

		http.antMatcher("/admin/**")
		.authorizeHttpRequests().anyRequest().authenticated()
		.and().formLogin()
				.loginPage("/admin/login").usernameParameter("email").loginProcessingUrl("/admin/login")
				.defaultSuccessUrl("/admin/home").permitAll().and().logout().logoutUrl("/admin/logout")
				.logoutSuccessUrl("/");

		return http.build();
	}
}
