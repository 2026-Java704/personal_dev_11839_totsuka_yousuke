package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Events;
import com.example.demo.entity.Exercise_records;

public interface EventsRepository extends JpaRepository<Events, Integer> {

	List<Events> findAllByOrderByIdAsc();

	List<Events> findByUserIdOrderByIdAsc(Integer userId);
	//
	//	List<Events> findByEventId(Integer eventsid);

	void save(Exercise_records exercise_records);

}