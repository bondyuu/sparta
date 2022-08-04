package com.sparta.week32.controller;


import com.sparta.week32.dto.CommentRequestDto;
import com.sparta.week32.model.Comment;
import com.sparta.week32.repository.CommentRepository;
import com.sparta.week32.security.UserDetailsImpl;
import com.sparta.week32.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final CommentRepository commentRepository;

    @GetMapping("/api/comments/{postingId}")
    public List<Comment> getComments(@PathVariable Long postingId) {

        return commentRepository.findAllByPostingId(postingId);
    }

    @PostMapping("/api/auth/comments/{postingId}")
    public Comment createComment(@PathVariable Long postingId, @RequestBody CommentRequestDto requestDto,
                                 @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return commentService.createComment(postingId, requestDto, userDetails);
    }

    @PutMapping("/api/auth/comments/{postingId}/{commentId}")
    public Comment updateComment(@PathVariable Long postingId, @PathVariable Long commentId, @RequestBody CommentRequestDto requestDto,
                                 @AuthenticationPrincipal UserDetailsImpl userDetails) {

        Comment comment = commentRepository.findByCommentId(commentId);


        if(!userDetails.getUser().getUsername().equals(comment.getUsername())) {
            throw new IllegalArgumentException("작성자만 수정할 수 있습니다.");
        }

        return commentService.updateComment(postingId, commentId, requestDto);
    }

    @DeleteMapping("/api/auth/comments/{postingId}/{commentId}")
    public String deleteComment(@PathVariable Long postingId, @PathVariable Long commentId,
                                @AuthenticationPrincipal UserDetailsImpl userDetails) {

        Comment comment = commentRepository.findByCommentId(commentId);

        if(!userDetails.getUser().getUsername().equals(comment.getUsername())) {
            throw new IllegalArgumentException("작성자만 삭제할 수 있습니다.");
        }

        commentService.deleteComment(postingId, commentId);
        return "Deleted Successfully";
    }
}
