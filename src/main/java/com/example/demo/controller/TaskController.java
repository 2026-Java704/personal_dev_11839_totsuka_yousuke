package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Events;
import com.example.demo.entity.Exercise_records;
import com.example.demo.model.Account;
import com.example.demo.repository.EventsRepository;
import com.example.demo.repository.Exercise_recordsRepository;

@Controller
public class TaskController {

	private final Account account;
	private final Exercise_recordsRepository exerciseRecordsRepository;
	private final EventsRepository eventsRepository;

	public TaskController(Account account, Exercise_recordsRepository exerciseRecordsRepository,
			EventsRepository eventsRepository) {
		this.account = account;
		this.exerciseRecordsRepository = exerciseRecordsRepository;
		this.eventsRepository = eventsRepository;
	}

	//記録画面出力
	@GetMapping("/records/add")
	public String records(Model model) {
		if (account.getId() == null) {
			return "redirect:/login";
		}
		List<Events> eventsList = eventsRepository.findByUserIdOrderByIdAsc(account.getId());
		model.addAttribute("events", eventsList);
		return "records";
	}

	//記録画面
	@PostMapping("/records/add")
	public String enter(
			@RequestParam LocalDate date,
			@RequestParam Integer eventId,
			@RequestParam Integer time,
			@RequestParam Double weight,
			@RequestParam(required = false, defaultValue = "") String memo, Model model) {
		if (account.getId() == null) {
			return "redirect:/login";
		}

		List<Events> eventsList = eventsRepository.findByUserIdOrderByIdAsc(account.getId());
		Events event = null;
		for (int i = 0; i < eventsList.size(); i++) {
			Events oneEvent = eventsList.get(i);
			if (oneEvent.getId().equals(eventId)) {
				event = oneEvent;
			}
		}

		if (date == null || time == null || weight == null || event == null) {
			model.addAttribute("message", "種目を選択してください");
			model.addAttribute("events", eventsList);
			return "records";
		}
		//計算式
		double burnCalorie = event.getMets() * weight * (time / 60.0) * 1.05;
		Exercise_records record = new Exercise_records(
				account.getId(),
				eventId,
				date,
				time,
				weight,
				Math.round(burnCalorie * 10.0) / 10.0,
				2,
				memo.trim());
		exerciseRecordsRepository.save(record);

		model.addAttribute("record", record);
		model.addAttribute("eventName", event.getName());
		return "output";
	}

	//過去記録見るためのページ出力
	@GetMapping("/past")
	public String past(Model model) {
		if (account.getId() == null) {
			return "redirect:/login";
		}

		List<Events> events = eventsRepository.findByUserIdOrderByIdAsc(account.getId());
		List<Exercise_records> records = exerciseRecordsRepository.findByUserIdOrderByDateDescIdDesc(account.getId());

		model.addAttribute("events", events);
		model.addAttribute("records", records);
		return "past";
	}

	//	//	更新処理
	//	@PostMapping("/past{id}/edit")
	//	public String update(
	//			@PathVariable Integer id,
	//			@RequestParam (defaultValue = "") Integer eventId, 
	//			@RequestParam (defaultValue = "") Integer time,
	//			@RequestParam (defaultValue = "") Double weight
	//			) {
	////		id検索
	//		Events events = eventsRepository.findById(id).get();
	//		events.setEventId;
	//		events.setTime;
	//		events.setWeight;
	//		
	//		
	//	eventsRepository.save(events);
	//	
	//		return "redirect:/past";
	//	}

}
