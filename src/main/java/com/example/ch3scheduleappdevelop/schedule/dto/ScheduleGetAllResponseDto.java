package com.example.ch3scheduleappdevelop.schedule.dto;

import com.example.ch3scheduleappdevelop.schedule.entity.Schedule;
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

    public static ScheduleGetAllResponseDto from(Schedule schedule) {
        return new ScheduleGetAllResponseDto(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getUser().getId(),
                schedule.getCreatedAt()
        );
    }
}
