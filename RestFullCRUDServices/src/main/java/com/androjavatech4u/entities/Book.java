package com.androjavatech4u.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Book 
{
	
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private int id;

@Column(name = "bookName")
private String bookName;

@Column(name = "bookAuthour")
private String bookAuthour;



	
}
