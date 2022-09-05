package com.employee.management.system.api.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeDto {
	private Long employeeId;

	@NotEmpty(message = "FirstName is required !!!")
	private String firstName;
	
	@NotEmpty(message = "LastName is required !!!")
	private String lastName;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@Past
	private LocalDate dob;

	@NotEmpty(message = "Email is required  !!!")
	@Email(message = "Email address is not valid !!", regexp = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\\.[a-zA-Z.]{2,5}")
	private String email;

	@NotEmpty(message = "Password is required  !!!")
	private String password;

	private DepartmentDto department;
}
