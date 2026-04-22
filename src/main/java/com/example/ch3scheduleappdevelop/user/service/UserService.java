package com.example.ch3scheduleappdevelop.user.service;

import com.example.ch3scheduleappdevelop.common.exception.InvalidCredentialsException;
import com.example.ch3scheduleappdevelop.common.exception.UserNotFoundException;
import com.example.ch3scheduleappdevelop.config.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserCreateResponseDto save(UserCreateRequestDto requestDto) {
        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());

        User user = new User(
                requestDto.getName(),
                requestDto.getEmail(),
                encodedPassword
        );

        User savedUser = userRepository.save(user);

        return new UserCreateResponseDto(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getCreatedAt()
        );
    }

    @Transactional(readOnly = true)
    public List<UserGetAllResponseDto> getAll() {

        List<User> userList = userRepository.findAll();

        return userList.stream()
                .map(user -> new UserGetAllResponseDto(
                        user.getId(),
                        user.getName()
                ))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserGetOneResponseDto getOne(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                UserNotFoundException::new
        );

        return new UserGetOneResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    @Transactional
    public UserUpdateResponseDto update(Long userId, UserUpdateRequestDto requestDto) {
        User user = userRepository.findById(userId).orElseThrow(
                UserNotFoundException::new
        );

        user.update(requestDto.getName(), requestDto.getEmail());

        return new UserUpdateResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getUpdatedAt()
        );
    }

    @Transactional
    public void delete(Long userId) {
        boolean existence = userRepository.existsById(userId);
        if (!existence) {
            throw new UserNotFoundException();
        }
        userRepository.deleteById(userId);
    }

    @Transactional(readOnly = true)
    public UserSessionDto login(UserLoginRequestDto requestDto) {
        User user = userRepository.findByEmail(requestDto.getEmail()).orElseThrow(
                InvalidCredentialsException::new
        );
        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException();
        }
        return new UserSessionDto(user.getId(), user.getEmail());
    }
}
