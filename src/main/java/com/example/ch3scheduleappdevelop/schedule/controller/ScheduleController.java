package com.example.ch3scheduleappdevelop.schedule.controller;

import com.example.ch3scheduleappdevelop.common.exception.UnauthorizedException;
import com.example.ch3scheduleappdevelop.schedule.dto.*;
import com.example.ch3scheduleappdevelop.schedule.service.ScheduleService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<ScheduleCreateResponseDto> scheduleCreate(@Valid @RequestBody ScheduleCreateRequestDto requestDto, HttpSession session) {
        if (session.getAttribute("loginUser") == null) {
            throw new UnauthorizedException();
        }
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

    @PutMapping("/{scheduleId}")
    public ResponseEntity<ScheduleUpdateResponseDto> scheduleUpdate(@PathVariable Long scheduleId, @Valid @RequestBody ScheduleUpdateRequestDto requestDto, HttpSession session) {
        if (session.getAttribute("loginUser") == null) {
            throw new UnauthorizedException();
        }
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.update(scheduleId, requestDto));
    }

    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> scheduleDelete(@PathVariable Long scheduleId, HttpSession session) {
        if (session.getAttribute("loginUser") == null) {
            throw new UnauthorizedException();
        }
        scheduleService.delete(scheduleId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
