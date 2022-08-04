package com.sparta.week32.repository;

import com.sparta.week32.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByPostingId(Long postingId);
    Comment findByCommentIdAndPostingId(Long commentId, Long postingId);
    Comment findByCommentId(Long commentId);
    void deleteAllByPostingId(Long postingId);

}

