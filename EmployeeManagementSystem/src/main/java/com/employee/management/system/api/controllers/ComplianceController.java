package com.employee.management.system.api.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.management.system.api.dto.ComplianceDto;
import com.employee.management.system.api.dto.StatusReportDto;
import com.employee.management.system.api.service.ComplianceService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/compliances")
public class ComplianceController {

	private static final Logger LOGGER = LogManager.getLogger(ComplianceController.class);

	@Autowired
	private ComplianceService complianceService;

	@PostMapping("/department/{departmentId}")
	public ResponseEntity<ComplianceDto> createCompliance(@RequestBody ComplianceDto complianceDto,
			@PathVariable("departmentId") Long departmentId) {

		LOGGER.info("====>>>> Go to inside ComplianceController createCompliance Method ComplianceDto [" + complianceDto
				+ "] DepartmentId [" + departmentId + "]");

		ComplianceDto createCompliance = complianceService.createRL(complianceDto, departmentId);

		LOGGER.info("====>>>> Go to inside ComplianceController createCompliance Method ComplianceDto ["
				+ createCompliance + "]");

		return new ResponseEntity<ComplianceDto>(createCompliance, HttpStatus.CREATED);
	}

	@GetMapping("/")
	public ResponseEntity<List<ComplianceDto>> getAllRl() {

		List<ComplianceDto> complianceDtos = this.complianceService.getAllRL();

		LOGGER.info("====>>>> Go to inside ComplianceController getAllRl Method ComplianceDto " + complianceDtos);

		return new ResponseEntity<List<ComplianceDto>>(complianceDtos, HttpStatus.OK);
	}

	@PostMapping("/statusReports/{departmentId}")
	public ResponseEntity<StatusReportDto> createStatusReport(@RequestBody StatusReportDto statusReportDto,
			@PathVariable("departmentId") Long departmentId) {

		LOGGER.info("====>>>> Go to inside ComplianceController createStatusReport Method StatusReportDto ["
				+ statusReportDto + "] DepartmentId [" + departmentId + "]");

		StatusReportDto createStatusReport = complianceService.createStatusReport(statusReportDto, departmentId);

		LOGGER.info("====>>>> Go to inside ComplianceController createStatusReport Method StatusReportDto ["
				+ createStatusReport + "]");

		return new ResponseEntity<StatusReportDto>(createStatusReport, HttpStatus.CREATED);
	}

	@GetMapping("/statusReports/{userId}/{complianceId}")
	public ResponseEntity<List<StatusReportDto>> getAllStatusReport(@PathVariable("userId") Long userId,
			@PathVariable("complianceId") Long complianceId) {

		LOGGER.info("====>>>> Go to inside ComplianceController getAllStatusReport Method UserId [" + userId
				+ "] ComplianceId [" + complianceId + "]");

		List<StatusReportDto> statusReportDto = this.complianceService.getAllStatusReport(userId, complianceId);

		LOGGER.info("====>>>> Go to inside ComplianceController getAllStatusReport Method StatusReportDto List ["
				+ statusReportDto + "]");

		return new ResponseEntity<List<StatusReportDto>>(statusReportDto, HttpStatus.OK);
	}
}
