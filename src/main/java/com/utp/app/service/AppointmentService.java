package com.utp.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.utp.app.model.Appointment;
import com.utp.app.repository.AppointmentRepository;

@Service
public class AppointmentService {

	private final AppointmentRepository appointmentRepository;

	public AppointmentService(AppointmentRepository appointmentRepository) {
		this.appointmentRepository = appointmentRepository;
	}

	@Transactional
	public Appointment saveAppointment( Appointment appointment) {
		return appointmentRepository.save(appointment);
	}

	@Transactional(readOnly = true)
	public List<Appointment> getAppointments() {
		return appointmentRepository.findAll();
	}

	@Transactional(readOnly = true) // Solo lectura
    public Appointment getAppointmentById(Long appointmentId) {
		Optional<Appointment> appointmentOpt = appointmentRepository.findById(appointmentId);
		Appointment appointment = new Appointment();
		if( appointmentOpt.isPresent())
			appointment = appointmentOpt.get();
		
        return appointment;
    }

	@Transactional
    public void deleteAppointment(Long appointmentId) {
		appointmentRepository.deleteById(appointmentId);
    }
	
	/*
	 * @Transactional public List<Appointment> getAppointmentsByDoctorId( Long
	 * doctorId ) { return appointmentRepository.findByDoctorDoctorId(doctorId); }
	 */

}
