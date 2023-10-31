package com.utp.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utp.app.model.Appointment;
import com.utp.app.service.AppointmentService;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

	@Autowired
	AppointmentService appointmentService;
	
	@GetMapping("/appointments")
	public List<Appointment> getAppointments() {
		List<Appointment> objL_appointments = new ArrayList<Appointment>();
		objL_appointments = appointmentService.getAppointments();
		
		return objL_appointments;
	}
	
}
