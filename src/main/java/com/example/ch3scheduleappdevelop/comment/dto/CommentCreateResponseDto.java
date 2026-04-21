package com.example.ch3scheduleappdevelop.comment.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentCreateResponseDto {

    private final Long id;
    private final Long scheduleId;
    private final Long userId;
    private final String content;
    private final LocalDateTime createdAt;

    public CommentCreateResponseDto(Long id, Long scheduleId, Long userId, String content, LocalDateTime createdAt) {
        this.id = id;
        this.scheduleId = scheduleId;
        this.userId = userId;
        this.content = content;
        this.createdAt = createdAt;
    }
}
