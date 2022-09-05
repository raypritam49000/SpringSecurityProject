package com.consume.rest.api.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Course implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String title;
	private String description;

}