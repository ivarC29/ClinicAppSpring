package com.utp.app.controller;

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

import com.utp.app.model.Patient;
import com.utp.app.service.PatientService;

@Controller
@RequestMapping("/patient")
public class PatientController {

	@Autowired
	PatientService patientService;

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
	//TODO: Change to unsuscribe method
	@GetMapping("/delete/{id}")
	@ResponseBody
	public void delete(@PathVariable("id") Long id) {
		patientService.deletePatientById(id);;
	}

}