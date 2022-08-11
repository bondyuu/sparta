package com.sparta.week3pjt.repository;

import com.sparta.week3pjt.model.Posting;
import com.sparta.week3pjt.model.PostingLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostingLikeRepository extends JpaRepository<PostingLike, Long> {

    Optional<PostingLike> findByPostingAndUsername(Posting posting, String username);
    List<PostingLike> findAllByUsername(String username);
}
