package com.francisco.auth_service.v1.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.francisco.auth_service.v1.external.dto.UserDetailsResponse;
import com.francisco.auth_service.v1.external.dto.UserResponse;

@FeignClient("users-service")
public interface UserServiceClient {

	@GetMapping("/v1/users/findUserByEmail/{email}")
	public UserResponse findUserByEmail(@PathVariable String email, @RequestHeader("X-Internal-Secret") String internalSecret);
	
	@GetMapping("/v1/users/findUserDetailsByEmail/{email}")
	public UserDetailsResponse findUserDetailsByEmail(@PathVariable String email,  @RequestHeader("X-Internal-Secret") String internalSecret);
}
