//package com.rest.api.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@EnableWebSecurity
//@Configuration
//public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter{
//
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		// set your configuration on the auth Object
//		
//		auth.inMemoryAuthentication()
//		.withUser("ram")
//		.password("123")
//		.roles("USER")
//		.and()
//		.withUser("admin")
//		.password("9211")
//		.roles("ADMIN");
//	}
//	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//	  http.authorizeRequests()
//	  .antMatchers("/admin").hasRole("ADMIN")
//	  .antMatchers("/user").hasAnyRole("USER","ADMIN")
//	  .antMatchers("/","static/css","static/js").permitAll()
//	  .and().formLogin();
//	  
//	  
//	};
//
//	@SuppressWarnings("deprecation")
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return NoOpPasswordEncoder.getInstance();
//	}
//	
//}
