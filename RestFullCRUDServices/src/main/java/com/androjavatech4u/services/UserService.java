package com.androjavatech4u.services;

import java.util.List;

import com.androjavatech4u.entities.User;

public interface UserService 
{
	public String registerUser(User user);
	public User findByEmails(String email);
	public String loginUser(User userEntity);
	public String updatePasssword(String oldpass,String newpass,String email);
	
	public String optVerfy(User user);
	 
		//ImageHub
		public boolean saveImageToImageHub(User imageHub);
		public List<User> findTop10ImagesinImageHub();
		public List<User> findByImagesViaSearching(String imageName);
		public User findByMobileOrEmail(String mobile, String email);
		
		
	

}
