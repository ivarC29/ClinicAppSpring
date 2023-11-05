package com.utp.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utp.app.model.Doctor;
import com.utp.app.model.MedicalSpeciality;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

	public List<Doctor> findByMedicalSpeciality(MedicalSpeciality medicalSpeciality);
	
}
