package com.rest.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.rest.api.exceptions.AuthenticationEntryPointJwt;
import com.rest.api.filters.JwtAuthenticateFilter;

@Configuration
public class SecurityConfiguration {
	
	@Autowired
	private JwtAuthenticateFilter authenticateFilter;
	
	@Autowired
	private AuthenticationEntryPointJwt authenticationEntryPoint;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http .csrf().disable()
               
		       .authorizeRequests().antMatchers("/auth/**").permitAll().
		      
				anyRequest().authenticated().and().

				exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).and()
				
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        
                 http.addFilterBefore(authenticateFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
