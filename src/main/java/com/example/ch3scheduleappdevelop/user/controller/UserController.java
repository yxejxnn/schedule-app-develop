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

    @PostMapping
    public ResponseEntity<UserCreateResponseDto> userCreate(@Valid @RequestBody UserCreateRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(requestDto));
    }

    @GetMapping
    public ResponseEntity<List<UserGetAllResponseDto>> userGetAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserGetOneResponseDto> userGetOne(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getOne(userId));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserUpdateResponseDto> userUpdate(@PathVariable Long userId, @Valid @RequestBody UserUpdateRequestDto requestDto, HttpSession session) {
        UserSessionDto loginUser = (UserSessionDto) session.getAttribute("loginUser");

        if (session.getAttribute("loginUser") == null) {
            throw new UnauthorizedException();
        }
        if (!loginUser.getId().equals(userId)) {
            throw new ForbiddenException();
        }
        return ResponseEntity.ok(userService.update(userId, requestDto));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> userDelete(@PathVariable Long userId, HttpSession session) {
        UserSessionDto loginUser = (UserSessionDto) session.getAttribute("loginUser");

        if (loginUser == null) {
            throw new ForbiddenException();
        }
        if (session.getAttribute("loginUser") == null) {
            throw new UnauthorizedException();
        }
        userService.delete(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody UserLoginRequestDto requestDto, HttpSession session) {
        UserSessionDto userSessionDto = userService.login(requestDto);
        session.setAttribute("loginUser", userSessionDto);
        return ResponseEntity.ok("로그인 성공!");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("로그아웃 성공!");
    }
}
