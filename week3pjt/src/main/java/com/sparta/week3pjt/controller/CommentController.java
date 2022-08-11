package com.sparta.week3pjt.controller;

import com.sparta.week3pjt.dto.CommentRequestDto;
import com.sparta.week3pjt.model.Comment;
import com.sparta.week3pjt.repository.CommentRepository;
import com.sparta.week3pjt.repository.PostingRepository;
import com.sparta.week3pjt.security.UserDetailsImpl;
import com.sparta.week3pjt.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final CommentRepository commentRepository;

    private final PostingRepository postingRepository;

    @GetMapping("/api/posting/{postingId}/comments")
    public List<Comment> getComments(@PathVariable Long postingId) {

        return commentService.getComments(postingId);
    }

    @PostMapping("/api/posting/{postingId}/comment")
    public Comment createComment(@PathVariable Long postingId, @RequestBody CommentRequestDto requestDto,
                                 @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return commentService.createComment(postingId, requestDto, userDetails);
    }

    @PutMapping("/api/posting/{postingId}/{commentId}")
    public Comment updateComment(@PathVariable Long commentId, @RequestBody CommentRequestDto requestDto,
                                 @AuthenticationPrincipal UserDetailsImpl userDetails) {

        //댓글 작성자와 로그인 유저 일치확인
        Comment comment = commentRepository.findByCommentId(commentId);
        if(!userDetails.getUser().getUsername().equals(comment.getUsername())) {
            throw new IllegalArgumentException("작성자만 수정할 수 있습니다.");
        }

        return commentService.updateComment(commentId, requestDto);
    }

    @DeleteMapping("/api/posting/{postingId}/{commentId}")
    public String deleteComment(@PathVariable Long postingId, @PathVariable Long commentId,
                                @AuthenticationPrincipal UserDetailsImpl userDetails) {

        //댓글 작석자와 로그인 유저 일치확인
        Comment comment = commentRepository.findByCommentId(commentId);
        if(!userDetails.getUser().getUsername().equals(comment.getUsername())) {
            throw new IllegalArgumentException("작성자만 삭제할 수 있습니다.");
        }

        commentService.deleteComment(postingId, commentId);
        return "Deleted Successfully";
    }
}
