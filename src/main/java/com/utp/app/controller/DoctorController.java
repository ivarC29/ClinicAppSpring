package com.utp.app.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import com.utp.app.model.Role;
import com.utp.app.model.User;
import com.utp.app.service.DoctorService;
import com.utp.app.service.UserService;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

	@Autowired
	DoctorService doctorService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

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
	
//TODO: move this logic to a global function
	@PostMapping("/add")
	public String add(@RequestBody Doctor doctor) {
		Role role = new Role();
		role.setRoleId(2L);
		Set<Role> roles = new HashSet<>();
		roles.add(role);		
		
		User user = new User();
		user.setUserId(0L);
		String username = createUsername(doctor.getDoctorName());
		user.setUsername(username);
		user.setRoles(roles);
		user.setEmail("");
		user.setPassword(passwordEncoder.encode(username));
		user.setEnabled(true);
		
		user = userService.saveUser(user);
		doctor.setUser(user);
		doctorService.saveDoctor(doctor);
		return "redirect:/admin/";
	}

	@GetMapping("/get/{id}")
	@ResponseBody
	public Doctor get(@PathVariable("id") Long id) {
		return doctorService.getDoctorById(id);
	}
	
	@GetMapping("/get")
    @ResponseBody
    public Doctor get(Principal principal) {
		String username = principal.getName();
		User user = new User();
		Doctor doctor = new Doctor();	

		if ("admin".contentEquals(username))
			return doctorService.getDoctorById(11L);

		user = userService.findByUsername(username);
		
		for (Doctor doc : user.getDoctors()) {
            doctor = doc;
            break;
        }

		return doctor;
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
	
	@GetMapping("/getBySpeciality/{speciality}")
	@ResponseBody
	public List<Doctor> getDoctorsByMedicalSpeciality(@PathVariable("speciality") MedicalSpeciality speciality) {
		List<Doctor> doctorList = doctorService.getDoctorsByMedicalSpeciality(speciality);

		return doctorList;
	}
	
	//TODO: Create UtilService and move this method there
	public static String createUsername(String fullName) {
        String[] parts = fullName.split(",| ");
        String firstName = parts[3].toLowerCase();
        char firstLetterLastName1 = parts[0].charAt(0);
        char firstLetterLastName2 = (parts.length > 2 && parts[1].length() > 0) ? parts[1].charAt(0) : ' ';
        String result = firstName + ("" + firstLetterLastName1 + firstLetterLastName2).toLowerCase() + "doc";
        return result;
    }
	

}
