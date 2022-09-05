package com.security.rest.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.security.rest.api.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

}
