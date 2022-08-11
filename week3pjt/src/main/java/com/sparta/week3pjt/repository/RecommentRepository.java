package com.sparta.week3pjt.repository;

import com.sparta.week3pjt.model.Comment;
import com.sparta.week3pjt.model.Recomment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecommentRepository extends JpaRepository<Recomment, Long> {

    List<Recomment> findAllBycomment(Comment comment);
    Recomment findByRecommentId(Long recommentId);
    List<Recomment> findAllByUsername(String username);

}