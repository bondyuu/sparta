package com.sparta.week3pjt.controller;

import com.sparta.week3pjt.model.Comment;
import com.sparta.week3pjt.model.User;
import com.sparta.week3pjt.repository.CommentRepository;
import com.sparta.week3pjt.repository.RecommentRepository;
import com.sparta.week3pjt.security.UserDetailsImpl;
import com.sparta.week3pjt.service.CommentLikeService;
import com.sparta.week3pjt.service.RecommentLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentLikeController {

    private final CommentRepository commentRepository;
    private final CommentLikeService commentLikeService;

    @PostMapping("/api/comment/like/{commentId}")
    public Comment commentLike(@PathVariable Long commentId,
                                   @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("대댓글이 없습니다.")
        );

        User user = userDetails.getUser();

        return commentLikeService.like(comment, user);
    }

}