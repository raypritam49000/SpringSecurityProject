package com.employee.management.system.api.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusReportDto {
	
	private Long statusId;
	@NotEmpty(message = "Please enter comments !!! ")
	private String comments;
	
	@NotEmpty(message = "Please enter details !!! ")
	private String details;
	
    private Date createDate;
    
    @NotEmpty(message = "Please enter userId !!! ")
	private Long userId;
    
    @NotEmpty(message = "Please enter complianceId !!! ")
	private Long complianceId;
    
	private DepartmentDto department;
}
