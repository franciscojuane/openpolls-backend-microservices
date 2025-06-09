package com.francisco.auth_service.v1.external;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.francisco.auth_service.v1.external.dto.UserDetailsResponse;
import com.francisco.auth_service.v1.external.dto.UserResponse;

@Component
public class UserService {

	@Autowired
	UserServiceClient userServiceClient;
	
	public UserResponse findUserByEmail(String email) {
		return userServiceClient.findUserByEmail(email);
	}
	
	public UserDetailsResponse findUserDetailsByEmail(String email) {
		return userServiceClient.findUserDetailsByEmail(email);
	}
	
	
}
