package com.cookie.security.rest.api.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
	  ROLE_USER,ROLE_ADMIN;

	  public String getAuthority() {
	    return name();
	  }

}
