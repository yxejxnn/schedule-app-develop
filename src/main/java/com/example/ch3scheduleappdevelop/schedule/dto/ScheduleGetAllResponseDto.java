package com.example.ch3scheduleappdevelop.schedule.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleGetAllResponseDto {

    private final Long id;
    private final String title;
    private final Long userId;
    private final LocalDateTime createdAt;

    public ScheduleGetAllResponseDto(Long id, String title, Long userId, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.userId = userId;
        this.createdAt = createdAt;
    }
}
