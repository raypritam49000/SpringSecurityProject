package com.androjavatech4u.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import org.springframework.web.multipart.MultipartFile;
import lombok.Data;

 
@Entity
@Data
public class Product 
{
	
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
int pid;

private String pname;
private String price;

@Column(length = 1000)
private String productImageNames;

@Column(length = 1000)
private String productImageUrl;

@Transient
private List<MultipartFile> productImages;


}
