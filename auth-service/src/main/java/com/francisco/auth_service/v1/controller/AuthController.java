package com.francisco.auth_service.v1.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.francisco.auth_service.v1.dto.LoginRequest;
import com.francisco.auth_service.v1.dto.LoginResponse;
import com.francisco.auth_service.v1.external.UserService;
import com.francisco.auth_service.v1.external.dto.UserDetailsResponse;
import com.francisco.auth_service.v1.external.dto.UserResponse;
import com.francisco.auth_service.v1.security.JwtService;

@RequestMapping("/v1/auth")
@RestController
public class AuthController {

	@Autowired
	JwtService jwtService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
		
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
		UserDetailsResponse userDetails = userService.findUserDetailsByEmail(loginRequest.getUsername());
		
		if (userDetails == null)
			throw new RuntimeException("User not found");
		
		String password = loginRequest.getPassword();
		
		boolean passwordMatches = passwordEncoder.matches(password, userDetails.getPassword());
		
		if (passwordMatches) {
			UserResponse userResponse = userService.findUserByEmail(loginRequest.getUsername());
			
			Map<String, Object> extraClaims = new HashMap<>();
			extraClaims.put("firstName", userResponse.getFirstName());
			extraClaims.put("lastName", userResponse.getLastName());
			extraClaims.put("roles", userResponse.getRoles());
			extraClaims.put("firstName", userResponse.getFirstName());
			extraClaims.put("authorities", userDetails.getAuthorities());
			String token = jwtService.buildToken(null, userDetails, 3600);
			return ResponseEntity.ok(LoginResponse.builder().token(token).build());
		} else {
			throw new RuntimeException("Incorrect password");
		}

	}
}
