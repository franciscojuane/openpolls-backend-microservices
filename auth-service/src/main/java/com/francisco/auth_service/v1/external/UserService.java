package com.francisco.auth_service.v1.external;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.francisco.auth_service.v1.external.dto.UserDetailsResponse;
import com.francisco.auth_service.v1.external.dto.UserResponse;

@Component
public class UserService {

	@Autowired
	UserServiceClient userServiceClient;
	
	@Value("${security.internal-secret}")
	String internalSecret;
	
	public UserResponse findUserByEmail(String email) {
		return userServiceClient.findUserByEmail(email, internalSecret);
	}
	
	public UserDetailsResponse findUserDetailsByEmail(String email) {
		return userServiceClient.findUserDetailsByEmail(email, internalSecret);
	}
	
	
}
