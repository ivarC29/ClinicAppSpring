package com.utp.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utp.app.model.Diagnosis;

public interface DiagnosisRepository extends JpaRepository<Diagnosis, Long>{

}
