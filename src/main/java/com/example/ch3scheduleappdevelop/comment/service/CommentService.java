package com.example.ch3scheduleappdevelop.comment.service;

import com.example.ch3scheduleappdevelop.comment.dto.CommentCreateRequestDto;
import com.example.ch3scheduleappdevelop.comment.dto.CommentCreateResponseDto;
import com.example.ch3scheduleappdevelop.comment.dto.CommentGetAllResponseDto;
import com.example.ch3scheduleappdevelop.comment.entity.Comment;
import com.example.ch3scheduleappdevelop.comment.repository.CommentRepository;
import com.example.ch3scheduleappdevelop.common.exception.ScheduleNotFoundException;
import com.example.ch3scheduleappdevelop.common.exception.UserNotFoundException;
import com.example.ch3scheduleappdevelop.schedule.entity.Schedule;
import com.example.ch3scheduleappdevelop.schedule.repository.ScheduleRepository;
import com.example.ch3scheduleappdevelop.user.entity.User;
import com.example.ch3scheduleappdevelop.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    // 댓글 생성
    @Transactional
    public CommentCreateResponseDto save(CommentCreateRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getUserId()).orElseThrow(
                UserNotFoundException::new
        );

        Schedule schedule = scheduleRepository.findById(requestDto.getScheduleId()).orElseThrow(
                ScheduleNotFoundException::new
        );

        Comment comment = new Comment(
                requestDto.getContent(),
                user,
                schedule
        );

        Comment savedComment = commentRepository.save(comment);

        return new CommentCreateResponseDto(
                savedComment.getId(),
                savedComment.getSchedule().getId(),
                savedComment.getUser().getId(),
                savedComment.getContent(),
                savedComment.getCreatedAt()
        );
    }

    // 댓글 다건 조회
    @Transactional(readOnly = true)
    public List<CommentGetAllResponseDto> getAll() {

        List<Comment> commentList = commentRepository.findAll();

        return commentList.stream()
                .map(comment -> new CommentGetAllResponseDto(
                        comment.getId(),
                        comment.getSchedule().getId(),
                        comment.getUser().getId(),
                        comment.getContent(),
                        comment.getCreatedAt(),
                        comment.getUpdatedAt()
                ))
                .collect(Collectors.toList());
    }
}
