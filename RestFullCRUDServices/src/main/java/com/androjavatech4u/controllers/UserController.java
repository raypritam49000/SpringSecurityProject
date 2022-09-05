package com.androjavatech4u.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.androjavatech4u.entities.User;
import com.androjavatech4u.helpers.ResponseHandler;
import com.androjavatech4u.helpers.SendEmail;
import com.androjavatech4u.repository.UserRepository;
import com.androjavatech4u.services.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	UserService userservice;

	@Autowired
	private UserRepository userRepository;

//LOCAL-SERVER URL
	private String URL = "http://localhost:9999/imagesHub/";

	// LIVE-SERVER
	/// private String URL="https://www.androjavatech4u.com/imagesHub/";

	@PostMapping("/register")
	public ResponseEntity<Object> userRegister(@Valid @RequestBody User user) {
		try {
			String msg = userservice.registerUser(user);

			if (msg.equals("success")) {
				return ResponseHandler.generateResponse(HttpStatus.OK, true, msg, user);
			}

			else if (msg.equalsIgnoreCase("fail")) {
				return ResponseHandler.generateResponse(HttpStatus.METHOD_FAILURE, true, msg, user);
			} else {
				return ResponseHandler.generateResponse(HttpStatus.OK, true, msg, user);
			}
		}

		catch (Exception e) {
			return ResponseHandler.generateResponse(HttpStatus.METHOD_FAILURE, true, "ye to error ba", user);
		}
	}

	@PostMapping("/login")
	public ResponseEntity<Object> doLogin(@RequestBody User userEntity) {

		String msg = userservice.loginUser(userEntity);

		if (msg.equalsIgnoreCase("loginsuccess")) {
			return ResponseHandler.generateResponse(HttpStatus.OK, true, msg, userEntity);
		}

		else if (msg.equalsIgnoreCase("Please Activate Account First")) {
			return ResponseHandler.generateResponse(HttpStatus.OK, true, msg, userEntity);
		}

		else {
			return ResponseHandler.generateResponse(HttpStatus.METHOD_FAILURE, false, msg, userEntity);
		}

	}

	@PostMapping("/changePassword")
	public ResponseEntity<Object> chnagePassword(@RequestParam("newpassword") String newpassword,
			@RequestParam("oldpassword") String oldpassword, @RequestParam("email") String email) {
		String msg = userservice.updatePasssword(oldpassword, newpassword, email);
		return ResponseHandler.generateResponse(HttpStatus.OK, true, msg, null);

	}

	@PostMapping("/forgetPassword")
	public ResponseEntity<?> forgetPassword(@RequestParam("mobile") String mobile,
			@RequestParam("email") String email) {
		String msg = null;
		Boolean isSendOtp = false;
		try {
			User user = userservice.findByMobileOrEmail(mobile, email);
			System.out.println(user);

			if (user != null) {
				String link = "http://localhost:9999/api/updatePassword?id=" + user.getId();
				isSendOtp = SendEmail.sendEmail(email, link);

				return ResponseEntity.ok(Map.of("message", "send update password link register email", "success", true,
						"statusCode", 200));
			}

		} catch (Exception e) {
			return ResponseEntity.ok(Map.of("message", e.getMessage(), "success", false, "statusCode", 502));
		}
		return ResponseEntity.ok(Map.of("message", "Bad Request", "success", false, "statusCode", 502));
	}

	@PostMapping("/verifyotp")
	public ResponseEntity<Object> otpVerify(@RequestBody User user) {
		String msg = userservice.optVerfy(user);
		return ResponseHandler.generateResponse(HttpStatus.OK, true, msg, null);

	}

	@PostMapping("/updatePassword")
	public ResponseEntity<?> updatePassword(@RequestParam("id") int id,
			@RequestParam(name = "updatePassword", required = false) String updatePassword) {
		try {
			User user = userRepository.findById(id).get();
			if (user != null) {
				user.setPass(updatePassword);
				this.userRepository.save(user);
			}

			return ResponseEntity.ok(Map.of("message", "Updated Password", "success", true, "statusCode", 200));
		} catch (Exception e) {
			return ResponseEntity.ok(Map.of("message", "Bad Request", "success", false, "statusCode", 502));
		}
	}

	@PostMapping("/updateProfile")
	public String updateProfile(@ModelAttribute User user) {

		// set USEREMAIL
		// String userEmail=(String)session.getAttribute("ad_Email");

		List<MultipartFile> images = user.getImages();

//			//getting the Real Path of the Directory
		try {
			// String absolutePath = new
			// ClassPathResource("/static/imagesHub/").getFile().getPath();
			// System.out.println(absolutePath);

			String absolutePath = "F:\\REST_Webservices\\RestFullCRUDServices\\src\\main\\resources\\static\\imagesHub";

			for (MultipartFile mul : images) {
				// Writing the Image
				byte[] bytes = mul.getBytes();
				FileOutputStream fileOutputStream = new FileOutputStream(
						absolutePath + File.separator + mul.getOriginalFilename());
				fileOutputStream.write(bytes);
				fileOutputStream.close();

				// LIVE SERVER
				// imageHub.setImageUrl(URL+absolutePath+mul.getOriginalFilename());

				// Setting Data to image hub single Object
				// User hub=new User();

				user.setImageNames(user.getImageNames());

				// LIVE-SERVER OR LOCAL_SERVER URL first remove-comment to upper side
				user.setImageUrl(URL + mul.getOriginalFilename());

				// hub.setUserEmail(userEmail);

				// SAVE IMAGE TO DATABASE

				if (this.userservice.saveImageToImageHub(user)) {

					return "success";

				}

				else {

					return "failed";

				}

			}

		} catch (Exception e) {
			e.printStackTrace();

		}

		return "failed";
	}

}