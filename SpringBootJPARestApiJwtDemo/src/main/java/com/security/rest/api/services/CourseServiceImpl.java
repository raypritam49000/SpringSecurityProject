package com.security.rest.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.security.rest.api.entity.Course;
import com.security.rest.api.repositories.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseRepository courseRepository;

	@Override
	public Course createCourse(Course course) {
		return this.courseRepository.save(course);
	}

	@Cacheable(value = "courseAllData")
	@Override
	public List<Course> getAllCourses() {
		return this.courseRepository.findAll();
	}

	@Cacheable(value = "courseInfo",key = "#id")
	@Override
	public Course getCourse(Long id) {
		return this.courseRepository.findById(id).get();
	}

	@CacheEvict(value = "courseInfo",key = "#id")
	@Override
	public Boolean deleteCourse(Long id) {
		this.courseRepository.deleteById(id);
		return Boolean.TRUE;
	}

	@CachePut(value = "courseInfo",key = "#id")
	@Override
	public Boolean updateCourse(Long id, Course updateCourse) {
		Course existingCourse = this.courseRepository.findById(id).get();
		if (existingCourse != null) {
			existingCourse.setName(updateCourse.getName());
			existingCourse.setDescription(updateCourse.getDescription());
			existingCourse.setTitle(updateCourse.getTitle());
			this.courseRepository.save(existingCourse);
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

}
