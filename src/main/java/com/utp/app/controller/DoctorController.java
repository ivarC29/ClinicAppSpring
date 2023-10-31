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

import com.utp.app.model.Doctor;
import com.utp.app.model.MedicalSpeciality;
import com.utp.app.service.DoctorService;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

	@Autowired
	DoctorService doctorService;

	@GetMapping("/")
	public String goDoctor(Model model) {
		model.addAttribute("pageTitle", "Doctor");
		return "doctor";
	}

	@GetMapping("/toList")
	@ResponseBody
	public List<Doctor> toList() {
		List<Doctor> objL_doctors = new ArrayList<Doctor>();
		objL_doctors = doctorService.getDoctors();

		return objL_doctors;
	}

	@PostMapping("/add")
	public String add(@RequestBody Doctor doctor) {
		doctorService.saveDoctor(doctor);
		return "redirect:/admin/";
	}

	@GetMapping("/get/{id}")
	@ResponseBody
	public Doctor get(@PathVariable("id") Long id) {
		return doctorService.getDoctorById(id);
	}

	@GetMapping("/delete/{id}")
	@ResponseBody
	public void delete(@PathVariable("id") Long id) {
		doctorService.deleteDoctorById(id);;
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
