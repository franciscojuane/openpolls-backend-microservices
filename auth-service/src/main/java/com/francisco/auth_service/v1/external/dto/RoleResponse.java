package com.francisco.auth_service.v1.external.dto;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleResponse {

	private String name;
	
	private Set<PermissionResponse> permissions = new HashSet<>();
}
