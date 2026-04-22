package com.example.ch3scheduleappdevelop.user.dto;

import com.example.ch3scheduleappdevelop.user.entity.User;
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

    public static UserUpdateResponseDto from(User user) {
        return new UserUpdateResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getUpdatedAt()
        );
    }
}
