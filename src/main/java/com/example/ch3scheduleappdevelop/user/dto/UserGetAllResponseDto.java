package com.example.ch3scheduleappdevelop.user.dto;

import lombok.Getter;

@Getter
public class UserGetAllResponseDto {

    private final Long id;
    private final String userName;

    public UserGetAllResponseDto(Long id, String userName) {
        this.id = id;
        this.userName = userName;
    }
}
