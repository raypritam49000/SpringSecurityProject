package com.rest.api.services;

import java.util.List;

import com.rest.api.entity.User;
import com.rest.api.entity.payload.UserDto;

public interface UserService {
	public List<UserDto> getAllUsers();
	public UserDto getUser(Integer userId);
	public Boolean deleteUser(Integer userId);
	public UserDto updateUser(Integer userId,UserDto user);
	public UserDto createUser(UserDto userDto);
}
