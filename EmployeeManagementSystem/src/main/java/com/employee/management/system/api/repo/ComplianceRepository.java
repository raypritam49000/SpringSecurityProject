package com.employee.management.system.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.management.system.api.entity.Compliance;

@Repository
public interface ComplianceRepository extends JpaRepository<Compliance, Long> {

}
