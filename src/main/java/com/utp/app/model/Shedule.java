package com.utp.app.model;

import java.sql.Time;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class Shedule {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "shedule_id")
	private Long	sheduleId;
	
	@OneToMany(mappedBy = "shedule", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Doctor> doctors;
	
	@OneToMany(mappedBy = "shedule", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Receptionist> receptionist;
	
	@Column(name = "start_time")
	private Time	startTime;
	
	@Column(name = "end_time")
	private Time	endTime;

	public Long getSheduleId() {
		return sheduleId;
	}

	public void setSheduleId(Long sheduleId) {
		this.sheduleId = sheduleId;
	}

	public List<Doctor> getDoctors() {
		return doctors;
	}

	public void setDoctors(List<Doctor> doctors) {
		this.doctors = doctors;
	}

	public List<Receptionist> getReceptionist() {
		return receptionist;
	}

	public void setReceptionist(List<Receptionist> receptionist) {
		this.receptionist = receptionist;
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}
}
