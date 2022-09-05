package com.employee.management.system.api.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComplianceDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long complianceId;
	private String rlType;
	private String details;
	private Date createDate;
	private DepartmentDto department;
	private Long empCount;
	private Long stsCount;
	private String status;
}
