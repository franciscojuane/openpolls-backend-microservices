package com.francisco.auth_service.v1.external.dto;

import java.time.LocalDateTime;
import java.util.Set;

public class UserResponse {
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	public LocalDateTime effectiveDate;
	
	public LocalDateTime expirationDate;
	
	public LocalDateTime creationTime;
	
	public LocalDateTime updateTime;
	
	public Set<String> roles;

}
