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

    // 회원가입 (유저 생성)
    @Transactional
    public UserCreateResponseDto save(UserCreateRequestDto requestDto) {
        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());

        User user = new User(
                requestDto.getName(),
                requestDto.getEmail(),
                encodedPassword
        );

        User savedUser = userRepository.save(user);

        return UserCreateResponseDto.from(savedUser);
    }

    // 유저 다건 조회
    @Transactional(readOnly = true)
    public List<UserGetAllResponseDto> getAll() {

        List<User> userList = userRepository.findAll();

        return userList.stream()
                .map(user -> UserGetAllResponseDto.from(user))
                .collect(Collectors.toList());
    }

    // 유저 단건 조회
    @Transactional(readOnly = true)
    public UserGetOneResponseDto getOne(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                UserNotFoundException::new
        );

        return UserGetOneResponseDto.from(user);
    }

    // 유저 수정
    @Transactional
    public UserUpdateResponseDto update(Long userId, UserUpdateRequestDto requestDto) {
        User user = userRepository.findById(userId).orElseThrow(
                UserNotFoundException::new
        );

        user.update(requestDto.getName(), requestDto.getEmail());

        return UserUpdateResponseDto.from(user);
    }

    // 유저 삭제
    @Transactional
    public void delete(Long userId) {
        boolean existence = userRepository.existsById(userId);
        if (!existence) {
            throw new UserNotFoundException();
        }
        userRepository.deleteById(userId);
    }

    // 로그인
    @Transactional(readOnly = true)
    public UserSessionDto login(UserLoginRequestDto requestDto) {
        User user = userRepository.findByEmail(requestDto.getEmail()).orElseThrow(
                InvalidCredentialsException::new
        );
        // 이메일과 비밀번호 모두 같은 예외를 던져서 어느 쪽이 틀렸는지 노출하지 않음 (보안)
        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException();
        }
        return UserSessionDto.from(user);
    }
}
