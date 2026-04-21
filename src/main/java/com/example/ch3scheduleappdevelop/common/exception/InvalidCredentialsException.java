package com.example.ch3scheduleappdevelop.common.exception;

import org.springframework.http.HttpStatus;

public class InvalidCredentialsException extends ServiceException {
    public InvalidCredentialsException() {
        super(HttpStatus.UNAUTHORIZED, "이메일 또는 비밀번호가 일치하지 않습니다.");
    }
}
