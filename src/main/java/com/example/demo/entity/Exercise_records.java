package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "exercise_records")
public class Exercise_records {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "event_id")
	private Integer eventId;

	private LocalDate date;

	private Integer time;

	private Double weight;

	@Column(name = "burn_calorie")
	private Double burnCalorie;

	private Integer progress;

	private String memo;

	public Exercise_records() {
	}

	public Exercise_records(Integer userId, Integer eventId, LocalDate date, Integer time, Double weight,
			Double burnCalorie, Integer progress, String memo) {
		this.userId = userId;
		this.eventId = eventId;
		this.date = date;
		this.time = time;
		this.weight = weight;
		this.burnCalorie = burnCalorie;
		this.progress = progress;
		this.memo = memo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getBurnCalorie() {
		return burnCalorie;
	}

	public void setBurnCalorie(Double burnCalorie) {
		this.burnCalorie = burnCalorie;
	}

	public Integer getProgress() {
		return progress;
	}

	public void setProgress(Integer progress) {
		this.progress = progress;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
}
