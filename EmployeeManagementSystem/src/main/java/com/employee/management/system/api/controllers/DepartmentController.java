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
import com.employee.management.system.api.dto.DepartmentDto;
import com.employee.management.system.api.service.DepartmentService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/departments")
public class DepartmentController {

	private static final Logger LOGGER = LogManager.getLogger(EmployeeController.class);

	@Autowired
	private DepartmentService departmentService;

	@PostMapping("/")
	public ResponseEntity<DepartmentDto> createDepartment(@Valid @RequestBody DepartmentDto departmentDto) {
		LOGGER.info("====>>>> Go to inside DepartmentController  createDepartment Method DepartmentDto ["
				+ departmentDto + "]");

		DepartmentDto createDepartmentDto = this.departmentService.createDepartment(departmentDto);

		LOGGER.info("====>>>> Go to inside DepartmentController createDepartment Method DepartmentDto ["
				+ createDepartmentDto + "]");

		return new ResponseEntity<>(createDepartmentDto, HttpStatus.CREATED);
	}

	@PutMapping("/{departmentId}")
	public ResponseEntity<DepartmentDto> updateDepartment(@Valid @RequestBody DepartmentDto departmentDto,
			@PathVariable("departmentId") Long departmentId) {

		LOGGER.info("====>>>> Go to inside DepartmentController updateDepartment Method DepartmentDto [" + departmentDto
				+ "] DepartmentId [" + departmentId + "]");

		DepartmentDto updatedDepartmentDto = this.departmentService.updateDepartment(departmentId, departmentDto);

		LOGGER.info("====>>>> Go to inside DepartmentController updateDepartment Method DepartmentDto ["
				+ updatedDepartmentDto + "]");

		return ResponseEntity.ok(updatedDepartmentDto);
	}

	@GetMapping("/count")
	public ResponseEntity<Long> countDepartments() {
		Long totalDepartment = this.departmentService.countDepartment();

		LOGGER.info("====>>>> Go to inside DepartmentController countDepartments Method Total Department ["
				+ totalDepartment + "]");

		return ResponseEntity.ok(totalDepartment);
	}

	@GetMapping("/")
	public ResponseEntity<List<DepartmentDto>> getAllDepartments() {

		List<DepartmentDto> departments = this.departmentService.getAllDepartmentDtos();

		LOGGER.info("====>>>> Go to inside DepartmentController getAllDepartments Method Department List ["
				+ departments + "]");

		return ResponseEntity.ok(departments);
	}

	@GetMapping("/{departmentId}")
	public ResponseEntity<DepartmentDto> getSingleDepartment(@PathVariable("departmentId") Long departmentId) {

		LOGGER.info("====>>>> Go to inside DepartmentController getSingleDepartment Method DepartmentId ["
				+ departmentId + "]");

		DepartmentDto departmentDto = this.departmentService.getDepartmentDto(departmentId);

		LOGGER.info("====>>>> Go to inside DepartmentController getSingleDepartment Method Department[" + departmentDto
				+ "]");

		return ResponseEntity.ok(departmentDto);
	}

	@DeleteMapping("/{departmentId}")
	public ResponseEntity<ApiResponse> deleteDepartment(@PathVariable("departmentId") Long departmentId) {

		LOGGER.info("====>>>> Go to inside DepartmentController deleteDepartment Method DepartmentId [" + departmentId
				+ "]");

		this.departmentService.deleteDepartment(departmentId);

		ApiResponse apiResponse = new ApiResponse("Department deleted Successfully", true);

		LOGGER.info(
				"====>>>> Go to inside DepartmentController deleteDepartment Method ApiResponse [" + apiResponse + "]");

		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
	}

	@GetMapping("/departemtList")
	public ResponseEntity<List<DepartmentDto>> getAllDepartments(
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize) {

		LOGGER.info("====>>>> Go to inside DepartmentController getAllDepartments Method PAGE_NUMBER [" + pageNumber
				+ "] PAGE_SIZE [" + pageSize + "]");

		List<DepartmentDto> departmentResponse = this.departmentService.getAllDepartmentByPagination(pageNumber,
				pageSize);

		LOGGER.info("====>>>> Go to inside DepartmentController getAllDepartments Method Employee List ["
				+ departmentResponse + "]");

		return new ResponseEntity<List<DepartmentDto>>(departmentResponse, HttpStatus.OK);
	}

}
