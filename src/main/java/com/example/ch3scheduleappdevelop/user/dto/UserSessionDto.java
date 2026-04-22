package com.example.ch3scheduleappdevelop.user.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserSessionDto {

    private final Long id;
    private final String email;
}
