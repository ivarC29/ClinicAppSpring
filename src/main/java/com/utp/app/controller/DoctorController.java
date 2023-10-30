package com.utp.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/doctor")
public class DoctorController {
	
	@GetMapping("/")
	public String goDoctor(Model model) {
		model.addAttribute("pageTitle", "Doctor");
		return "doctor";
	}

}
