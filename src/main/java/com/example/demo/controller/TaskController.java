package com.example.demo.controller;

import java.sql.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Records;
import com.example.demo.repository.ExerciseRepository;

@Controller
public class TaskController {

	private final ExerciseRepository exerciseRepository;

	public TaskController(ExerciseRepository exerciseRepository) {
		this.exerciseRepository = exerciseRepository;
	}

	//記録入力ページ
	@GetMapping("/records/add")
	public String index() {
		return "addLog";
	}

	@PostMapping("/records/add")
	public String enter(
			@RequestParam(defaultValue = "") Date date,
			@RequestParam(defaultValue = "0") Integer time,
			@RequestParam(defaultValue = "0") Integer weight,
			@RequestParam(defaultValue = "") Integer eventId,
			Model model) {

		//		List<Records> recordsList = ExerciseRepository.findByEventId(eventId);

		Records records = new Records(date, time, weight);
		model.addAttribute("records", records);

		return "pLog";

	}

}