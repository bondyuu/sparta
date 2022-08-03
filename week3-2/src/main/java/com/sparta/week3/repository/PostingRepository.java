package com.sparta.week3.repository;

import com.sparta.week3.model.Posting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostingRepository extends JpaRepository<Posting, Long> {

    List<Posting> findAllByOrderByCreatedAtDesc();

    void deleteByPostingId(Long postingId);

}

