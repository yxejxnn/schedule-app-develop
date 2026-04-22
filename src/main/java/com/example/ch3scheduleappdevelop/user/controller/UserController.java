package com.example.ch3scheduleappdevelop.user.controller;

import com.example.ch3scheduleappdevelop.common.exception.ForbiddenException;
import com.example.ch3scheduleappdevelop.common.exception.UnauthorizedException;
import com.example.ch3scheduleappdevelop.user.dto.*;
import com.example.ch3scheduleappdevelop.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    // 회원가입 (유저 생성)
    @PostMapping
    public ResponseEntity<UserCreateResponseDto> userCreate(@Valid @RequestBody UserCreateRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(requestDto));
    }

    // 유저 다건 조회
    @GetMapping
    public ResponseEntity<List<UserGetAllResponseDto>> userGetAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    // 유저 단건 조회
    @GetMapping("/{userId}")
    public ResponseEntity<UserGetOneResponseDto> userGetOne(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getOne(userId));
    }

    // 유저 수정
    @PutMapping("/{userId}")
    public ResponseEntity<UserUpdateResponseDto> userUpdate(@PathVariable Long userId, @Valid @RequestBody UserUpdateRequestDto requestDto, HttpSession session) {
        // 로그인 여부 확인 후 본인 여부 검증
        UserSessionDto loginUser = (UserSessionDto) session.getAttribute("loginUser");
        if (loginUser == null) {
            throw new UnauthorizedException();
        }
        if (!loginUser.getId().equals(userId)) {
            throw new ForbiddenException();
        }

        return ResponseEntity.ok(userService.update(userId, requestDto));
    }

    // 유저 삭제
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> userDelete(@PathVariable Long userId, HttpSession session) {
        UserSessionDto loginUser = (UserSessionDto) session.getAttribute("loginUser");

        if (loginUser == null) {
            throw new UnauthorizedException();
        }
        if (!loginUser.getId().equals(userId)) {
            throw new ForbiddenException();
        }
        userService.delete(userId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody UserLoginRequestDto requestDto, HttpSession session) {
        UserSessionDto userSessionDto = userService.login(requestDto);
        session.setAttribute("loginUser", userSessionDto);
        return ResponseEntity.ok("로그인 성공!");
    }

    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("로그아웃 성공!");
    }
}
