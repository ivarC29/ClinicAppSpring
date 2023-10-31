package com.utp.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utp.app.model.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

}
