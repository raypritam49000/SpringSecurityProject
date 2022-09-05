package com.security.rest.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.rest.api.common.Constants;
import com.security.rest.api.common.Response;
import com.security.rest.api.entity.Course;
import com.security.rest.api.models.CourseVO;
import com.security.rest.api.services.CourseService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CourseController {

	@Autowired
	private CourseService courseService;


	@PostMapping("/createCourse")
	public Response createCourse(@RequestBody CourseVO courseVO) {
		try {
			Course course = new Course();
			BeanUtils.copyProperties(courseVO, course);

			Course courseDb = this.courseService.createCourse(course);

			if (null != courseDb && courseDb.getId() != null) {
				return new Response(HttpStatus.CREATED, Constants.HTTP_STATUS_CODE_CREATED, new ArrayList<>(),
						"Course created", Constants.STATUS_SUCCESS, Constants.STATUS_SUCCESS_MESSAGE);
			} else {
				return new Response(HttpStatus.BAD_GATEWAY, Constants.HTTP_STATUS_CODE_BAD_REQUEST, new ArrayList<>(),
						"Course is not create", Constants.STATUS_FAILURE, Constants.STATUS_FAILURE_MESSAGE);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			return new Response(HttpStatus.BAD_REQUEST, Constants.HTTP_STATUS_CODE_BAD_REQUEST, new ArrayList<>(),
					exception.toString(), Constants.STATUS_FAILURE, Constants.STATUS_FAILURE_MESSAGE);
		}
	}

	@DeleteMapping("/deleteCourse/{couserId}")
	public Response deleteCourseId(@PathVariable(name = "couserId") Long couserId) {

		try {

			if (null != couserId) {
				boolean isCourseDeleted = courseService.deleteCourse(couserId);

				if (isCourseDeleted) {
					return new Response(HttpStatus.NO_CONTENT, Constants.HTTP_STATUS_CODE_NO_CONTACT, new ArrayList<>(),
							"Course deleted", Constants.STATUS_SUCCESS, Constants.STATUS_SUCCESS_MESSAGE);

				} else {
					return new Response(HttpStatus.BAD_GATEWAY, Constants.HTTP_STATUS_CODE_BAD_REQUEST,
							new ArrayList<>(), "Course is not deleted", Constants.STATUS_FAILURE,
							Constants.STATUS_FAILURE_MESSAGE);
				}

			} else {
				return new Response(HttpStatus.BAD_GATEWAY, Constants.HTTP_STATUS_CODE_BAD_REQUEST, new ArrayList<>(),
						"Course is not deleted", Constants.STATUS_FAILURE, Constants.STATUS_FAILURE_MESSAGE);
			}

		} catch (

		Exception exception) {
			return new Response(HttpStatus.BAD_REQUEST, Constants.HTTP_STATUS_CODE_BAD_REQUEST, new ArrayList<>(),
					exception.toString(), Constants.STATUS_FAILURE, Constants.STATUS_FAILURE_MESSAGE);
		}

	}

	@GetMapping("/getAllCourse")
	public Response findAllCourse() {

		try {
			List<Course> courses = courseService.getAllCourses();
			if (courses != null && !courses.isEmpty()) {
				return new Response(HttpStatus.OK, Constants.HTTP_STATUS_CODE_SCCUESS, courses, "All Course List",
						Constants.STATUS_SUCCESS, Constants.STATUS_SUCCESS_MESSAGE);
			}
		} catch (Exception exception) {
			return new Response(HttpStatus.BAD_REQUEST, Constants.HTTP_STATUS_CODE_BAD_REQUEST, new ArrayList<>(),
					exception.toString(), Constants.STATUS_FAILURE, Constants.STATUS_FAILURE_MESSAGE);

		}
		return new Response(HttpStatus.NO_CONTENT, Constants.HTTP_STATUS_CODE_NO_CONTACT, new ArrayList<>(),
				"Course list not found", Constants.STATUS_FAILURE, Constants.STATUS_FAILURE_MESSAGE);
	}

	@PutMapping("/updateCourse/{courseId}")
	public Response updateCourse(@PathVariable("courseId") Long courseId, @RequestBody CourseVO courseVO) {

		try {
			Course course = new Course();
			BeanUtils.copyProperties(courseVO, course);
			boolean isCourseUpdated = courseService.updateCourse(courseId, course);

			if (isCourseUpdated) {
				return new Response(HttpStatus.OK, Constants.HTTP_STATUS_CODE_SCCUESS, new ArrayList<>(),
						"Course Updated", Constants.STATUS_SUCCESS, Constants.STATUS_SUCCESS_MESSAGE);

			} else {
				return new Response(HttpStatus.BAD_GATEWAY, Constants.HTTP_STATUS_CODE_BAD_REQUEST, new ArrayList<>(),
						"Course is not updated", Constants.STATUS_FAILURE, Constants.STATUS_FAILURE_MESSAGE);
			}

		} catch (Exception exception) {
			return new Response(HttpStatus.BAD_REQUEST, Constants.HTTP_STATUS_CODE_BAD_REQUEST, new ArrayList<>(),
					exception.toString(), Constants.STATUS_FAILURE, Constants.STATUS_FAILURE_MESSAGE);
		}
	}

	@GetMapping("/getCourse/{courseId}")
	public Response findByPackId(@PathVariable("courseId") Long courseId) {

		try {

			CourseVO courseVO = null;
			List<CourseVO> courseVOList = new ArrayList<>();
			
			if (null != courseId) {
				Course course = this.courseService.getCourse(courseId);
				courseVO = new CourseVO();
				BeanUtils.copyProperties(course, courseVO);
				courseVOList.add(courseVO);
			}

			if (null != courseVOList && !courseVOList.isEmpty()) {
				return new Response(HttpStatus.OK, Constants.HTTP_STATUS_CODE_SCCUESS, courseVOList, "Course list",
						Constants.STATUS_SUCCESS, Constants.STATUS_SUCCESS_MESSAGE);

			} else {
				return new Response(HttpStatus.BAD_GATEWAY, Constants.HTTP_STATUS_CODE_BAD_REQUEST, courseVOList,
						"Data not found", Constants.STATUS_FAILURE, Constants.STATUS_FAILURE_MESSAGE);
			}

		} catch (

		Exception exception) {
			return new Response(HttpStatus.BAD_REQUEST, Constants.HTTP_STATUS_CODE_BAD_REQUEST, new ArrayList<>(), "",
					Constants.STATUS_FAILURE, Constants.STATUS_FAILURE_MESSAGE);
		}

	}

}
