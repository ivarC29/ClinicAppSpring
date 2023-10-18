package com.utp.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utp.app.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
