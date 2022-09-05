package com.cookie.security.rest.api.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cookie.security.rest.api.models.ApiResponseMessage;
import com.cookie.security.rest.api.models.LoginRequest;
import com.cookie.security.rest.api.models.LoginResponse;
import com.cookie.security.rest.api.services.UserService;
import com.cookie.security.rest.api.utils.SecurityCipher;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;

	@PostMapping(value="/login")
	public ResponseEntity<LoginResponse> login(@CookieValue(name = "accessToken", required = false) String accessToken,
			@CookieValue(name = "refreshToken", required = false) String refreshToken,
			@RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String decryptedAccessToken = SecurityCipher.decrypt(accessToken);
		String decryptedRefreshToken = SecurityCipher.decrypt(refreshToken);
		return userService.login(loginRequest, decryptedAccessToken, decryptedRefreshToken);
	}

	@PostMapping(value = "/refresh", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoginResponse> refreshToken(HttpServletRequest request) {
		
		Cookie[] cookies = request.getCookies();
		
		System.out.println(cookies[0].getValue());
		System.out.println(cookies[1].getValue());
		
		String decryptedAccessToken = SecurityCipher.decrypt(cookies[0].getValue());
		String decryptedRefreshToken = SecurityCipher.decrypt(cookies[1].getValue());
		return userService.refresh(decryptedAccessToken, decryptedRefreshToken);
	}

    @GetMapping("/logout")
    public ResponseEntity<?> logOut(HttpServletRequest request, HttpServletResponse response){
       // return new ResponseEntity(new ApiResponseMessage(true, userService.logout(request, response)), HttpStatus.OK);
        return ResponseEntity.ok(new ApiResponseMessage(true, userService.logout(request, response)));

    }

	
}
