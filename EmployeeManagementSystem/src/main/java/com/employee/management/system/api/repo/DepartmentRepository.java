package com.employee.management.system.api.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.management.system.api.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>{
	public abstract Optional<Department> findByName(String name);
}
