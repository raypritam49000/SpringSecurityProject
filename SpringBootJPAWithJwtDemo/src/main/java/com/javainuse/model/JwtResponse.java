package com.javainuse.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JwtResponse implements Serializable {
	private static final long serialVersionUID = -8091879091924046844L;
	private String token;
	private String type;

}