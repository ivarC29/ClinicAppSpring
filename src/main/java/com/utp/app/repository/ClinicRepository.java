package com.utp.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utp.app.model.Clinic;

public interface ClinicRepository extends JpaRepository<Clinic, Long>{

}
