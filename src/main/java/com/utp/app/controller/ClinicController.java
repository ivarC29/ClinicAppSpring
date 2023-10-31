package com.utp.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utp.app.model.Clinic;
import com.utp.app.service.ClinicService;

@RestController
@RequestMapping("/clinic")
public class ClinicController {

	@Autowired
	ClinicService clinicService;
	
	@GetMapping("/toList")
	public List<Clinic> toList() {
		List<Clinic> objL_clinics = new ArrayList<Clinic>();
		objL_clinics = clinicService.getClinics();
		
		return objL_clinics;
	}
	
}
