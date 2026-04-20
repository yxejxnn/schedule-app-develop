package com.example.ch3scheduleappdevelop.user.controller;

import com.example.ch3scheduleappdevelop.user.dto.UserCreateRequestDto;
import com.example.ch3scheduleappdevelop.user.dto.UserCreateResponseDto;
import com.example.ch3scheduleappdevelop.user.dto.UserGetAllResponseDto;
import com.example.ch3scheduleappdevelop.user.service.UserService;
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
}
