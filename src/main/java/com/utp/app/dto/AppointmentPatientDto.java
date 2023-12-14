package com.utp.app.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.utp.app.model.MedicalSpeciality;

public class AppointmentPatientDto {
	
	private Long				appointmentId;
	private String				doctorName;
	private MedicalSpeciality	medicalSpeciality;
	@JsonFormat(pattern="yyyy-MM-dd - HH:mm:ss")
	private LocalDateTime		appointmentDate;
	private String				diagnosis;

	public Long getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public MedicalSpeciality getMedicalSpeciality() {
		return medicalSpeciality;
	}
	public void setMedicalSpeciality(MedicalSpeciality medicalSpeciality) {
		this.medicalSpeciality = medicalSpeciality;
	}
	public LocalDateTime getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(LocalDateTime appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	public String getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
}
