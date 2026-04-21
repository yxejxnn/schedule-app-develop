package com.example.ch3scheduleappdevelop.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ScheduleCreateRequestDto {

    @NotBlank(message = "제목은 필수입니다.")
    @Size(max = 10, message = "제목은 10글자 이내여야 합니다.")
    private String title;

    private String content;

    @NotNull(message = "유저 아이디는 필수입니다.")
    private Long userId;
}
