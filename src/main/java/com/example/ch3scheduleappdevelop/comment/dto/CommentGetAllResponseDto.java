package com.example.ch3scheduleappdevelop.comment.dto;

import com.example.ch3scheduleappdevelop.comment.entity.Comment;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class CommentGetAllResponseDto {

    private final Long id;
    private final Long scheduleId;
    private final Long userId;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public static CommentGetAllResponseDto from(Comment comment) {
        return new CommentGetAllResponseDto(
                comment.getId(),
                comment.getSchedule().getId(),
                comment.getUser().getId(),
                comment.getContent(),
                comment.getCreatedAt(),
                comment.getUpdatedAt()
        );
    }
}
