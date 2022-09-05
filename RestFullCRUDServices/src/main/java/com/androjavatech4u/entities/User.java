package com.androjavatech4u.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Entity
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Size(min = 6, max = 30, message = "name must be 6 to 30 charchers !!!")
	@NotEmpty(message = "name can not be empty")
	private String name;

	@Column(length = 1000)
	private String imageNames;

	@Column(length = 1000)
	private String imageUrl;

	@Transient
	private List<MultipartFile> images;

	@Email(message = "email pattern can not be valid !!!")
	@NotEmpty(message = "email can not be empty !!!")
	private String email;

	@NotEmpty(message = "mobile can not be empty !!!")
	@Size(min = 0, max = 10, message = "mobile number can be 10 digits")
	private String mobile;

	@NotEmpty(message = "passowrd can not be empty !!!")
	@Size(min = 6, max = 20, message = "password must be 6 to 20 charchers !!!")
	private String pass;

	private String status;

	private String otp;

	@Column(insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date registertime;

}
