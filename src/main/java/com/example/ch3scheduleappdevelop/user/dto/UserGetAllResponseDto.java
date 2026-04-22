package com.example.ch3scheduleappdevelop.user.dto;

import com.example.ch3scheduleappdevelop.user.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserGetAllResponseDto {

    private final Long id;
    private final String name;

    public static UserGetAllResponseDto from(User user) {
        return new UserGetAllResponseDto(
                user.getId(),
                user.getName()
        );
    }
}
