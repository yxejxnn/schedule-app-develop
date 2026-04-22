package com.example.ch3scheduleappdevelop.user.dto;

import com.example.ch3scheduleappdevelop.user.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class UserCreateResponseDto {

    private final Long id;
    private final String name;
    private final String email;
    private final LocalDateTime createdAt;

    public static UserCreateResponseDto from(User user) {
        return new UserCreateResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt()
        );
    }
}
