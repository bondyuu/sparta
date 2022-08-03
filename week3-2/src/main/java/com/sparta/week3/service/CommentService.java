package com.sparta.week3.service;


import com.sparta.week3.dto.CommentRequestDto;
import com.sparta.week3.model.Comment;
import com.sparta.week3.repository.CommentRepository;
import com.sparta.week3.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    @Transactional
    public Comment createComment(Long postingId, CommentRequestDto requestDto, UserDetailsImpl userDetails) {
        Comment comment = new Comment(requestDto);
        comment.setPostingId(postingId);
        comment.setUsername(userDetails.getUser().getUsername());
        comment.setPassword(userDetails.getUser().getPassword());

        commentRepository.save(comment);
        return comment;
    }

    @Transactional
    public Comment updateComment(Long postingId, Long commentId, CommentRequestDto requestDto) {
        Comment comment = commentRepository.findByCommentIdAndPostingId(commentId, postingId);

        comment.setComment(requestDto.getComment());
        return comment;
    }



    public void deleteComment(Long postingId, Long commentId) {
        Comment comment = commentRepository.findByCommentIdAndPostingId(commentId, postingId);
        commentRepository.delete(comment);

    }
}
