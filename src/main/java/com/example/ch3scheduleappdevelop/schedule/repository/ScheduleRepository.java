package com.example.ch3scheduleappdevelop.schedule.repository;

import com.example.ch3scheduleappdevelop.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
