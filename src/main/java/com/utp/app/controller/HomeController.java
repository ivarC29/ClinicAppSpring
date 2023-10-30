package com.utp.app.controller;

import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String goHome(Model model) {
		model.addAttribute("pageTitle", "Home");
		return "home";
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("pageTitle", "Login");
		return "login";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
		logoutHandler.logout(request, null, null);
		
		return "redirect:/login";
	}
	
	@GetMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("pageTitle", "Registro");
		return "signup";
	}

}
