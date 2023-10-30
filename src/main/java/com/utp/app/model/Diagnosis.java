package com.utp.app.model;
import jakarta.persistence.*;

@Entity
@Table(name = "diagnoses")
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
