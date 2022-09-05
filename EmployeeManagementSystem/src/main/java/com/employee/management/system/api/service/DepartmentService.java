package com.employee.management.system.api.service;

import java.util.List;

import com.employee.management.system.api.dto.DepartmentDto;

public interface DepartmentService {
	public DepartmentDto createDepartment(DepartmentDto departmentDto);
	public List<DepartmentDto> getAllDepartmentDtos();
	public DepartmentDto getDepartmentDto(Long departmentId);
	public void deleteDepartment(Long departmentId);
	public DepartmentDto updateDepartment(Long departmentId,DepartmentDto departmentDto);
	public List<DepartmentDto> getAllDepartmentByPagination(int pageNo, int pageSize);
	public Long countDepartment();
}
