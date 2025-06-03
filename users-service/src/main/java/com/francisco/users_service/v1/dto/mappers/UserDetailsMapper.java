package com.francisco.users_service.v1.dto.mappers;

import org.springframework.stereotype.Component;

import com.francisco.users_service.v1.dto.UserRequest;
import com.francisco.users_service.v1.dto.UserResponse;
import com.francisco.users_service.v1.model.User;

@Component
public class UserDetailsMapper {

	public UserResponse userToUserResponse(User user) {
		return UserResponse.builder()
	            .id(user.getId())
	            .firstName(user.getFirstName())
	            .lastName(user.getLastName())
	            .email(user.getEmail())
	            .effectiveDate(user.getEffectiveDate())
	            .expirationDate(user.getExpirationDate())
	            .creationTime(user.getCreationTime())
	            .updateTime(user.getUpdateTime())
	            .roles(user.getRoles())
	            .build();
	}
	
	public User userRequestToUser(UserRequest userRequest) {
	    User user =  User.builder()
	            .firstName(userRequest.getFirstName())
	            .lastName(userRequest.getLastName())
	            .email(userRequest.getEmail())
	            .build();
	    
	    user.setEffectiveDate(userRequest.getEffectiveDate());
        user.setExpirationDate(userRequest.getExpirationDate());
        return user;
	}
}
