package com.francisco.users_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.francisco.users_service.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}