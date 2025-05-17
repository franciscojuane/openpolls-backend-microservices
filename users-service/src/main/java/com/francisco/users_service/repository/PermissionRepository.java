package com.francisco.users_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.francisco.users_service.model.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long>{

}
