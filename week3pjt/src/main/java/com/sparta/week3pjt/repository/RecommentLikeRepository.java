package com.sparta.week3pjt.repository;

import com.sparta.week3pjt.model.Recomment;
import com.sparta.week3pjt.model.RecommentLike;
import com.sparta.week3pjt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RecommentLikeRepository extends JpaRepository<RecommentLike, Long> {

    Optional<RecommentLike> findByRecommentAndUsername(Recomment recomment, String username);
    List<RecommentLike> findAllByUsername(String username);
}
