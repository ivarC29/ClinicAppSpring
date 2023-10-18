package com.utp.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utp.app.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}
