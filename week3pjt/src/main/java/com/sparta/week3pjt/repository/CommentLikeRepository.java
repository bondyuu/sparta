package com.sparta.week3pjt.repository;

import com.sparta.week3pjt.model.Comment;
import com.sparta.week3pjt.model.CommentLike;
import com.sparta.week3pjt.model.Recomment;
import com.sparta.week3pjt.model.RecommentLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {

    Optional<CommentLike> findByCommentAndUsername(Comment comment, String username);
    List<CommentLike> findAllByUsername(String username);
}
