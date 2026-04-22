package com.example.ch3scheduleappdevelop.schedule.dto;

import com.example.ch3scheduleappdevelop.schedule.entity.Schedule;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class SchedulePageResponseDto {

    private final String title;
    private final String content;
    private final int commentCount;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final String name;

    public static SchedulePageResponseDto from(Schedule schedule) {
        return new SchedulePageResponseDto(
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getCommentList().size(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt(),
                schedule.getUser().getName()
        );
    }
}
