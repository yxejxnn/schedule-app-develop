package com.example.ch3scheduleappdevelop.schedule.dto;

import com.example.ch3scheduleappdevelop.schedule.entity.Schedule;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ScheduleCreateResponseDto {

    private final Long id;
    private final String title;
    private final String content;
    private final Long userId;
    private final LocalDateTime createdAt;

    public static ScheduleCreateResponseDto from(Schedule schedule) {
        return new ScheduleCreateResponseDto(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getUser().getId(),
                schedule.getCreatedAt()
        );
    }
}
