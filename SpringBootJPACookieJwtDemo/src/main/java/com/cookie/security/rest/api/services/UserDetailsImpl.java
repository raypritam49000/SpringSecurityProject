package com.cookie.security.rest.api.services;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cookie.security.rest.api.entity.Role;
import com.cookie.security.rest.api.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;
	private Long id;

	private String username;

	@JsonIgnore
	private String email;

	@JsonIgnore
	private String password;

	private List<Role> roles;

	public UserDetailsImpl(Long id, String username, String email, String password, List<Role> roles) {
		this.id = id;

		this.username = username;
		this.email = email;
		this.password = password;

		this.roles = roles;

	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static UserDetailsImpl build(User user) {
		return new UserDetailsImpl(user.getId(), user.getUsername(), user.getEmail(), user.getPassword(),
				user.getRoles());
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object otherUser) {
		if (otherUser == null)
			return false;
		else if (!(otherUser instanceof UserDetails))
			return false;
		else
			return (otherUser.hashCode() == hashCode());
	}

	@Override
	public int hashCode() {

		return this.username.hashCode();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.roles.stream().collect(Collectors.toList());
	}

}
