package com.utp.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.utp.app.dto.UserDto;
import com.utp.app.model.Patient;
import com.utp.app.model.User;
import com.utp.app.service.PatientService;
import com.utp.app.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	@Autowired
	PatientService patientService;
	
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

	@GetMapping("/toList")
	@ResponseBody
	public List<User> toList() {
		List<User> objL_users = new ArrayList<User>();
		objL_users = userService.getUsers();

		return objL_users;
	}

	@PostMapping("/signup")
	public String signup(@RequestBody UserDto userDto) {
		User user = new User();
		Patient patient = new Patient();

		String plainTextPassword = userDto.getPassword();
		String encrypPassword = passwordEncoder.encode(plainTextPassword);

		user.setPassword(encrypPassword);
		user.setUsername(userDto.getUsername());
		user.setEmail(userDto.getEmail());
		user.setEnabled(true);

		patient.setUser(user);
		patient.setPatientName(userDto.getLastName().trim() + ", " + userDto.getFirstName().trim());
		patient.setPatientDNI(userDto.getDni());
		patient.setPatientAddress(userDto.getAddress());
		patient.setPatientPhone(userDto.getCelphone());

		user = userService.saveUser(user);
		int isConfirmed = userService.addRole(user.getUserId(), 4L);
		if ( isConfirmed == 1)
			patientService.savePatient(patient);
		else {
			System.err.println("Error al asignar rol de usuario");
			return null;
		}

		return null;
	}

}
