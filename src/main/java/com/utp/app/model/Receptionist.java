package com.utp.app.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "receptionists")
public class Receptionist {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "receptionist_id")
	private Long			receptionistId;
	
	@OneToMany(mappedBy = "receptionist", cascade = CascadeType.ALL)
    private List<Appointment> 	appointments;
	
	@ManyToOne
    @JoinColumn(name = "user_id")
	private User			user;
	
	@ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;
	
	@Column(name = "receptionist_name")
	private String		receptionistName;
	
	@Column(name = "receptionist_dni")
	private String		receptionistDNI;
	
	@Column(name = "receptionist_address")
	private String		receptionistAddress;
	
	@Column(name = "receptionist_phone")
	private String		receptionistPhone;

	public Long getReceptionistId() {
		return receptionistId;
	}

	public void setReceptionistId(Long receptionistId) {
		this.receptionistId = receptionistId;
	}

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public String getReceptionistName() {
		return receptionistName;
	}

	public void setReceptionistName(String receptionistName) {
		this.receptionistName = receptionistName;
	}

	public String getReceptionistDNI() {
		return receptionistDNI;
	}

	public void setReceptionistDNI(String receptionistDNI) {
		this.receptionistDNI = receptionistDNI;
	}

	public String getReceptionistAddress() {
		return receptionistAddress;
	}

	public void setReceptionistAddress(String receptionistAddress) {
		this.receptionistAddress = receptionistAddress;
	}

	public String getReceptionistPhone() {
		return receptionistPhone;
	}

	public void setReceptionistPhone(String receptionistPhone) {
		this.receptionistPhone = receptionistPhone;
	}
}
