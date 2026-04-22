package com.example.ch3scheduleappdevelop.user.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class UserUpdateResponseDto {

    private final Long id;
    private final String name;
    private final String email;
    private final LocalDateTime updatedAt;

}
