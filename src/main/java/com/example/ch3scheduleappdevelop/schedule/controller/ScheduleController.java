package com.example.ch3scheduleappdevelop.schedule.controller;

import com.example.ch3scheduleappdevelop.schedule.dto.ScheduleCreateRequestDto;
import com.example.ch3scheduleappdevelop.schedule.dto.ScheduleCreateResponseDto;
import com.example.ch3scheduleappdevelop.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleCreateResponseDto> scheduleCreate(@RequestBody ScheduleCreateRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.save(requestDto));
    }
}
