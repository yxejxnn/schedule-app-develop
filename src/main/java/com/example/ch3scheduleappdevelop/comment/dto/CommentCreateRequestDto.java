package com.example.ch3scheduleappdevelop.comment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CommentCreateRequestDto {

    @NotNull(message = "유저 아이디는 필수입니다.")
    private Long userId;

    @NotNull(message = "일정 아이디는 필수입니다.")
    private Long scheduleId;

    @NotBlank(message = "댓글 내용은 필수입니다.")
    private String content;
}
