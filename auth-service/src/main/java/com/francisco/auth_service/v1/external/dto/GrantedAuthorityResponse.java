package com.francisco.auth_service.v1.external.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GrantedAuthorityResponse {

	private String authority;
	
	
}
