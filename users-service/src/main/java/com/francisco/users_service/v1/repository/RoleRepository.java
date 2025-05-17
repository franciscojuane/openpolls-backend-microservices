package com.francisco.users_service.v1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.francisco.users_service.v1.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}