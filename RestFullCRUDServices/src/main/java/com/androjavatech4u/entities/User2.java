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

import lombok.Data;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

 


@Entity
@Data
public class User2 {

@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE)
private int id;

@NotBlank(message = "Name Can not Blank")
@Size(min = 3, max = 20 , message = "user name must be btween 3 and 20")
private String name;



@Column(length = 1000)
private String imageNames;

@Column(length = 1000)
private String imageUrl;

@Transient
private List<MultipartFile> images;



@NotBlank 
private String email;


@NotBlank(message = "Mobile Can not Blank")
@Size(min = 10, max = 10 ,message ="user mobile must 10 digit")
private String mobile;


@NotBlank
@Size(min = 6, max = 15)
@Pattern(regexp = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$", message = "Password Must be start with uppper case and should constains at least one special symbol")
private String pass;


@NotBlank
private String status;


@NotBlank
private String otp;


@Column( insertable = false, updatable = false,columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
@Temporal(TemporalType.TIMESTAMP)
private Date registertime; 
	
	
}
