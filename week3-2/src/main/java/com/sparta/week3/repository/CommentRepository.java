package com.sparta.week3.repository;

import com.sparta.week3.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByPostingId(Long postingId);
    Comment findByCommentIdAndPostingId(Long commentId, Long postingId);
    Comment findByCommentId(Long commentId);
    void deleteAllByPostingId(Long postingId);

}

