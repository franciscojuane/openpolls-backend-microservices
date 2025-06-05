package com.francisco.users_service.v1.dto.mappers;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.francisco.users_service.v1.dto.GrantedAuthorityResponse;
import com.francisco.users_service.v1.dto.UserDetailsResponse;

@Component
public class UserDetailsMapper {
	
	@Autowired
	RoleMapper roleMapper;

	public UserDetailsResponse userDetailsToUserDetailsResponse(UserDetails userDetails) {
		
		Set<GrantedAuthorityResponse> grantedAuthorities = new HashSet<>();
		for (GrantedAuthority role: userDetails.getAuthorities()) {
			grantedAuthorities.add(GrantedAuthorityResponse.builder().authority(role.getAuthority()).build());
		}
		
		return UserDetailsResponse.builder()
	           .username(userDetails.getUsername())
	           .password(userDetails.getPassword())
	           .authorities(grantedAuthorities)
	           .accountNonExpired(userDetails.isAccountNonExpired())
	           .accountNonLocked(userDetails.isAccountNonLocked())
	           .credentialsNonExpired(userDetails.isCredentialsNonExpired())
	           .enabled(userDetails.isEnabled())
	           .build();
	}

}
