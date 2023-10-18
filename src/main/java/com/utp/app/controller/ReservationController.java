package com.utp.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

	@GetMapping("/")
	public String goReservation(Model model) {
		model.addAttribute("pageTitle", "Reservacion");
		
		return "reservation";
	}
	
}
