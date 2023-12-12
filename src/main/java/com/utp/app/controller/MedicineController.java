package com.utp.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utp.app.model.Medicine;
import com.utp.app.service.MedicineService;

@RestController
@RequestMapping("/medicine")
public class MedicineController {

	@Autowired
	private MedicineService medicineService;
	
	@GetMapping("/toList")
	public List<Medicine> getMedicines() {
		return medicineService.getMedicines();
	}
	
}
