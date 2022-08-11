package com.sparta.week3pjt.service;

import com.sparta.week3pjt.model.Comment;
import com.sparta.week3pjt.model.CommentLike;
import com.sparta.week3pjt.model.User;
import com.sparta.week3pjt.repository.CommentLikeRepository;
import com.sparta.week3pjt.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentLikeService {

    private final CommentLikeRepository commentLikeRepository;
    private final CommentRepository commentRepository;

    public Comment like(Comment comment, User user) {

        String username = user.getUsername();

        Optional<CommentLike> commentLike =
                commentLikeRepository.findByCommentAndUsername(comment, username);

        if (commentLike.isEmpty()) {
            comment.setCntLike(comment.getCntLike() + 1L);
            CommentLike newcommentLike = new CommentLike(comment, username);
            commentLikeRepository.save(newcommentLike);

        } else {
            comment.setCntLike(comment.getCntLike() - 1L);
            commentLikeRepository.deleteById(commentLike.get().getCommentlikeId());

        }
        return comment;
    }

}