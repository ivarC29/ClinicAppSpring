package com.utp.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utp.app.model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

}
