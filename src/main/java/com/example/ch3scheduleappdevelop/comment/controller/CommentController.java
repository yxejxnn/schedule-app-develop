package com.example.ch3scheduleappdevelop.comment.controller;

import com.example.ch3scheduleappdevelop.comment.dto.CommentCreateRequestDto;
import com.example.ch3scheduleappdevelop.comment.dto.CommentCreateResponseDto;
import com.example.ch3scheduleappdevelop.comment.dto.CommentGetAllResponseDto;
import com.example.ch3scheduleappdevelop.comment.service.CommentService;
import com.example.ch3scheduleappdevelop.common.exception.UnauthorizedException;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    // 댓글 생성
    @PostMapping
    public ResponseEntity<CommentCreateResponseDto> commentCreate(@Valid @RequestBody CommentCreateRequestDto requestDto, HttpSession session) {
        if (session.getAttribute("loginUser") == null) {
            throw new UnauthorizedException();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.save(requestDto));
    }

    // 댓글 다건 조회
    @GetMapping
    public ResponseEntity<List<CommentGetAllResponseDto>> commentGetAll() {
        return ResponseEntity.ok(commentService.getAll());
    }
}
