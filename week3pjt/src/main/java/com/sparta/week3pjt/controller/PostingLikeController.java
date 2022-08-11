package com.sparta.week3pjt.controller;

import com.sparta.week3pjt.model.Posting;
import com.sparta.week3pjt.model.User;
import com.sparta.week3pjt.repository.PostingRepository;
import com.sparta.week3pjt.repository.RecommentRepository;
import com.sparta.week3pjt.security.UserDetailsImpl;
import com.sparta.week3pjt.service.PostingLikeService;
import com.sparta.week3pjt.service.RecommentLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostingLikeController {

    private final PostingRepository postingRepository;
    private final PostingLikeService postingLikeService;

    @PostMapping("/api/posting/like/{postingId}")
    public Posting postingLike(@PathVariable Long postingId,
                                   @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Posting posting = postingRepository.findById(postingId).orElseThrow(
                () -> new IllegalArgumentException("대댓글이 없습니다.")
        );

        User user = userDetails.getUser();

        return postingLikeService.like(posting, user);
    }

}