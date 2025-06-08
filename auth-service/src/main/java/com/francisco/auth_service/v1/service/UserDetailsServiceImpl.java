package com.francisco.auth_service.v1.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

/*	@Autowired
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
	

	}*/

}
