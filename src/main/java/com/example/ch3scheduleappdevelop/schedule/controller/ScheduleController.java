package com.example.ch3scheduleappdevelop.schedule.controller;

import com.example.ch3scheduleappdevelop.schedule.dto.ScheduleCreateRequestDto;
import com.example.ch3scheduleappdevelop.schedule.dto.ScheduleCreateResponseDto;
import com.example.ch3scheduleappdevelop.schedule.dto.ScheduleGetAllResponseDto;
import com.example.ch3scheduleappdevelop.schedule.dto.ScheduleGetOneResponseDto;
import com.example.ch3scheduleappdevelop.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleCreateResponseDto> scheduleCreate(@RequestBody ScheduleCreateRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.save(requestDto));
    }

    @GetMapping
    public ResponseEntity<List<ScheduleGetAllResponseDto>> scheduleGetAll() {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.getAll());
    }

    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleGetOneResponseDto> scheduleGetOne(@PathVariable Long scheduleId) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.getOne(scheduleId));
    }
}
