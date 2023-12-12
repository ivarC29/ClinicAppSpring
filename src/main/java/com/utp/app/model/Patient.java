package com.utp.app.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "patients")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "patientId")
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "patient_id")
	private Long			patientId;

	@ManyToOne
    @JoinColumn(name = "user_id")
	private User			user;
	
	@OneToOne(mappedBy = "patient", cascade = CascadeType.ALL)
    private MedicalRecord medicalRecord;
	
	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
	@JsonIgnore
    private List<Appointment> appointments;

	@Column(name = "patient_name")
	private String			patientName;

	@Column(name = "patient_dni")
	private String			patientDNI;

	@Column(name = "patient_address")
	private String			patientAddress;

	@Column(name = "patient_phone")
	private String			patientPhone;

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public MedicalRecord getMedicalRecord() {
		return medicalRecord;
	}

	public void setMedicalRecord(MedicalRecord medicalRecord) {
		this.medicalRecord = medicalRecord;
	}

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getPatientDNI() {
		return patientDNI;
	}

	public void setPatientDNI(String patientDNI) {
		this.patientDNI = patientDNI;
	}

	public String getPatientAddress() {
		return patientAddress;
	}

	public void setPatientAddress(String patientAddress) {
		this.patientAddress = patientAddress;
	}

	public String getPatientPhone() {
		return patientPhone;
	}

	public void setPatientPhone(String patientPhone) {
		this.patientPhone = patientPhone;
	}
}
