package com.francisco.auth_service.v1.external;

import org.springframework.beans.factory.annotation.Autowired;

import com.francisco.auth_service.v1.external.dto.UserResponse;

public class UserService {

	@Autowired
	UserServiceClient userServiceClient;
	
	public UserResponse findUserByEmail(String email) {
		
	}
}
