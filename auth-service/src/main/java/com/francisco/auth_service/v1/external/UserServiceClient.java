package com.francisco.auth_service.v1.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.francisco.auth_service.v1.external.dto.UserResponse;

@FeignClient("user-service")
public interface UserServiceClient {

	@GetMapping("/findUserByEmail/{email}")
	public UserResponse findUserByEmail(@PathVariable String email);
}
