package com.sparta.week3.domian;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostingRepository extends JpaRepository<Posting, Long> {

    List<Posting> findAllByOrderByCreatedAtDesc();

}

