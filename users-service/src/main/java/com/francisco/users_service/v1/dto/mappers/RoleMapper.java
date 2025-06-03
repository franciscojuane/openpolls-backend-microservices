package com.francisco.users_service.v1.dto.mappers;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.francisco.users_service.v1.dto.PermissionResponse;
import com.francisco.users_service.v1.dto.RoleResponse;
import com.francisco.users_service.v1.model.Permission;
import com.francisco.users_service.v1.model.Role;

@Component
public class RoleMapper {

	@Autowired
	PermissionMapper permissionMapper;
	
	public RoleResponse roleToRoleResponse(Role role) {
		Set<PermissionResponse> permissions = new HashSet<>();
		for (Permission permission : role.getPermissions()) {
			permissions.add(permissionMapper.permissionToPermissionResponse(permission));
		}
		return RoleResponse.builder().permissions(permissions).name(role.getName()).build();
	}
}
