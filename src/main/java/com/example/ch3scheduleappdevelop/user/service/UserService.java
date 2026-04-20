package com.example.ch3scheduleappdevelop.user.service;

import com.example.ch3scheduleappdevelop.user.dto.*;
import com.example.ch3scheduleappdevelop.user.entity.User;
import com.example.ch3scheduleappdevelop.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserCreateResponseDto save(UserCreateRequestDto requestDto) {
        User user = new User(
                requestDto.getUserName(),
                requestDto.getUserEmail(),
                requestDto.getPassword()
        );

        User savedUser = userRepository.save(user);

        return new UserCreateResponseDto(
                savedUser.getId(),
                savedUser.getUserName(),
                savedUser.getUserEmail(),
                savedUser.getCreatedAt()
        );
    }

    @Transactional(readOnly = true)
    public List<UserGetAllResponseDto> getAll() {

        List<User> userList = userRepository.findAll();

        return userList.stream()
                .map(user -> new UserGetAllResponseDto(
                        user.getId(),
                        user.getUserName()
                ))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserGetOneResponseDto getOne(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalStateException("해당 유저가 존재하지 않습니다.")
        );

        return new UserGetOneResponseDto(
                user.getId(),
                user.getUserName(),
                user.getUserEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    @Transactional
    public UserUpdateResponseDto update(Long userId, UserUpdateRequestDto requestDto) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalStateException("해당 유저가 존재하지 않습니다.")
        );

        user.update(requestDto.getUserName(), requestDto.getUserEmail());
        userRepository.flush();

        return new UserUpdateResponseDto(
                user.getId(),
                user.getUserName(),
                user.getUserEmail(),
                user.getUpdatedAt()
        );
    }

    @Transactional
    public void delete(Long userId) {
        boolean existence = userRepository.existsById(userId);
        if (!existence) {
            throw new IllegalStateException("해당 유저가 존재하지 않습니다.");
        }
        userRepository.deleteById(userId);
    }

    @Transactional(readOnly = true)
    public UserSessionDto login(UserLoginRequestDto requestDto) {
        User user = userRepository.findByUserEmail(requestDto.getUserEmail()).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 이메일입니다.")
        );
        if (!user.getPassword().equals(requestDto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        return new UserSessionDto(user.getId(), user.getUserEmail());
    }
}
