package com.francisco.users_service.v1.dto.mappers;

import org.springframework.stereotype.Component;

import com.francisco.users_service.v1.dto.PermissionResponse;
import com.francisco.users_service.v1.model.Permission;

@Component
public class PermissionMapper {

	public PermissionResponse permissionToPermissionResponse(Permission permission) {
		return PermissionResponse.builder().name(permission.getName()).build();
	}
}
