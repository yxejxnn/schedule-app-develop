package com.example.ch3scheduleappdevelop.user.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserCreateResponseDto {

    private final Long id;
    private final String userName;
    private final String userEmail;
    private final LocalDateTime createdAt;

    public UserCreateResponseDto(Long id, String userName, String userEmail, LocalDateTime createdAt) {
        this.id = id;
        this.userName = userName;
        this.userEmail = userEmail;
        this.createdAt = createdAt;
    }
}
