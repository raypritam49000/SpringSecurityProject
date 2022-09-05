package com.security.rest.api.models;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String title;
	private String description;

}
