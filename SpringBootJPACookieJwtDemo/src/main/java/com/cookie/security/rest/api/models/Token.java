package com.cookie.security.rest.api.models;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Token {
	private TokenType tokenType;
	private String tokenValue;
	private Long duration;
	private LocalDateTime expiryDate;

	public enum TokenType {
		ACCESS, REFRESH
	}
}
