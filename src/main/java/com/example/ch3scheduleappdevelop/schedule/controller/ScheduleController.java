package com.example.ch3scheduleappdevelop.schedule.controller;

import com.example.ch3scheduleappdevelop.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;
}
