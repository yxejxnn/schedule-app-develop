package com.example.ch3scheduleappdevelop.schedule.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleGetAllResponseDto {

    private final Long id;
    private final String title;
    private final String authorName;
    private final LocalDateTime createdAt;

    public ScheduleGetAllResponseDto(Long id, String title, String authorName, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.authorName = authorName;
        this.createdAt = createdAt;
    }
}
