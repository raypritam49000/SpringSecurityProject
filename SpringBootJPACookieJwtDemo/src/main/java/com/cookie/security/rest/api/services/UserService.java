package com.cookie.security.rest.api.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

import com.cookie.security.rest.api.models.LoginRequest;
import com.cookie.security.rest.api.models.LoginResponse;
import com.cookie.security.rest.api.models.UserProfile;

public interface UserService {
    ResponseEntity<LoginResponse> login(LoginRequest loginRequest, String accessToken, String refreshToken);

    ResponseEntity<LoginResponse> refresh(String accessToken, String refreshToken);

    UserProfile getUserProfile();
    
    String logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);

}
