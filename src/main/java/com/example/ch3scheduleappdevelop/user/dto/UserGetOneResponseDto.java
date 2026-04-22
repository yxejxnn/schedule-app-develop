package com.example.ch3scheduleappdevelop.user.dto;

import com.example.ch3scheduleappdevelop.user.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class UserGetOneResponseDto {

    private final Long id;
    private final String name;
    private final String email;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public static UserGetOneResponseDto from(User user) {
        return new UserGetOneResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}
