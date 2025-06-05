package com.francisco.users_service.v1.dto.mappers;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.francisco.users_service.v1.dto.RoleResponse;
import com.francisco.users_service.v1.dto.UserDetailsResponse;
import com.francisco.users_service.v1.model.Role;
import com.francisco.users_service.v1.model.User;

@Component
public class UserDetailsMapper {
	
	@Autowired
	RoleMapper roleMapper;

	public UserDetailsResponse userToUserResponse(User user) {
		
		Set<RoleResponse> roleResponses = new HashSet<>();
		for (Role role: user.getRoles()) {
			roleResponses.add(roleMapper.roleToRoleResponse(role));
		}
		
		return UserDetailsResponse.builder()
	            .id(user.getId())
	            .firstName(user.getFirstName())
	            .lastName(user.getLastName())
	            .password(user.getPassword())
	            .email(user.getEmail())
	            .effectiveDate(user.getEffectiveDate())
	            .expirationDate(user.getExpirationDate())
	            .creationTime(user.getCreationTime())
	            .updateTime(user.getUpdateTime())
	            .roles(roleResponses)
	            .isAccountNonExpired(user.isAccountNonExpired())
	            .isAccountNonLocked(user.isAccountNonLocked())
	            .isCredentialsNonExpired(user.isCredentialsNonExpired())
	            .isEnabled(user.isEnabled())
	            .build();
	}
	
	
}
