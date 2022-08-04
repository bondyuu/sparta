package com.sparta.week32.repository;

import com.sparta.week32.model.Posting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostingRepository extends JpaRepository<Posting, Long> {

    List<Posting> findAllByOrderByCreatedAtDesc();

    void deleteByPostingId(Long postingId);

}

