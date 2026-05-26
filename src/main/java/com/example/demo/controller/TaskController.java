package com.example.demo.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	//	@PostMapping("/records/add")
	//	public String enter(
	//			@RequestParam List<LocalDate> date,
	//			@RequestParam List<Integer> eventId,
	//			@RequestParam List<Integer> time,
	//			@RequestParam List<Double> weight,
	//			@RequestParam(required = false, defaultValue = "") List<String> memo, Model model) {
	//		if (account.getId() == null) {
	//			return "redirect:/login";
	//		}
	//
	//		List<Events> eventsList = eventsRepository.findByUserIdOrderByIdAsc(account.getId());
	//		Events event = null;
	//		for (int i = 0; i < eventsList.size(); i++) {
	//			Events oneEvent = eventsList.get(i);
	//			if (oneEvent.getId().equals(eventId)) {
	//				event = oneEvent;
	//			}
	//		}
	//
	//		if (date == null || time == null || weight == null || event == null) {
	//			model.addAttribute("message", "種目を選択してください");
	//			model.addAttribute("events", eventsList);
	//			return "records";
	//		}
	//		
	//		// 種目取得
	//				Events event = eventsRepository.findById(eventId.get(i)).orElse(null);
	//
	//				if (event == null) {
	//					continue;
	//				}
	//		
	//		//計算式
	//		double burnCalorie = event.getMets() * weight.get(i) * (time.get(i) / 60.0) * 1.05;
	//		Exercise_records record = new Exercise_records(
	//				account.getId(),
	//				eventId.get(i),
	//				date.get(i),
	//				time.get(i),
	//				weight.get(i),
	//				Math.round(burnCalorie * 10.0) / 10.0,
	//				2,
	//				memo.get(i));
	//		exerciseRecordsRepository.save(record);
	//
	//		model.addAttribute("record", record);
	//		model.addAttribute("eventName", event.getName());
	//		return "output";
	//	}

	@PostMapping("/records/add")
	public String enter(
			@RequestParam List<LocalDate> date,
			@RequestParam List<Integer> eventId,
			@RequestParam List<Integer> time,
			@RequestParam List<Double> weight,
			@RequestParam(required = false) List<String> memo,
			Model model) {

		List<Exercise_records> records = new ArrayList<>();

		double totalBurnCalorie = 0;
		for (int i = 0; i < date.size(); i++) {

			// 種目取得
			Events event = eventsRepository.findById(eventId.get(i)).orElse(null);

			if (event == null) {
				continue;
			}

			double burnCalorie = event.getMets() * weight.get(i) * (time.get(i) / 60.0) * 1.05;

			totalBurnCalorie += burnCalorie;
			Exercise_records record = new Exercise_records(
					account.getId(),
					eventId.get(i),
					date.get(i),
					time.get(i),
					weight.get(i),
					Math.round(burnCalorie * 10.0) / 10.0,
					2,
					memo.get(i));

			exerciseRecordsRepository.save(record);

			records.add(record);
		}
		model.addAttribute("totalBurnCalorie",
				Math.round(totalBurnCalorie * 10.0) / 10.0);

		model.addAttribute("records", records);

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

	//	更新処理
	@GetMapping("/past/{id}/edit")
	public String edit(@PathVariable Integer id, Model model) {
		if (account.getId() == null) {
			return "redirect:/login";
		}

		List<Events> events = eventsRepository.findByUserIdOrderByIdAsc(account.getId());

		model.addAttribute("events", events);

		Exercise_records exercise_records = exerciseRecordsRepository.findById(id).get();
		model.addAttribute("exercise_records", exercise_records);
		model.addAttribute("recordId", id);

		return "pastEdit";
	}

	@PostMapping("/past/{id}/edit")
	public String update(
			@PathVariable Integer id,
			@RequestParam(defaultValue = "") Integer eventId,
			@RequestParam(defaultValue = "") LocalDate date,
			@RequestParam(defaultValue = "") Integer time,
			@RequestParam(defaultValue = "") Double weight,
			@RequestParam(defaultValue = "") String memo) {
		//		id検索
		Exercise_records exercise_records = exerciseRecordsRepository.findById(id).get();
		Events events = eventsRepository.findById(eventId).get();

		double burnCalorie = events.getMets() * weight * (time / 60.0) * 1.05;
		double roundedCalorie = Math.round(burnCalorie * 10.0) / 10.0;

		exercise_records.setEventId(eventId);
		exercise_records.setDate(date);
		exercise_records.setTime(time);
		exercise_records.setWeight(weight);
		exercise_records.setBurnCalorie(roundedCalorie);
		exercise_records.setMemo(memo);

		eventsRepository.save(exercise_records);

		return "redirect:/past";
	}

	//	基礎代謝計算機能
	@GetMapping("/bmr")
	public String bmrindex() {
		return "bmr";
	}

	@PostMapping("/bmr/result")
	public String bmr(
			@RequestParam String sex,
			@RequestParam Double weight,
			@RequestParam Double height,
			@RequestParam Integer age,
			Model model) {

		double bmr;
		if (sex.equals("m")) {
			bmr = 13.397 * weight + 4.79 * height - 5.677 * age + 88.362;

		} else {
			bmr = 9.247 * weight + 3.098 * height - 4.33 * age + 447.593;
		}
		model.addAttribute("bmr", bmr);

		if (sex == null || weight == null || height == null || age == null) {
			model.addAttribute("message", "種目を選択してください");
			return "bmr";

		}

		return "bmrResult";
	}
}
