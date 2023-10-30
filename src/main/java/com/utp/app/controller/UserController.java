package com.utp.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.utp.app.dto.UserDto;
import com.utp.app.model.User;
import com.utp.app.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/")
	public String goUser(Authentication auth, HttpSession session, Model model) {
		String username = auth.getName();
		
		if (session.getAttribute("user") == null) {
			User user = userService.findByUsername(username);
			user.setPassword(null);
			session.setAttribute("user", user);
			System.out.println("Usuario: " + user);
		}
		
		model.addAttribute("pageTitle", "Usuario");
		return "user";
	}
	
	@GetMapping("/changePassword")
	public String changePassword(Model model) {
		model.addAttribute("pageTitle", "Usuario");
		
		return "user";
	}
	
	@PostMapping("/signup")
	public String signup(@RequestBody UserDto userDto) {
		User user = new User();

		String plainTextPassword = userDto.getPassword();
		String encrypPassword = passwordEncoder.encode(plainTextPassword);

		user.setPassword(encrypPassword);
		user.setUsername(userDto.getUsername());
		user.setEmail(userDto.getEmail());
		user.setEnabled(true);

		userService.saveUser(user);
		return "redirect:/";
	}

}
