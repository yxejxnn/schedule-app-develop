package com.example.ch3scheduleappdevelop.user.controller;

import com.example.ch3scheduleappdevelop.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private UserService userService;
}
