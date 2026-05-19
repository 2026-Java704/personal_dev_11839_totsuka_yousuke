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
public class exercise_records {

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

	public exercise_records(Integer id, Integer userId, Integer eventId, Date date, Integer time, Integer burnCalorie) {
		super();
		this.id = id;
		this.userId = userId;
		this.eventId = eventId;
		this.date = date;
		this.time = time;
		this.burnCalorie = burnCalorie;
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

}
