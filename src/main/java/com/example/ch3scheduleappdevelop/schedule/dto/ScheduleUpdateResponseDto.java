package com.example.ch3scheduleappdevelop.schedule.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleUpdateResponseDto {

    private final Long id;
    private final String title;
    private final String content;
    private final String authorName;
    private final LocalDateTime updatedAt;

    public ScheduleUpdateResponseDto(Long id, String title, String content, String authorName, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.authorName = authorName;
        this.updatedAt = updatedAt;
    }
}
