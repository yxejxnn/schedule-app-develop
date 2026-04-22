package com.example.ch3scheduleappdevelop.schedule.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ScheduleGetAllResponseDto {

    private final Long id;
    private final String title;
    private final Long userId;
    private final LocalDateTime createdAt;

}
