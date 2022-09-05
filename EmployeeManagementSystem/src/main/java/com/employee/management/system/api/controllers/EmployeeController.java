package com.employee.management.system.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.management.system.api.config.AppConstants;
import com.employee.management.system.api.dto.ApiResponse;
import com.employee.management.system.api.dto.EmployeeDto;
import com.employee.management.system.api.service.EmployeeService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

	private static final Logger LOGGER = LogManager.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/department/{departmentId}")
	public ResponseEntity<EmployeeDto> createEmployee(@Valid @RequestBody EmployeeDto employeeDto,
			@PathVariable("departmentId") Long departmentId) {

		LOGGER.info("====>>>> Go to inside EmployeeController createEmployee Method Employee [" + employeeDto
				+ "] DepartmentId [" + departmentId + "]");
		EmployeeDto createEmployeeDto = this.employeeService.createEmployee(employeeDto, departmentId);

		LOGGER.info("====>>>> Go to inside EmployeeController createEmployee Method EmployeeDto [" + createEmployeeDto
				+ "]");

		return new ResponseEntity<>(createEmployeeDto, HttpStatus.CREATED);
	}

	@PutMapping("/{employeeId}")
	public ResponseEntity<EmployeeDto> updateEmployee(@Valid @RequestBody EmployeeDto employeeDto,
			@PathVariable("employeeId") Long employeeId) {

		LOGGER.info("====>>>> Go to inside EmployeeController updateEmployee Method Employee [" + employeeDto
				+ "] EmployeeId [" + employeeId + "]");

		EmployeeDto updatedEmployeeDto = this.employeeService.updateEmployee(employeeId, employeeDto);

		LOGGER.info("====>>>> Go to inside EmployeeController updateEmployee Method EmployeeDto [" + employeeDto + "]");

		return ResponseEntity.ok(updatedEmployeeDto);
	}

	@GetMapping("/")
	public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
		List<EmployeeDto> employees = this.employeeService.getAllEmployees();
		LOGGER.info("====>>>> Go to inside EmployeeController getAllEmployees Method EmployeeList [" + employees + "]");
		return ResponseEntity.ok(employees);
	}

	@GetMapping("/{employeeId}")
	public ResponseEntity<EmployeeDto> getSingleEmployee(@PathVariable("employeeId") Long employeeId) {
		LOGGER.info(
				"====>>>> Go to inside EmployeeController getSingleEmployee Method EmployeeId [" + employeeId + "]");
		EmployeeDto employee = this.employeeService.getEmployee(employeeId);
		LOGGER.info("====>>>> Go to inside EmployeeController getSingleEmployee Method EmployeeId [" + employee + "]");
		return ResponseEntity.ok(employee);
	}

	@DeleteMapping("/{employeeId}")
	public ResponseEntity<ApiResponse> deleteEmployee(@PathVariable("employeeId") Long employeeId) {
		LOGGER.info("====>>>> Go to inside EmployeeController deleteEmployee Method EmployeeId [" + employeeId + "]");
		this.employeeService.deleteEmployee(employeeId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Employee deleted Successfully", true), HttpStatus.OK);
	}

	@GetMapping("/count")
	public ResponseEntity<Long> getCountEmployees() {
		Long totalEmployees = this.employeeService.getCountEmployees();
		LOGGER.info("====>>>> Go to inside EmployeeController getCountEmployees Method Total Employees ["
				+ totalEmployees + "]");
		return ResponseEntity.ok(totalEmployees);
	}

	@GetMapping("/employeeList")
	public ResponseEntity<List<EmployeeDto>> getAllEmployees(
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize) {

		LOGGER.info("====>>>> Go to inside EmployeeController getAllEmployees Method PAGE_NUMBER [" + pageNumber
				+ "] PAGE_SIZE [" + pageSize + "]");
		List<EmployeeDto> employeeResponse = this.employeeService.getAllEmployeesByPagination(pageNumber, pageSize);

		LOGGER.info(
				"====>>>> Go to inside EmployeeController getAllEmployees Method Employees [" + employeeResponse + "]");
		return new ResponseEntity<List<EmployeeDto>>(employeeResponse, HttpStatus.OK);
	}

}
