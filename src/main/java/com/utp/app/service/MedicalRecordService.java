package com.utp.app.service;

import java.util.List;
import java.util.Optional;

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
	
	@Transactional
	public List<MedicalRecord> getMedicalRecords() {
		return medicalRecordRepository.findAll();
	}
	
	@Transactional
	public MedicalRecord getMedicalRecordById(Long id) {
		Optional<MedicalRecord> medicalRecordOpt = medicalRecordRepository.findById(id);
		MedicalRecord medicalRecord = new MedicalRecord();
		
		if (medicalRecordOpt.isPresent())
			medicalRecord = medicalRecordOpt.get();
		
		return medicalRecord;
	}
	
}
