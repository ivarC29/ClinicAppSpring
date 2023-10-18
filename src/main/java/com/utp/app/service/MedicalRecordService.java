package com.utp.app.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.utp.app.model.MedicalRecord;
import com.utp.app.repository.MedicalRecordRepository;

@Service
public class MedicalRecordService {

	private final MedicalRecordRepository medicalRecordRepository;

	public MedicalRecordService(MedicalRecordRepository medicalRecordRepository) {
		this.medicalRecordRepository = medicalRecordRepository;
	}
	
	@Transactional
	public MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord) {
		return medicalRecordRepository.save(medicalRecord);
	}
	
}
