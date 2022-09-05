package com.employee.management.system.api.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.management.system.api.dto.ComplianceDto;
import com.employee.management.system.api.dto.StatusReportDto;
import com.employee.management.system.api.entity.Compliance;
import com.employee.management.system.api.entity.Department;
import com.employee.management.system.api.entity.StatusReport;
import com.employee.management.system.api.exceptions.ResourceNotFoundException;
import com.employee.management.system.api.repo.ComplianceRepository;
import com.employee.management.system.api.repo.DepartmentRepository;
import com.employee.management.system.api.repo.EmployeeRepository;
import com.employee.management.system.api.repo.StatusReportRepository;
import com.employee.management.system.api.service.ComplianceService;

@Service
public class ComplianceServiceImpl implements ComplianceService {

	private static final Logger LOGGER = LogManager.getLogger(ComplianceServiceImpl.class);

	@Autowired
	private ComplianceRepository complianceRepository;

	@Autowired
	private StatusReportRepository statusReportRepository;

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ComplianceDto createRL(ComplianceDto complianceDto, Long departmentId) {

		LOGGER.info("====>>>> Go to inside ComplianceServiceImpl createCompliance Method ComplianceDto ["
				+ complianceDto + "] DepartmentId [" + departmentId + "]");

		Department department = this.departmentRepository.findById(departmentId)
				.orElseThrow(() -> new ResourceNotFoundException("Department", "Id", departmentId));

		Long empCount = this.employeeRepository.count();

		Long stsCount = this.statusReportRepository.count();

		Compliance compliance = this.modelMapper.map(complianceDto, Compliance.class);
		compliance.setDepartment(department);
		compliance.setEmpCount(empCount);
		compliance.setStsCount(stsCount);

		Compliance createCompliance = this.complianceRepository.save(compliance);

		LOGGER.info("====>>>> Go to inside ComplianceServiceImpl createCompliance Method ComplianceDto ["
				+ createCompliance + "]");

		return this.modelMapper.map(createCompliance, ComplianceDto.class);
	}

	@Override
	public List<ComplianceDto> getAllRL() {

		List<Compliance> compliances = this.complianceRepository.findAll();

		List<ComplianceDto> complianceDtos = compliances.stream()
				.map((compliance) -> this.modelMapper.map(compliance, ComplianceDto.class))
				.collect(Collectors.toList());

		LOGGER.info("====>>>> Go to inside ComplianceServiceImpl getAllRl Method ComplianceDto " + complianceDtos);

		return complianceDtos;
	}

	@Override
	public List<ComplianceDto> getAllRL(Long userId) {
		return null;
	}

	@Override
	public StatusReportDto createStatusReport(StatusReportDto statusReportDto, Long departmentId) {

		LOGGER.info("====>>>> Go to inside ComplianceServiceImpl createStatusReport Method StatusReportDto ["
				+ statusReportDto + "] DepartmentId [" + departmentId + "]");

		Department department = this.departmentRepository.findById(departmentId)
				.orElseThrow(() -> new ResourceNotFoundException("Department", "Id", departmentId));

		StatusReport statusReport = this.modelMapper.map(statusReportDto, StatusReport.class);

		statusReport.setDepartment(department);

		StatusReport createStatusReport = this.statusReportRepository.save(statusReport);

		LOGGER.info("====>>>> Go to inside ComplianceServiceImpl createStatusReport Method StatusReportDto ["
				+ createStatusReport + "]");

		return this.modelMapper.map(createStatusReport, StatusReportDto.class);
	}

	@Override
	public List<StatusReportDto> getAllStatusReport(Long userId, Long complianceId) {

		LOGGER.info("====>>>> Go to inside ComplianceServiceImpl getAllStatusReport Method UserId [" + userId
				+ "] ComplianceId [" + complianceId + "]");

		List<StatusReport> statusReports = statusReportRepository.findByUserIdAndComplianceId(userId, complianceId);
		List<StatusReportDto> statusReportDtos = statusReports.stream()
				.map((statusReport) -> modelMapper.map(statusReport, StatusReportDto.class))
				.collect(Collectors.toList());

		LOGGER.info("====>>>> Go to inside ComplianceServiceImpl getAllStatusReport Method StatusReportDto List ["
				+ statusReportDtos + "]");

		return statusReportDtos;
	}

}
