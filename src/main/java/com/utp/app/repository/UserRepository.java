package com.utp.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utp.app.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}