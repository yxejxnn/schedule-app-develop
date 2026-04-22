package com.example.ch3scheduleappdevelop.schedule.dto;

import com.example.ch3scheduleappdevelop.schedule.entity.Schedule;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ScheduleUpdateResponseDto {

    private final Long id;
    private final String title;
    private final String content;
    private final Long userId;
    private final LocalDateTime updatedAt;

    public static ScheduleUpdateResponseDto from(Schedule schedule) {
        return new ScheduleUpdateResponseDto(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getUser().getId(),
                schedule.getUpdatedAt()
        );
    }
}
