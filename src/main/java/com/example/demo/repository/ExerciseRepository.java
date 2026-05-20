package com.example.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Records;

public interface ExerciseRepository extends JpaRepository<Records, Integer> {

	List<Records> findByDateAndTimeAndWeight(Date date, Integer time, Integer weight);
}