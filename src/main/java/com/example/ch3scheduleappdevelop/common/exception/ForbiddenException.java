package com.example.ch3scheduleappdevelop.common.exception;

import org.springframework.http.HttpStatus;

public class ForbiddenException extends ServiceException {
    public ForbiddenException() {
        super(HttpStatus.FORBIDDEN, "본인만 수정/삭제할 수 있습니다.");
    }
}
