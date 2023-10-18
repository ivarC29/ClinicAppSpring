package com.utp.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.utp.app.model.Receptionist;
import com.utp.app.repository.ReceptionistRepository;

@Service
public class ReceptionistService {

	private final ReceptionistRepository receptionistRepository;

	public ReceptionistService(ReceptionistRepository receptionistRepository) {
		this.receptionistRepository = receptionistRepository;
	}
	
	@Transactional
	public Receptionist saveReceptionist(Receptionist receptionist) {
		return receptionistRepository.save(receptionist);
	}
	
	@Transactional
	public List<Receptionist> getReceptionists() {
		return receptionistRepository.findAll();
	}
	
	@Transactional
	public Receptionist getReceptionistById(Long id) {
		Optional<Receptionist> receptionistOpt = receptionistRepository.findById(id);
		Receptionist receptionist = new Receptionist();
		if (receptionistOpt.isPresent())
			receptionist = receptionistOpt.get();
		return receptionist;
	}
	
	@Transactional
	public void deleteReceptionistById(Long id) {
		receptionistRepository.deleteById(id);
	}
	
}
