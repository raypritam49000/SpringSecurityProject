package com.rest.api.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployeeController {
	
	@GetMapping("/")
	public String home() {
		System.out.println("Hello Pritam Ray");
		return "Hello Pritam Ray";
	}

}
