package com.utp.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.utp.app.model.Doctor;
import com.utp.app.repository.DoctorRepository;

@Service
public class DoctorService {

	private final DoctorRepository doctorRepository;

	public DoctorService(DoctorRepository doctorRepository) {
		this.doctorRepository = doctorRepository;
	}

	@Transactional
	public Doctor saveDoctor(Doctor doctor) {
		return doctorRepository.save(doctor);
	}
	
	@Transactional
	public List<Doctor> getDoctors() {
		return doctorRepository.findAll();
	}
	
	@Transactional
	public Doctor getDoctorById(Long id) {
		Optional<Doctor> doctorOpt = doctorRepository.findById(id);
		Doctor doctor = new Doctor();
		if (doctorOpt.isPresent())
			doctor = doctorOpt.get();
		return doctor;
	}
	
	@Transactional
	public void deleteDoctorById(Long id) {
		doctorRepository.deleteById(id);
	}

}
