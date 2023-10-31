package com.utp.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.utp.app.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findByUsername(String username);

	@Modifying
    @Transactional
	@Query(value = "INSERT INTO user_role(user_id, role_id) values(?1, ?2);", 
			  nativeQuery = true)
	int addRole(Long userId, Long roleId);
	
}
