package com.example.ch3scheduleappdevelop.comment.repository;

import com.example.ch3scheduleappdevelop.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
