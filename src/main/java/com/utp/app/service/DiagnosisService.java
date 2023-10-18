package com.utp.app.service;

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
	public Diagnosis saveDiagnosis(Diagnosis diagnosis) {
		return diagnosisRepository.save(diagnosis);
	}
	
}
