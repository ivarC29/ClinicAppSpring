package com.utp.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.utp.app.model.Patient;
import com.utp.app.repository.PatientRepository;

@Service
public class PatientService {

	private final PatientRepository patientRepository;

	public PatientService(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}
	
	@Transactional
	public List<Patient> getPatients() {
		return patientRepository.findAll();
	}
	
	@Transactional
	public Patient savePatient(Patient patient) {
		return patientRepository.save(patient);
	}
	
	@Transactional
	public Patient getPatientById(Long id) {
		Optional<Patient> patientOpt = patientRepository.findById(id);
		Patient patient = new Patient();
		if (patientOpt.isPresent())
			patient = patientOpt.get();
		return patient;
	}
	
	@Transactional
	public void deletePatientById(Long id) {
		patientRepository.deleteById(id);
	}
	
	@Transactional
	public void unsubscribe(Long id) {
	}
	
	
}
