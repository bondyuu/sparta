package com.sparta.week3pjt.repository;

import com.sparta.week3pjt.model.Comment;
import com.sparta.week3pjt.model.Posting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByPosting(Posting posting);
    List<Comment> findAllByUsername(String username);
    Comment findByCommentId(Long commentId);


}

