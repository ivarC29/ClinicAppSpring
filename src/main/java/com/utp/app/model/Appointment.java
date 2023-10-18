package com.utp.app.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;

@Entity
public class Appointment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "appointment_id")
	private Long			appointmentId;
	
	@ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient 		patient;
	
	@ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor 			doctor;
	
	@ManyToOne
	@JoinColumn(name = "receptionist_id")
	private Receptionist			receptionist;
	
	@OneToOne(mappedBy = "appointment", cascade = CascadeType.ALL)
    private Diagnosis diagnosis;
	
	@OneToOne(mappedBy = "appointment", cascade = CascadeType.ALL)
    private Recipe recipe;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name = "appointment_date")
	private LocalDateTime	appointmentDate;

}
