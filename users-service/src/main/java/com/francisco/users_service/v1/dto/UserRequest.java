package com.francisco.users_service.v1.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

	@NotNull
	private String firstName;

	@NotNull
	private String lastName;
	
	@NotNull
	private String password;

	@NotNull
	private String email;

	public LocalDateTime effectiveDate;

	public LocalDateTime expirationDate;
	

}