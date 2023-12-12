package com.utp.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.utp.app.dto.AppointmentDto;
import com.utp.app.model.Appointment;
import com.utp.app.service.AppointmentService;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;
	
	@GetMapping("/toList")
	public List<Appointment> getAppointments() {
		List<Appointment> objL_appointments = new ArrayList<Appointment>();
		objL_appointments = appointmentService.getAppointments();
		
		return objL_appointments;
	}

	@PutMapping("/add")
	@ResponseBody
	public void add(@RequestBody Appointment appointment) {
		appointmentService.saveAppointment(appointment);
	}
	
	@GetMapping("/fillCalendar")
	@ResponseBody
	public List<AppointmentDto> fillCalendar() {
		List<AppointmentDto> appointmentDtoList = new ArrayList<AppointmentDto>(); 
		List<Appointment> objL_appointments = appointmentService.getAppointments();
		
		for (Appointment appointment : objL_appointments) {
			AppointmentDto appointmentDto = new AppointmentDto();
			
			appointmentDto.setId(appointment.getAppointmentId());
			appointmentDto.setTitle(appointment.getDoctor().getDoctorName().split(", ")[1]+ "-" + appointment.getPatient().getPatientName().split(", ")[1]);
			appointmentDto.setClassName(appointment.getDoctor().getMedicalSpeciality().getDescription());
			appointmentDto.setStart(appointment.getAppointmentDate().toString());
			appointmentDtoList.add(appointmentDto);
		}
		
		return  appointmentDtoList;
	}
	
//	@GetMapping("/byDoctor/{doctorId}")
//	public List<Appointment> getAppointmentsByDoctor(@PathVariable Integer doctorId) {
//		return appointmentService.getAppointmentsByDoctorId(doctorId.longValue());
//	}
//	
}
