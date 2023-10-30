package com.utp.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/patient")
public class PatientController {

	@GetMapping("/")
	public String goPatient(Model model) {
		model.addAttribute("pageTitle", "Paciente");
		
		return "patient";
	}
	
}
