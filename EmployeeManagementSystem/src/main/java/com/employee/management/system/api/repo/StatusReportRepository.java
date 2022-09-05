package com.employee.management.system.api.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.management.system.api.entity.StatusReport;

@Repository
public interface StatusReportRepository extends JpaRepository<StatusReport, Long> {
	public List<StatusReport> findByUserIdAndComplianceId(Long userId, Long complianceId);
}
