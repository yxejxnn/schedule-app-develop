package com.example.ch3scheduleappdevelop.common.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends ServiceException {
    public UserNotFoundException() {
        super(HttpStatus.NOT_FOUND, "해당 유저가 존재하지 않습니다.");
    }
}
