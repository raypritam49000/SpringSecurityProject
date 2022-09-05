package com.security.rest.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.rest.api.common.Constants;
import com.security.rest.api.common.Response;
import com.security.rest.api.entity.User;
import com.security.rest.api.models.JwtRequest;
import com.security.rest.api.models.JwtResponse;
import com.security.rest.api.models.UserVO;
import com.security.rest.api.services.UserService;
import com.security.rest.api.services.jwt.CustomUserDetailsService;
import com.security.rest.api.utils.JWTUtility;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/rest/api")
public class AuthController {

	@Autowired
	private JWTUtility jwtUtility;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/login")
	public Response login(@RequestBody JwtRequest jwtRequest) throws Exception {

		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));

			final UserDetails userDetails = customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());

			final String token = jwtUtility.generateToken(userDetails);
			
			if (token != null) {

				List<Object> userlst = new ArrayList<>();

				User user = userService.findByUsername(jwtRequest.getUsername());

				UserVO userVO = new UserVO();
				BeanUtils.copyProperties(user, userVO);
				userlst.add(userVO);
				userlst.add(new JwtResponse("Bearer", token));

				return new Response(HttpStatus.OK, Constants.HTTP_STATUS_CODE_SCCUESS, userlst, "Login suceessfully",
						Constants.STATUS_SUCCESS, Constants.STATUS_SUCCESS_MESSAGE);

			}

			return new Response(HttpStatus.BAD_REQUEST, Constants.HTTP_STATUS_CODE_BAD_REQUEST, new ArrayList<>(),
					"failed login", Constants.STATUS_FAILURE, Constants.STATUS_FAILURE_MESSAGE);

		} catch (BadCredentialsException e) {
			return new Response(HttpStatus.BAD_REQUEST, Constants.HTTP_STATUS_CODE_BAD_REQUEST, new ArrayList<>(),
					e.toString(), Constants.STATUS_FAILURE, Constants.STATUS_FAILURE_MESSAGE);
		}
	}

	@PostMapping("/register")
	public Response createUser(@RequestBody UserVO userVO) {
		try {

			userVO.setPassword(this.passwordEncoder.encode(userVO.getPassword()));
			User user = new User();
			BeanUtils.copyProperties(userVO, user);
			
			User userdb = this.userService.saveUser(user);
			
			if (null != userdb  && userdb.getId()  != null) {
				return new Response(HttpStatus.CREATED, Constants.HTTP_STATUS_CODE_CREATED, new ArrayList<>(),
						"User registered", Constants.STATUS_SUCCESS, Constants.STATUS_SUCCESS_MESSAGE);
			} else {
				return new Response(HttpStatus.BAD_GATEWAY, Constants.HTTP_STATUS_CODE_BAD_REQUEST, new ArrayList<>(),
						"User is not registered", Constants.STATUS_FAILURE, Constants.STATUS_FAILURE_MESSAGE);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			return new Response(HttpStatus.BAD_REQUEST, Constants.HTTP_STATUS_CODE_BAD_REQUEST, new ArrayList<>(),
					exception.toString(), Constants.STATUS_FAILURE, Constants.STATUS_FAILURE_MESSAGE);
		}
	}

}
