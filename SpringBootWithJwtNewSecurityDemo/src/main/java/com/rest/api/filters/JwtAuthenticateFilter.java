package com.rest.api.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.rest.api.services.impl.CustomUserDetailsService;
import com.rest.api.utils.JwtTokenUtil;

@Component
public class JwtAuthenticateFilter extends OncePerRequestFilter {

	@Autowired
	private JwtTokenUtil jwtUtility;

	@Autowired
	private CustomUserDetailsService userService;

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			FilterChain filterChain) throws ServletException, IOException {
		String authorization = httpServletRequest.getHeader("Authorization");
		System.out.println(authorization);
		String token = null;
		String userName = null;

		if (null != authorization && authorization.startsWith("Bearer ")) {
			token = authorization.substring(7);
			System.out.println(token);
			userName = jwtUtility.getUsernameFromToken(token);
			System.out.println(userName);
		}

		if (null != userName && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = userService.loadUserByUsername(userName);
             System.out.println(userDetails);
			if (jwtUtility.validateToken(token, userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());

				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}

		}
		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}

}
