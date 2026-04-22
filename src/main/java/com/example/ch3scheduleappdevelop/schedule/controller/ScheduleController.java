package com.example.ch3scheduleappdevelop.schedule.controller;

import com.example.ch3scheduleappdevelop.common.exception.UnauthorizedException;
import com.example.ch3scheduleappdevelop.schedule.dto.*;
import com.example.ch3scheduleappdevelop.schedule.service.ScheduleService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    // 일정 생성
    @PostMapping
    public ResponseEntity<ScheduleCreateResponseDto> scheduleCreate(@Valid @RequestBody ScheduleCreateRequestDto requestDto, HttpSession session) {
        if (session.getAttribute("loginUser") == null) {
            throw new UnauthorizedException();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.save(requestDto));
    }

    // 일정 다건 조회
    @GetMapping
    public ResponseEntity<List<ScheduleGetAllResponseDto>> scheduleGetAll() {
        return ResponseEntity.ok(scheduleService.getAll());
    }

    // 일정 단건 조회
    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleGetOneResponseDto> scheduleGetOne(@PathVariable Long scheduleId) {
        return ResponseEntity.ok(scheduleService.getOne(scheduleId));
    }

    // 일정 수정
    @PutMapping("/{scheduleId}")
    public ResponseEntity<ScheduleUpdateResponseDto> scheduleUpdate(@PathVariable Long scheduleId, @Valid @RequestBody ScheduleUpdateRequestDto requestDto, HttpSession session) {
        if (session.getAttribute("loginUser") == null) {
            throw new UnauthorizedException();
        }
        return ResponseEntity.ok(scheduleService.update(scheduleId, requestDto));
    }

    // 일정 삭제
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> scheduleDelete(@PathVariable Long scheduleId, HttpSession session) {
        if (session.getAttribute("loginUser") == null) {
            throw new UnauthorizedException();
        }
        scheduleService.delete(scheduleId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // 일정 페이지 조회
    @GetMapping("/page")
    public ResponseEntity<Page<SchedulePageResponseDto>> getPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(scheduleService.getPage(page, size));
    }
}
