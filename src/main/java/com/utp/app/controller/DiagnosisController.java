package com.utp.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utp.app.model.Diagnosis;
import com.utp.app.service.DiagnosisService;

@RestController
@RequestMapping("/diagnosis")
public class DiagnosisController {

	@Autowired
	private DiagnosisService diagnosisService;

	@GetMapping("/toList")
	public List<Diagnosis> getDiagnoses() {
		return diagnosisService.getDiagnoses();
	}

	@PutMapping("/add")
	public Diagnosis add(@RequestBody Diagnosis diagnosis) {
		return diagnosisService.saveDiagnosis(diagnosis);
	}

	@GetMapping("/byAppointment/{appointmentId}")
	public Diagnosis getDiagnosisByAppointmentId(@PathVariable Long appointmentId) {
		return diagnosisService.getDiagnosisByAppointmentId(appointmentId);
	}

	@GetMapping("/byPatient/{patientId}")
	public List<Diagnosis> getDiagnosesByPatientId(@PathVariable Long patientId) {
		return diagnosisService.getDianosesByPatientId(patientId);
	}

}
