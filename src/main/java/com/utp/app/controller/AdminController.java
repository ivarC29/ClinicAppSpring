package com.utp.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@GetMapping("/")
	public String goAdmin(Model model) {
		model.addAttribute("pageTitle", "Admin");
		return "admin";
	}

}
