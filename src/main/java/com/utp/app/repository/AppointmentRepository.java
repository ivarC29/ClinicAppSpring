package com.utp.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utp.app.model.Appointment;

public interface AppointmentRepository  extends JpaRepository<Appointment, Long> {

}
