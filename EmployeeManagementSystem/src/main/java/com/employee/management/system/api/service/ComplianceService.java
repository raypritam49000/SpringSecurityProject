package com.employee.management.system.api.service;

import java.util.List;

import com.employee.management.system.api.dto.ComplianceDto;
import com.employee.management.system.api.dto.StatusReportDto;

public interface ComplianceService {
	public ComplianceDto createRL(ComplianceDto complianceDto,Long departmentId);
	public List<ComplianceDto> getAllRL();
	public List<ComplianceDto> getAllRL(Long userId);
	public StatusReportDto createStatusReport(StatusReportDto statusReportDto,Long departmentId);
	public List<StatusReportDto> getAllStatusReport(Long userId,Long complianceId);
}
