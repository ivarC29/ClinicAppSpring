package com.utp.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.utp.app.model.Shedule;
import com.utp.app.repository.SheduleRepository;

@Service
public class SheduleService {

	private final SheduleRepository sheduleRepository;

	public SheduleService(SheduleRepository sheduleRepository) {
		this.sheduleRepository = sheduleRepository;
	}
	
	@Transactional
	public Shedule saveShedule(Shedule shedule) {
		return sheduleRepository.save(shedule);
	}
	
	@Transactional
	public List<Shedule> getShedules() {
		return sheduleRepository.findAll();
	}
	
	@Transactional
	public Shedule getSheduleById(Long id) {
		Optional<Shedule> sheduleOpt = sheduleRepository.findById(id);
		Shedule shedule = new Shedule();
		if (sheduleOpt.isPresent())
			shedule = sheduleOpt.get();
		return shedule;
	}
	
	@Transactional
	public void deleteSheduleById(Long id) {
		sheduleRepository.deleteById(id);
	}
	
}
