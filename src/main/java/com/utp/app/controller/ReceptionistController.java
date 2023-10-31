package com.utp.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utp.app.model.Receptionist;
import com.utp.app.service.ReceptionistService;

@RestController
@RequestMapping("/receptionist")
public class ReceptionistController {

	@Autowired
	ReceptionistService receptionistService;

	@GetMapping("/toList")
	public List<Receptionist> toList() {
		List<Receptionist> objL_receptionists = new ArrayList<Receptionist>();
		objL_receptionists = receptionistService.getReceptionists();
		
		return objL_receptionists;
	}

	@PostMapping("/add")
	public String add(@RequestBody Receptionist receptionist) {
		receptionistService.saveReceptionist(receptionist);
		return "redirect:/admin/";
	}

	@GetMapping("/get/{id}")
	public Receptionist get(@PathVariable("id") Long id) {
		return receptionistService.getReceptionistById(id);
	}

	@GetMapping("/delete/{id}")
	public void delete(@PathVariable("id") Long id) {
		receptionistService.deleteReceptionistById(id);;
	}

}
