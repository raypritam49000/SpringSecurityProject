package com.rest.api.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.api.entity.User;
import com.rest.api.entity.payload.UserDto;
import com.rest.api.repository.UserRepository;
import com.rest.api.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = userRepository.findAll();
		List<UserDto> userList = users.stream().map((user)->modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
		return userList;
	}

	@Override
	public UserDto getUser(Integer userId) {
		User user = userRepository.findById(userId).get();
		return this.modelMapper.map(user, UserDto.class);
	}

	@Override
	public Boolean deleteUser(Integer userId) {
		User user = this.userRepository.findById(userId).get();
		if (user != null) {
			this.userRepository.delete(user);
			return true;
		}
		return false;
	}

	@Override
	public UserDto updateUser(Integer userId, UserDto user) {
		User existUser = this.userRepository.findById(userId).get();
		User updatedUser = null;
		if (user != null) {
			existUser.setEmail(user.getEmail());
			existUser.setName(user.getName());
			existUser.setPassword(user.getPassword());
		    existUser.setUsername(user.getUsername());
			updatedUser = this.userRepository.save(existUser);
		}
		return modelMapper.map(updatedUser,UserDto.class);
	}

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = modelMapper.map(userDto, User.class);
		User savedUser = this.userRepository.save(user);
		return this.modelMapper.map(savedUser, UserDto.class);
	}

}
