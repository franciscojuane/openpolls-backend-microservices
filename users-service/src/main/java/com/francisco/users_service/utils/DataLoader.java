package com.francisco.users_service.utils;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.francisco.users_service.v1.model.Permission;
import com.francisco.users_service.v1.model.Role;
import com.francisco.users_service.v1.model.User;
import com.francisco.users_service.v1.repository.PermissionRepository;
import com.francisco.users_service.v1.repository.RoleRepository;
import com.francisco.users_service.v1.service.UserService;

@Component
public class DataLoader implements InitializingBean {

	@Autowired
	UserService userService;
		
	@Autowired
	PermissionRepository permissionRepository;
	
	@Autowired
	RoleRepository roleRepository;

	@Override
	public void afterPropertiesSet() throws Exception {
		
		
		Permission resultsReadPermission = Permission.builder().name("RESULTS_READ").build();
		resultsReadPermission = permissionRepository.save(resultsReadPermission);
		
		Permission pollReadPermission = Permission.builder().name("POLL_READ").build();
		pollReadPermission = permissionRepository.save(pollReadPermission);
		
		Permission pollCreatePermission = Permission.builder().name("POLL_CREATE").build();
		pollCreatePermission = permissionRepository.save(pollCreatePermission);
		
		Permission pollUpdatePermission = Permission.builder().name("POLL_UPDATE").build();
		pollUpdatePermission = permissionRepository.save(pollUpdatePermission);
		
		Permission pollDeletePermission = Permission.builder().name("POLL_DELETE").build();
		pollDeletePermission = permissionRepository.save(pollDeletePermission);
		
		Permission submissionReadPermission = Permission.builder().name("SUBMISSION_READ").build();
		submissionReadPermission = permissionRepository.save(submissionReadPermission);
		
		Permission submissionDeletePermission = Permission.builder().name("SUBMISSION_DELETE").build();
		submissionDeletePermission = permissionRepository.save(submissionDeletePermission);
		
		
		Set<Permission> adminPermissions = new HashSet<>();
		adminPermissions.add(resultsReadPermission);
		adminPermissions.add(pollReadPermission);
		adminPermissions.add(pollCreatePermission);
		adminPermissions.add(pollUpdatePermission);
		adminPermissions.add(pollDeletePermission);
		adminPermissions.add(submissionReadPermission);
		adminPermissions.add(submissionDeletePermission);
		
		Role adminRole = Role.builder().name("ADMIN").permissions(adminPermissions).build();
		adminRole = roleRepository.save(adminRole);
		
		Set<Permission> viewerPermissions = new HashSet<>();
		viewerPermissions.add(pollReadPermission);
		viewerPermissions.add(submissionReadPermission);
		viewerPermissions.add(resultsReadPermission);
		
		Role viewerRole = Role.builder().name("VIEWER").permissions(viewerPermissions).build();
		viewerRole = roleRepository.save(viewerRole);
		
		Set<Permission> editorPermissions = new HashSet<>();
		adminPermissions.add(pollReadPermission);
		adminPermissions.add(pollCreatePermission);
		adminPermissions.add(pollUpdatePermission);
		adminPermissions.add(pollDeletePermission);
		
		Role editorRole = Role.builder().name("EDITOR").permissions(editorPermissions).build();
		editorRole = roleRepository.save(editorRole);
		
		User adminUser = User.builder().firstName("Francisco")
				.lastName("Juane").email("admin@admin.com").roles(Set.of(adminRole))
				.build();
		User user1 = userService.create(adminUser, "admin");
		
		User viewerUser = User.builder().firstName("John")
				.lastName("Doe").email("viewer@viewer.com").roles(Set.of(viewerRole))
				.build();
		User user2 = userService.create(viewerUser, "viewer");
		
		User editorUser = User.builder().firstName("Mary")
				.lastName("Johnson").email("editor@editor.com").roles(Set.of(editorRole))
				.build();
		User user3 = userService.create(editorUser, "editor");
		
		
		
	}

}
