package com.francisco.users_service.v1.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.francisco.users_service.v1.dto.UserRequest;
import com.francisco.users_service.v1.dto.UserResponse;
import com.francisco.users_service.v1.dto.mappers.UserMapper;
import com.francisco.users_service.v1.model.User;
import com.francisco.users_service.v1.repository.UserRepository;
import com.francisco.users_service.v1.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/users")
public class UsersController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserService userService;
	
	@Autowired
	UserMapper userMapper;

	@GetMapping("/{id}")
	public ResponseEntity<?> getUser(@PathVariable Long id) {
		Optional<User> user = userService.findById(id);
		if (user.isPresent()) {
			UserResponse userResponse = userMapper.userToUserResponse(user.get());
			return ResponseEntity.ok(userResponse);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/")
	public ResponseEntity<?> getUsers(Pageable pageable) {
		Page<User> page = userService.findAll(pageable);
		List<UserResponse> userResponses = page.toList().stream().map(user -> userMapper.userToUserResponse(user))
				.collect(Collectors.toList());
		return ResponseEntity.ok(userResponses);
	}

	@PostMapping("/")
	public ResponseEntity<?> createUser(@Valid @RequestBody UserRequest userRequest) {
		User user = userService.create(userMapper.userRequestToUser(userRequest), userRequest.getPassword());
		return ResponseEntity.ok(userMapper.userToUserResponse(user));
	}

	@PatchMapping("/{id}")
	public ResponseEntity<?> updateUser(@Valid @RequestBody UserRequest userRequest, @PathVariable Long id) {
		User user = userService.update(userMapper.userRequestToUser(userRequest), userRequest.getPassword());
		return ResponseEntity.ok(userMapper.userToUserResponse(user));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
		userService.deleteUserById(id);
		return ResponseEntity.ok().build();
	}


}
