package com.example.demo.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "exercise_records")
public class Records {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "user_id")
	private Integer userId;
	@Column(name = "event_id")
	private Integer eventId;
	private Date date;
	private Integer time;
	@Column(name = "burn_calorie")
	private Integer burnCalorie;
	private Integer weight;

	//	外部キーは後で

	public Records(Date date, Integer time, Integer weight) {
		this.date = date;
		this.time = time;
		this.weight = weight;
		this.eventId = eventId;
	}

	public Records() {
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public Integer getBurnCalorie() {
		return burnCalorie;
	}

	public void setBurnCalorie(Integer burnCalorie) {
		this.burnCalorie = burnCalorie;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

}
