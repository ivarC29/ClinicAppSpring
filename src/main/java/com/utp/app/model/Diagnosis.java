package com.utp.app.model;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "diagnoses")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "diagnosisId", scope = Diagnosis.class)
public class Diagnosis {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "diagnosis_id")
    private Long diagnosisId;

	@OneToOne
	@JoinColumn(name = "appointment_id")
	private Appointment appointment;

    private String description;

	public Long getDiagnosisId() {
		return diagnosisId;
	}

	public void setDiagnosisId(Long diagnosisId) {
		this.diagnosisId = diagnosisId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}
}
