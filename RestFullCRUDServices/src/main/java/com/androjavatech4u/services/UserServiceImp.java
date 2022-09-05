package com.androjavatech4u.services;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.androjavatech4u.entities.User;
import com.androjavatech4u.helpers.SendEmail;
import com.androjavatech4u.repository.UserRepository;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	UserRepository userRapo;

	@Override
	public String registerUser(User userEntity) {

		if (findByEmails(userEntity.getEmail()) != null) {
			return "exist";
		} else {
			Random random = new Random();
			int randomNum = random.nextInt(2323);
			userEntity.setOtp(String.valueOf(randomNum));
			userEntity.setStatus("inactive");
			User userEntity2 = userRapo.save(userEntity);
			if (userEntity2 != null) {
				SendEmail.sendEmail(userEntity2.getEmail(), String.valueOf(randomNum));
				return "success";
			} else {
				return "fail";
			}
		}
	}

	@Override
	public User findByEmails(String email) {

		return userRapo.findByEmail(email);
	}

	@Override
	public String loginUser(User userEntity) {

		System.out.println(userEntity.getEmail() + " " + userEntity.getPass());

		User userEntity2 = userRapo.findByEmailPass(userEntity.getEmail(), userEntity.getPass());

		if (userEntity2 != null) {

			if (userEntity2.getStatus().equalsIgnoreCase("active")) {
				return "loginsuccess";
			}

			else {
				return "Please Activate Account First";
			}
		} else {
			return "Email id or Password is wrong";
		}
	}

	@Override
	public String updatePasssword(String oldpass, String newpass, String email) {
		System.out.println(oldpass);
		System.out.println(newpass);

		User userEntity2 = userRapo.findByEmailPass(email, oldpass);
		if (userEntity2 != null) {
			userEntity2.setPass(newpass);
			userRapo.save(userEntity2);
			return "passwordupdated";
		}

		else
			return "oldpasswordwrong";

	}

	@Override
	public boolean saveImageToImageHub(User imageHub) {
		User userEntity2 = userRapo.findByEmailPass(imageHub.getEmail(), imageHub.getPass());
		System.out.println("userdata=" + userEntity2);

		if (userEntity2 != null) {
			this.userRapo.save(imageHub);
			return true;
		} else {
			return false;

		}
	}

	@Override
	public List<User> findTop10ImagesinImageHub() {
		return this.userRapo.findTop10ByOrderByIdDesc();
	}

	@Override
	public List<User> findByImagesViaSearching(String imageName) {
		return this.userRapo.findByimageNamesContaining(imageName);
	}

	@Override
	public String optVerfy(User user) {

		User user2 = userRapo.findByEmail(user.getEmail());
		System.out.println(user2);

		if (user2 != null && (user2.getOtp().equalsIgnoreCase(user.getOtp()))) {
			user2.setStatus("active");
			userRapo.save(user2);
			return "Otp Verified";

		} else {

			return "Invalid Otp";
		}
	}

	@Override
	public User findByMobileOrEmail(String mobile, String email) {
		return userRapo.findByMobileOrEmail(mobile, email);
	}
}
