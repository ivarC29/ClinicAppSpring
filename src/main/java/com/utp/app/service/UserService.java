package com.utp.app.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.utp.app.model.User;
import com.utp.app.repository.UserRepository;

@Service
public class UserService {
	
	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Transactional
	public User saveUser( User user) {
		return userRepository.save(user);
	}
	
	@Transactional
	public List<User> getUsers() {
		return userRepository.findAll();
	}
}
