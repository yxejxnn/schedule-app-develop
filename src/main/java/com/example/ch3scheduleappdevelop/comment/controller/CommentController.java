package com.example.ch3scheduleappdevelop.comment.controller;

import com.example.ch3scheduleappdevelop.comment.dto.CommentCreateRequestDto;
import com.example.ch3scheduleappdevelop.comment.dto.CommentCreateResponseDto;
import com.example.ch3scheduleappdevelop.comment.dto.CommentGetAllResponseDto;
import com.example.ch3scheduleappdevelop.comment.service.CommentService;
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

    @PostMapping
    public ResponseEntity<CommentCreateResponseDto> commentCreate(@RequestBody CommentCreateRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.save(requestDto));
    }

    @GetMapping
    public ResponseEntity<List<CommentGetAllResponseDto>> commentGetAll() {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getAll());
    }
}
