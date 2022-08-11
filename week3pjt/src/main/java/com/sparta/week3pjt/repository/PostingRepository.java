package com.sparta.week3pjt.repository;

import com.sparta.week3pjt.model.Posting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostingRepository extends JpaRepository<Posting, Long> {

    List<Posting> findAllByOrderByCreatedAtDesc();

    List<Posting> findAllByCntComment(Long cntComment);
    Posting findBypostingId(Long postingId);
    List<Posting> findAllByUsername(String username);
    void deleteByPostingId(Long postingId);

}

