package com.utp.app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.utp.app.model.Appointment;
import com.utp.app.model.Clinic;
import com.utp.app.model.Doctor;
import com.utp.app.model.MedicalSpeciality;
import com.utp.app.model.Patient;
import com.utp.app.model.Receptionist;
import com.utp.app.model.Schedule;
import com.utp.app.model.User;
import com.utp.app.service.AppointmentService;
import com.utp.app.service.ClinicService;
import com.utp.app.service.DoctorService;
import com.utp.app.service.PatientService;
import com.utp.app.service.ReceptionistService;
import com.utp.app.service.SheduleService;
import com.utp.app.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	DoctorService doctorService;
	
	@Autowired
	ReceptionistService receptionistService;
	
	@Autowired
	PatientService patientService;
	
	@Autowired
	AppointmentService appointmentService;

	@Autowired
	UserService userService;

	@Autowired
	SheduleService sheduleService;

	@Autowired
	ClinicService clinicService;
	
	@GetMapping("/")
	public String goAdmin(Model model) {
		model.addAttribute("titles", null);
		model.addAttribute("pageTitle", "Admin");
		return "admin";
	}
	//---------------------Doctor methods--------------------------------------------
	@GetMapping("/doctors")
	@ResponseBody
	public List<Doctor> getDoctors() {
		List<Doctor> objL_doctors = new ArrayList<Doctor>();
		objL_doctors = doctorService.getDoctors();
		
		return objL_doctors;
	}
	
	@PostMapping("/addDoctor")
	public String addDoctor(@RequestBody Doctor doctor) {
		doctorService.saveDoctor(doctor);
		return "redirect:/admin/";
	}
	
	@GetMapping("/getDoctor/{id}")
	@ResponseBody
	public Doctor getDoctor(@PathVariable("id") Long id) {
		return doctorService.getDoctorById(id);
	}
	
	@GetMapping("/deleteDoctor/{id}")
	@ResponseBody
	public void deleteDoctor(@PathVariable("id") Long id) {
		doctorService.deleteDoctorById(id);;
	}
//---------------------Receptionist methods--------------------------------------------
	@GetMapping("/receptionists")
	@ResponseBody
	public List<Receptionist> getReceptionists() {
		List<Receptionist> objL_receptionists = new ArrayList<Receptionist>();
		objL_receptionists = receptionistService.getReceptionists();
		
		return objL_receptionists;
	}
	
	@PostMapping("/addReceptionist")
	public String addReceptionist(@RequestBody Receptionist receptionist) {
		receptionistService.saveReceptionist(receptionist);
		return "redirect:/admin/";
	}
	
	@GetMapping("/getReceptionist/{id}")
	@ResponseBody
	public Receptionist getReceptionist(@PathVariable("id") Long id) {
		return receptionistService.getReceptionistById(id);
	}
	
	@GetMapping("/deleteReceptionist/{id}")
	@ResponseBody
	public void deleteReceptionist(@PathVariable("id") Long id) {
		receptionistService.deleteReceptionistById(id);;
	}
//---------------------Patient methods--------------------------------------------
	@GetMapping("/patients")
	@ResponseBody
	public List<Patient> getPatients() {
		List<Patient> objL_patients = new ArrayList<Patient>();
		objL_patients = patientService.getPatients();
		
		return objL_patients;
	}
	
	@PostMapping("/addPatient")
	public String addPatient(@RequestBody Patient patient) {
		patientService.savePatient(patient);
		return "redirect:/admin/";
	}
	
	@GetMapping("/getPatient/{id}")
	@ResponseBody
	public Patient getPatient(@PathVariable("id") Long id) {
		return patientService.getPatientById(id);
	}
	
	@GetMapping("/deletePatient/{id}")
	@ResponseBody
	public void deletePatient(@PathVariable("id") Long id) {
		patientService.deletePatientById(id);;
	}
//---------------------Appointment methods--------------------------------------------
	@GetMapping("/appointments")
	@ResponseBody
	public List<Appointment> getAppointments() {
		List<Appointment> objL_appointments = new ArrayList<Appointment>();
		objL_appointments = appointmentService.getAppointments();
		
		return objL_appointments;
	}
//---------------------Other methods--------------------------------------------
	@GetMapping("/users")
	@ResponseBody
	public List<User> getUsers() {
		List<User> objL_users = new ArrayList<User>();
		objL_users = userService.getUsers();

		return objL_users;
	}
	
	@GetMapping("/shedules")
	@ResponseBody
	public List<Schedule> getShedules() {
		List<Schedule> objL_shedules = new ArrayList<Schedule>();
		objL_shedules = sheduleService.getShedules();
		
		return objL_shedules;
	}
	
	@GetMapping("/clinics")
	@ResponseBody
	public List<Clinic> getClinics() {
		List<Clinic> objL_clinics = new ArrayList<Clinic>();
		objL_clinics = clinicService.getClinics();
		
		return objL_clinics;
	}
	
	@GetMapping("/specialities")
	@ResponseBody
	public List<Map<String, String>> getSpecialities() {
		List<Map<String, String>> optionDataList = new ArrayList<>();

        for (MedicalSpeciality speciality : MedicalSpeciality.values()) {
            Map<String, String> optionData = new HashMap<>();
            optionData.put("id", speciality.name());
            optionData.put("label", speciality.getDescription());
            optionDataList.add(optionData);
        }
		
		return optionDataList;
	}
}
