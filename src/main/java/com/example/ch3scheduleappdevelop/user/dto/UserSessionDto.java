package com.example.ch3scheduleappdevelop.user.dto;

import com.example.ch3scheduleappdevelop.user.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserSessionDto {

    private final Long id;
    private final String email;

    public static UserSessionDto from(User user) {
        return new UserSessionDto(
                user.getId(),
                user.getEmail()
        );
    }
}
