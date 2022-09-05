package com.employee.management.system.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.employee.management.system.entity.User;
import com.employee.management.system.helper.Message;
import com.employee.management.system.repository.UserRepository;

@Controller
public class HomeController {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	@RequestMapping("/home")
	public String homePage() {
		return "home";
	}
	
	@RequestMapping("/admin_home")
	public String adminHomePage() {
		return "admin_home";
	}

	@RequestMapping("/")
	public String redirectPage() {
		return "redirect:/login";
	}

	@RequestMapping("/login")
	public String loginPage() {
		return "user_login";
	}

	@RequestMapping("/register")
	public String registerPage() {
		return "register";
	}

	@RequestMapping(path = "/do_register", method = RequestMethod.POST)
	public String doRegister(@Valid @ModelAttribute("user") User user, BindingResult result,
			@RequestParam(value = "agreement", defaultValue = "false") Boolean agreement, Model model,
			HttpSession session) {

		try {
			if (!agreement) {
				System.out.println("You have not accepts terms and conditions");
				throw new Exception("You have not accepts terms and conditions");
			}

			if (result.hasErrors()) {
				System.out.println("Error : " + result.toString());
				model.addAttribute("result", result);
				return "/register";
			}

			if (userRepository.findByEmail(user.getEmail()) != null) {
				throw new Exception("Email is alreday exists");
			}

			if (userRepository.findByUsername(user.getUsername()) != null) {
				throw new Exception("Username is alreday exists");
			}

			user.setPassword(passwordEncoder.encode(user.getPassword()));

			User registerUser = this.userRepository.save(user);

			session.setAttribute("message", new Message("Successfully Registered !!!", "alert-success"));
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("message", new Message(e.getMessage(), "alert-danger"));
		}
		return "/register";
	}

	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "access_denied";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}

	private String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}
}
