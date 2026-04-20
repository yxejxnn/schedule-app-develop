package com.example.ch3scheduleappdevelop.user.controller;

import com.example.ch3scheduleappdevelop.user.dto.*;
import com.example.ch3scheduleappdevelop.user.service.UserService;
import jakarta.servlet.http.HttpSession;
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
    public ResponseEntity<UserCreateResponseDto> userCreate(@RequestBody UserCreateRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(requestDto));
    }

    @GetMapping
    public ResponseEntity<List<UserGetAllResponseDto>> userGetAll() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAll());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserGetOneResponseDto> userGetOne(@PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getOne(userId));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserUpdateResponseDto> userUpdate(@PathVariable Long userId, @RequestBody UserUpdateRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.update(userId, requestDto));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> userDelete(@PathVariable Long userId) {
        userService.delete(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginRequestDto requestDto, HttpSession session) {
        UserSessionDto userSessionDto = userService.login(requestDto);
        session.setAttribute("loginUser", userSessionDto);
        return ResponseEntity.status(HttpStatus.OK).body("로그인 성공!");
    }
}
