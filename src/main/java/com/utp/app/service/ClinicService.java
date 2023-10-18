package com.utp.app.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.utp.app.model.Clinic;
import com.utp.app.repository.ClinicRepository;

@Service
public class ClinicService {

	private final ClinicRepository clinicRepository;

	public ClinicService(ClinicRepository clinicRepository) {
		this.clinicRepository = clinicRepository;
	}
	
	@Transactional
	public Clinic saveClinic(Clinic clinic) {
		return clinicRepository.save(clinic);
	}
	
	@Transactional
	public List<Clinic> getClinics() {
		return clinicRepository.findAll();
	}
}
