package com.utp.app.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utp.app.model.Receptionist;
import com.utp.app.model.Role;
import com.utp.app.model.User;
import com.utp.app.service.ReceptionistService;
import com.utp.app.service.UserService;

@RestController
@RequestMapping("/receptionist")
public class ReceptionistController {

	@Autowired
	ReceptionistService receptionistService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/toList")
	public List<Receptionist> toList() {
		List<Receptionist> objL_receptionists = new ArrayList<Receptionist>();
		objL_receptionists = receptionistService.getReceptionists();
		
		return objL_receptionists;
	}
//TODO: move this logic to a global function
	@PostMapping("/add")
	public String add(@RequestBody Receptionist receptionist) {
		Role role = new Role();
		role.setRoleId(4L);
		Set<Role> roles = new HashSet<>();
		roles.add(role);		
		
		User user = new User();
		user.setUserId(0L);
		String username = createUsername(receptionist.getReceptionistName());
		user.setUsername(username);
		user.setRoles(roles);
		user.setEmail("");
		user.setPassword(passwordEncoder.encode(username));
		user.setEnabled(true);
		
		user = userService.saveUser(user);
		receptionist.setUser(user);
		receptionistService.saveReceptionist(receptionist);
		return "redirect:/admin/";
	}

	@GetMapping("/get/{id}")
	public Receptionist get(@PathVariable("id") Long id) {
		return receptionistService.getReceptionistById(id);
	}

	@GetMapping("/delete/{id}")
	public void delete(@PathVariable("id") Long id) {
		receptionistService.deleteReceptionistById(id);
	}
	
	//TODO: Create UtilService and move this method there
	public static String createUsername(String fullName) {
		String[] parts = fullName.split(",| ");
        String firstName = parts[3].toLowerCase();
        char firstLetterLastName1 = parts[0].charAt(0);
        char firstLetterLastName2 = (parts.length > 2 && parts[1].length() > 0) ? parts[1].charAt(0) : ' ';
        String result = firstName + ("" + firstLetterLastName1 + firstLetterLastName2).toLowerCase() + "rec";
        return result;
    }

}
