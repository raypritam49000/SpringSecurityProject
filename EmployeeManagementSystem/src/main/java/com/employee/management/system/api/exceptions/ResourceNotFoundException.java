package com.employee.management.system.api.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	String resourceName;
	String fieldName;
	long fieldValue;
	String field_Value;

	public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
		super(String.format("%s not found with %s : %s", resourceName, fieldName, fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	
	public ResourceNotFoundException(String resourceName, String fieldName, String field_Value) {
		super(String.format("%s not found with %s : %s", resourceName, fieldName, field_Value));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.field_Value = field_Value;
	}

}
