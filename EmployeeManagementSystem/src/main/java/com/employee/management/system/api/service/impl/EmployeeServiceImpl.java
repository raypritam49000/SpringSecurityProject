package com.employee.management.system.api.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.employee.management.system.api.dto.EmployeeDto;
import com.employee.management.system.api.entity.Department;
import com.employee.management.system.api.entity.Employee;
import com.employee.management.system.api.exceptions.ResourceNotFoundException;
import com.employee.management.system.api.repo.DepartmentRepository;
import com.employee.management.system.api.repo.EmployeeRepository;
import com.employee.management.system.api.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private static final Logger LOGGER = LogManager.getLogger(EmployeeServiceImpl.class);

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public EmployeeDto createEmployee(EmployeeDto employeeDto, Long departmentId) {

		LOGGER.info("====>>>> Go to inside EmployeeServiceImpl createEmployee Method Employee [" + employeeDto
				+ "] DepartmentId [" + departmentId + "]");

		Employee employee = this.modelMapper.map(employeeDto, Employee.class);

		Department department = this.departmentRepository.findById(departmentId)
				.orElseThrow(() -> new ResourceNotFoundException("Department", "Id", departmentId));

		employee.setDepartment(department);

		Employee createEmployee = this.employeeRepository.save(employee);

		LOGGER.info(
				"====>>>> Go to inside EmployeeServiceImpl createEmployee Method Employee [" + createEmployee + "]");

		return this.modelMapper.map(createEmployee, EmployeeDto.class);
	}

	@Override
	public List<EmployeeDto> getAllEmployees() {
		List<Employee> employees = this.employeeRepository.findAll();
		List<EmployeeDto> employeeDtos = employees.stream()
				.map((employee) -> this.modelMapper.map(employee, EmployeeDto.class)).collect(Collectors.toList());

		LOGGER.info(
				"====>>>> Go to inside EmployeeServiceImpl getAllEmployees Method Employees [" + employeeDtos + "]");

		return employeeDtos;
	}

	@Override
	public EmployeeDto getEmployee(Long employeeId) {

		LOGGER.info("====>>>> Go to inside EmployeeServiceImpl getEmployee Method EmployeeId [" + employeeId + "]");

		Employee employee = this.employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", " Id ", employeeId));

		LOGGER.info("====>>>> Go to inside EmployeeServiceImpl getEmployee Method Employee [" + employee + "]");

		return this.modelMapper.map(employee, EmployeeDto.class);
	}

	@Override
	public void deleteEmployee(Long employeeId) {

		LOGGER.info("====>>>> Go to inside EmployeeServiceImpl deleteEmployee Method EmployeeId [" + employeeId + "]");

		Employee employee = this.employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", " Id ", employeeId));
		this.employeeRepository.delete(employee);
	}

	@Override
	public EmployeeDto updateEmployee(Long employeeId, EmployeeDto employeeDto) {

		LOGGER.info("====>>>> Go to inside EmployeeServiceImpl updateEmployee Method EmployeeId [" + employeeId
				+ "] EmployeeDto [" + employeeDto + "]");

		Employee employee = this.employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", " Id ", employeeId));

		employee.setEmail(employeeDto.getEmail());
		employee.setFirstName(employeeDto.getFirstName());
		employee.setLastName(employeeDto.getLastName());
		employee.setDob(employeeDto.getDob());

		Employee updatedEmployee = this.employeeRepository.save(employee);

		LOGGER.info(
				"====>>>> Go to inside EmployeeServiceImpl updateEmployee Method Employee [" + updatedEmployee + "]");

		return this.modelMapper.map(updatedEmployee, EmployeeDto.class);
	}

	@Override
	public Long getCountEmployees() {
		Long totalEmployee = this.employeeRepository.count();

		LOGGER.info("====>>>> Go to inside EmployeeServiceImpl  getCountEmployees Method Total Employees ["
				+ totalEmployee + "]");

		return totalEmployee;
	}

	@Override
	public List<EmployeeDto> getAllEmployeesByPagination(int pageNo, int pageSize) {
		LOGGER.info("====>>>> Go to inside  EmployeeServiceImpl getAllEmployeesByPagination Method PAGE_NUMBER ["
				+ pageNo + "] PAGE_SIZE [" + pageSize + "]");
		Pageable paging = PageRequest.of(pageNo, pageSize);
		Page<Employee> pagedResult = this.employeeRepository.findAll(paging);
		List<EmployeeDto> employeeDtos = pagedResult.toList().stream()
				.map((employee) -> this.modelMapper.map(employee, EmployeeDto.class)).collect(Collectors.toList());

		LOGGER.info("====>>>> Go to inside  EmployeeServiceImpl getAllEmployeesByPagination Method Employees ["
				+ employeeDtos + "]");
		return employeeDtos;
	}

}
