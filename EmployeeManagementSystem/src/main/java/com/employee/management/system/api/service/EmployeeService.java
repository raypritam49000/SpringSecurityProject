package com.employee.management.system.api.service;

import java.util.List;

import com.employee.management.system.api.dto.EmployeeDto;

public interface EmployeeService {
	public EmployeeDto createEmployee(EmployeeDto employeeDto,Long departmentId);
	public List<EmployeeDto> getAllEmployees();
	public EmployeeDto getEmployee(Long employeeId);
	public void deleteEmployee(Long employeeId);
	public EmployeeDto updateEmployee(Long employeeId,EmployeeDto employeeDto);
	public Long getCountEmployees();
	public List<EmployeeDto> getAllEmployeesByPagination(int pageNo, int pageSize);
}
