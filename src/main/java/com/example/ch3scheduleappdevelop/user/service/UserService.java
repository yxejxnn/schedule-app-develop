package com.example.ch3scheduleappdevelop.user.service;

import com.example.ch3scheduleappdevelop.user.dto.UserCreateRequestDto;
import com.example.ch3scheduleappdevelop.user.dto.UserCreateResponseDto;
import com.example.ch3scheduleappdevelop.user.entity.User;
import com.example.ch3scheduleappdevelop.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserCreateResponseDto save(UserCreateRequestDto requestDto) {
        User user = new User(
                requestDto.getUserName(),
                requestDto.getUserEmail()
        );

        User savedUser = userRepository.save(user);

        return new UserCreateResponseDto(
                savedUser.getId(),
                savedUser.getUserName(),
                savedUser.getUserEmail(),
                savedUser.getCreatedAt()
        );
    }
}
