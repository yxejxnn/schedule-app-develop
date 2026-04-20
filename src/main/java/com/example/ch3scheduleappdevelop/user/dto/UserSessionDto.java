package com.example.ch3scheduleappdevelop.user.dto;

import lombok.Getter;

@Getter
public class UserSessionDto {

    private final Long id;
    private final String userEmail;

    public UserSessionDto(Long id, String userEmail) {
        this.id = id;
        this.userEmail = userEmail;
    }
}
