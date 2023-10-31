package com.utp.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utp.app.model.Schedule;
import com.utp.app.service.ScheduleService;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

	@Autowired
	ScheduleService scheduleService;

	@GetMapping("/toList")
	public List<Schedule> toList() {
		List<Schedule> objL_shedules = new ArrayList<Schedule>();
		objL_shedules = scheduleService.getShedules();
		
		return objL_shedules;
	}

}
