package com.utp.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.utp.app.model.Schedule;
import com.utp.app.repository.ScheduleRepository;

@Service
public class ScheduleService {

	private final ScheduleRepository sheduleRepository;

	public ScheduleService(ScheduleRepository sheduleRepository) {
		this.sheduleRepository = sheduleRepository;
	}
	
	@Transactional
	public Schedule saveShedule(Schedule shedule) {
		return sheduleRepository.save(shedule);
	}
	
	@Transactional
	public List<Schedule> getShedules() {
		return sheduleRepository.findAll();
	}
	
	@Transactional
	public Schedule getSheduleById(Long id) {
		Optional<Schedule> sheduleOpt = sheduleRepository.findById(id);
		Schedule shedule = new Schedule();
		if (sheduleOpt.isPresent())
			shedule = sheduleOpt.get();
		return shedule;
	}
	
	@Transactional
	public void deleteSheduleById(Long id) {
		sheduleRepository.deleteById(id);
	}
	
}
