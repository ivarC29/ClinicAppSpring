package com.utp.app.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.utp.app.dto.AppointmentPatientDto;
import com.utp.app.model.Appointment;
import com.utp.app.model.Patient;
import com.utp.app.model.User;
import com.utp.app.service.AppointmentService;
import com.utp.app.service.PatientService;
import com.utp.app.service.UserService;

@Controller
@RequestMapping("/patient")
public class PatientController {

	@Autowired
	PatientService patientService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	AppointmentService appointmentService;

	@GetMapping("/")
	public String goPatient(Model model) {
		model.addAttribute("pageTitle", "Paciente");
		
		return "patient";
	}

	@GetMapping("/toList")
	@ResponseBody
	public List<Patient> toList() {
		List<Patient> objL_patients = new ArrayList<Patient>();
		objL_patients = patientService.getPatients();
		
		return objL_patients;
	}

	@PostMapping("/add")
	public String add(@RequestBody Patient patient) {
		patientService.savePatient(patient);
		return "redirect:/admin/";
	}

	@GetMapping("/get/{id}")
	@ResponseBody
	public Patient get(@PathVariable("id") Long id) {
		return patientService.getPatientById(id);
	}
	
	@GetMapping("/getAppointments")
    @ResponseBody
    public List<AppointmentPatientDto> getAppointments(Principal principal) {
		User user = new User();
		Patient patient = new Patient();

		List<AppointmentPatientDto> appointments = new ArrayList<>();
		
		String username = principal.getName();
		
		if ( !("admin".contentEquals(username)) ) {
			user = userService.findByUsername(username);
			
			for (Patient pat : user.getPatients()) {
	            patient = pat;
	        }

		} else 
			patient = patientService.getPatientById(1L);
		
		
		for (Appointment apptmnt : appointmentService.getAppointments()) {
			if (apptmnt.getPatient().getPatientId()  == patient.getPatientId()) {
				AppointmentPatientDto apptmntDto = new AppointmentPatientDto();
				apptmntDto.setAppointmentId(apptmnt.getAppointmentId());
				apptmntDto.setDoctorName(apptmnt.getDoctor().getDoctorName());
				apptmntDto.setMedicalSpeciality(apptmnt.getDoctor().getMedicalSpeciality());
				apptmntDto.setAppointmentDate(apptmnt.getAppointmentDate());
				apptmntDto.setDiagnosis(apptmnt.getDiagnosis().getDescription());
				appointments.add(apptmntDto);
			}

		}

		return appointments;
    }
	
	//TODO: Change to unsuscribe method
	@GetMapping("/delete/{id}")
	@ResponseBody
	public void delete(@PathVariable("id") Long id) {
		patientService.deletePatientById(id);;
	}

}
