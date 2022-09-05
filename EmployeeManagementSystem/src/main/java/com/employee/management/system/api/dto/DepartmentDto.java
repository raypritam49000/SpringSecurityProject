package com.employee.management.system.api.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DepartmentDto implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long departmentId;
	
	@NotBlank(message = "Please enter name")
	@Size(min=4, message = "Department Name should be atleast 4 characters")
	@Size(max=35, message = "Department Name should not be greater than 30 characters")
	private String name;
	
}
