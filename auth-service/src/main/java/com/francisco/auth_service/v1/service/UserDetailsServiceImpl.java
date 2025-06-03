package com.francisco.auth_service.v1.service;

import java.security.Permission;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.francisco.auth_service.v1.external.dto.UserResponse;
import com.francisco.openpolls.repository.UserRepository;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserResponse user = userService.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found."));
		
		Set<GrantedAuthority> authorities = new HashSet<>();
	    for (String role : user.getRoles()) {
	        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
	        for (Permission permission : role.getPermissions()) {
	            authorities.add(new SimpleGrantedAuthority(permission.getName()));
	        }
	    }

	    return user;
	

	}

}
