package com.utp.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.utp.app.model.Diagnosis;

public interface DiagnosisRepository extends JpaRepository<Diagnosis, Long>{
	
	public Diagnosis findByAppointmentAppointmentId(Long appointmentId);
	
	@Query(value = "select\r\n"
			+ "  a.*\r\n"
			+ "from diagnoses a\r\n"
			+ "  join appointments b\r\n"
			+ "    on a.appointment_id = b.appointment_id\r\n"
			+ "  join patients c\r\n"
			+ "    on b.patient_id = c.patient_id\r\n"
			+ "where c.patient_id = ?1;", 
			  nativeQuery = true)
	public List<Diagnosis> findByPatientId(Long patientId);
	
}
