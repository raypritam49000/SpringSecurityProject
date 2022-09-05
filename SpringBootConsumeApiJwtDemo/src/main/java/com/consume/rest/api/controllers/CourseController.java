package com.consume.rest.api.controllers;

import java.net.URI;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.consume.rest.api.common.Response;
import com.consume.rest.api.vo.Course;

@RestController
@RequestMapping("/rest/v1")
public class CourseController {

	@Autowired
	private RestTemplate restTemplate;

	@PostMapping("/createCourse")
	public ResponseEntity<?> createCourse(@RequestBody Course course, HttpSession httpSession) {
		Response response = null;
		try {

			String token = (String) httpSession.getAttribute("token");

			if (token != null) {
				System.out.println(token);

				final String baseUrl = "http://localhost:9999/api/v1/createCourse";
				URI uri = new URI(baseUrl);

				HttpHeaders headers = new HttpHeaders();
				headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
				headers.set("Authorization", "Bearer " + token);

				HttpEntity<Course> requestEntity = new HttpEntity<Course>(course, headers);

				ResponseEntity<Response> responseEntity = restTemplate.exchange(uri, HttpMethod.POST, requestEntity,
						Response.class);

				response = responseEntity.getBody();
			}

			return ResponseEntity.ok(response);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(Map.of("message", HttpStatus.INTERNAL_SERVER_ERROR, "success", false));
		}

	}
	
	
	@GetMapping("/getAllCourses")
	public ResponseEntity<?> getAllCourse(HttpSession httpSession) {
		Response response = null;
		try {

			String token = (String) httpSession.getAttribute("token");

			if (token != null) {
				System.out.println(token);

				final String baseUrl = "http://localhost:9999/api/v1/getAllCourse";
				URI uri = new URI(baseUrl);

				HttpHeaders headers = new HttpHeaders();
				headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
				headers.set("Authorization", "Bearer " + token);

				HttpEntity<Course> requestEntity = new HttpEntity<Course>(null, headers);

				ResponseEntity<Response> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, requestEntity,
						Response.class);

				response = responseEntity.getBody();
			}

			return ResponseEntity.ok(response);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(Map.of("message", HttpStatus.INTERNAL_SERVER_ERROR, "success", false));
		}

	}
	
	
	@GetMapping("/getCourse/{id}")
	public ResponseEntity<?> getCourse(@PathVariable("id") Long id,HttpSession httpSession) {
		Response response = null;
		try {

			String token = (String) httpSession.getAttribute("token");

			if (token != null) {
				System.out.println(token);

				final String baseUrl = "http://localhost:9999/api/v1/getCourse/"+id;
				URI uri = new URI(baseUrl);

				HttpHeaders headers = new HttpHeaders();
				headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
				headers.set("Authorization", "Bearer " + token);

				HttpEntity<Course> requestEntity = new HttpEntity<Course>(null, headers);

				ResponseEntity<Response> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, requestEntity,
						Response.class);

				response = responseEntity.getBody();
			}

			return ResponseEntity.ok(response);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(Map.of("message", HttpStatus.INTERNAL_SERVER_ERROR, "success", false));
		}

	}
	
	@DeleteMapping("/deleteCourse/{id}")
	public ResponseEntity<?> deleteCourse(@PathVariable("id") Long id,HttpSession httpSession) {
		Response response = null;
		try {

			String token = (String) httpSession.getAttribute("token");

			if (token != null) {
				System.out.println(token);

				final String baseUrl = "http://localhost:9999/api/v1/deleteCourse/"+id;
				URI uri = new URI(baseUrl);

				HttpHeaders headers = new HttpHeaders();
				headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
				headers.set("Authorization", "Bearer " + token);

				HttpEntity<Course> requestEntity = new HttpEntity<Course>(null, headers);

				ResponseEntity<Response> responseEntity = restTemplate.exchange(uri, HttpMethod.DELETE, requestEntity,Response.class);

				response = responseEntity.getBody();
			}

			return ResponseEntity.ok(response);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(Map.of("message", HttpStatus.INTERNAL_SERVER_ERROR, "success", false));
		}

	}
	
	
	@PutMapping("/updateCourse/{id}")
	public ResponseEntity<?> updateCourse(@PathVariable("id") Long id,@RequestBody Course course,HttpSession httpSession) {
		Response response = null;
		try {

			String token = (String) httpSession.getAttribute("token");

			if (token != null) {
				System.out.println(token);

				final String baseUrl = "http://localhost:9999/api/v1/updateCourse/"+id;
				URI uri = new URI(baseUrl);

				HttpHeaders headers = new HttpHeaders();
				headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
				headers.set("Authorization", "Bearer " + token);

				HttpEntity<Course> requestEntity = new HttpEntity<Course>(course, headers);

				ResponseEntity<Response> responseEntity = restTemplate.exchange(uri, HttpMethod.PUT, requestEntity,Response.class);

				response = responseEntity.getBody();
			}

			return ResponseEntity.ok(response);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(Map.of("message", HttpStatus.INTERNAL_SERVER_ERROR, "success", false));
		}

	}
}
