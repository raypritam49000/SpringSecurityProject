package com.employee.management.system.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	@NotBlank(message = "Name Field is Required!!")
	@Size(min = 3, max = 20, message = "min 3 and max 20 characher is allowed !!!")
	@Column(nullable = false)
	private String username;

	@NotBlank(message = "Password Field is Required!!")
	@Pattern(regexp = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$", message = "Invalid Password !!!")
	@Column(nullable = false)
	private String password;

	@NotBlank(message = "Email Field is Required!!")
	@Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Invalid Email !!!")
	@Column(unique = true, nullable = false, length = 100)
	private String email;

	@Column(nullable = false)
	@NotBlank(message = "Role Field is Required!!")
	private String role;
}
