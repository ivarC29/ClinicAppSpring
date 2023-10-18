package com.utp.app.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.utp.app.model.Role;
import com.utp.app.repository.RoleRepository;

@Service
public class RoleService {
	
	private final RoleRepository roleRepository;

	public RoleService(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}
	
	@Transactional
	public Role saveRole(Role role) {
		return roleRepository.save(role);
	}

}
