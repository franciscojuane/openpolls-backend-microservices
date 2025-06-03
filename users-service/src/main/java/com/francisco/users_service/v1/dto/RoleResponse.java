package com.francisco.users_service.v1.dto;

import java.util.HashSet;
import java.util.Set;

import com.francisco.users_service.v1.model.Permission;

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
	
	private Set<Permission> permissions = new HashSet<>();
}
