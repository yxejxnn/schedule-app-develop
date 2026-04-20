package com.example.ch3scheduleappdevelop.user.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserUpdateResponseDto {

    private final Long id;
    private final String userName;
    private final String userEmail;
    private final LocalDateTime updatedAt;

    public UserUpdateResponseDto(Long id, String userName, String userEmail, LocalDateTime updatedAt) {
        this.id = id;
        this.userName = userName;
        this.userEmail = userEmail;
        this.updatedAt = updatedAt;
    }
}
