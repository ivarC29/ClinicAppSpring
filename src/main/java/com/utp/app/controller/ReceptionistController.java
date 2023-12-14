package com.utp.app.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
		
		Iterator<Receptionist> iterator = objL_receptionists.iterator();
		while(iterator.hasNext()) {
			Receptionist receptionist = iterator.next();
			if (!receptionist.getUser().isEnabled()) {
				iterator.remove();
                System.out.println("Se ha eliminado a una persona con Usuario deshabilitado");
			}
		}
		
		return objL_receptionists;
	}
//TODO: move this logic to a global function
	@PostMapping("/add")
	@ResponseBody
	public Receptionist add(@RequestBody Receptionist receptionist) {
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
		receptionist = receptionistService.saveReceptionist(receptionist);
		return receptionist;
	}
	
	@PostMapping("/update")
	@ResponseBody
	public Receptionist update(@RequestBody Receptionist receptionist) {
		Receptionist receptionistTmp = receptionistService.getReceptionistById(receptionist.getReceptionistId());

		if (receptionistTmp != null ) {
			receptionist.setUser(receptionistTmp.getUser());
			receptionist = receptionistService.saveReceptionist(receptionist);			
		}

		return receptionist;
	}

	@GetMapping("/get/{id}")
	public Receptionist get(@PathVariable("id") Long id) {
		return receptionistService.getReceptionistById(id);
	}

	@GetMapping("/delete/{id}")
	public void delete(@PathVariable("id") Long id) {
		Receptionist receptionist = receptionistService.getReceptionistById(id);
		User user = new User();
		
		if ( receptionist != null ) {
			user = receptionist.getUser();
			user.setEnabled(false);
			userService.saveUser(user);
		}
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
