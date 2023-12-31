package com.utp.app.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.utp.app.model.Medicine;
import com.utp.app.repository.MedicineRepository;

@Service
public class MedicineService {

	private final MedicineRepository medicineRepository;

	public MedicineService(MedicineRepository medicineRepository) {
		this.medicineRepository = medicineRepository;
	}
	
	@Transactional
	public List<Medicine> getMedicines() {
		return medicineRepository.findAll();
	}
	
	@Transactional
	public Medicine saveMedicine(Medicine medicine) {
		return medicineRepository.save(medicine);
	}
	
}
