package com.utp.app.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.utp.app.model.Diagnosis;
import com.utp.app.repository.DiagnosisRepository;

@Service
public class DiagnosisService {

	private final DiagnosisRepository diagnosisRepository;
	
	public DiagnosisService(DiagnosisRepository diagnosisRepository) {
		this.diagnosisRepository = diagnosisRepository;
	}
	
	@Transactional
	public List<Diagnosis> getDiagnoses() {
		return diagnosisRepository.findAll();
	}
	
	@Transactional
	public Diagnosis saveDiagnosis(Diagnosis diagnosis) {
		return diagnosisRepository.save(diagnosis);
	}
	
	@Transactional
	public Diagnosis getDiagnosisByAppointmentId(Long appointmentId) {
		return diagnosisRepository.findByAppointmentAppointmentId(appointmentId);
	}
	
	@Transactional
	public List<Diagnosis> getDianosesByPatientId(Long patientId) {
		return diagnosisRepository.findByPatientId(patientId);
	}
	
	
	
}
