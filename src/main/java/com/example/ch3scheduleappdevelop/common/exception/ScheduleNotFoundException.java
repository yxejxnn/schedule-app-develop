package com.example.ch3scheduleappdevelop.common.exception;

import org.springframework.http.HttpStatus;

public class ScheduleNotFoundException extends ServiceException {
    public ScheduleNotFoundException() {
        super(HttpStatus.NOT_FOUND, "해당 일정이 존재하지 않습니다.");
    }
}
