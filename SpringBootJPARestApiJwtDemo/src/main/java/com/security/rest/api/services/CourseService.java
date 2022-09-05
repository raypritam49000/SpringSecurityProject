package com.security.rest.api.services;

import java.util.List;

import com.security.rest.api.entity.Course;

public interface CourseService {
	public abstract Course createCourse(Course course);
	public abstract List<Course> getAllCourses();
	public abstract Course getCourse(Long id);
	public abstract Boolean deleteCourse(Long id);
	public abstract Boolean updateCourse(Long id,Course updateCourse);
}
