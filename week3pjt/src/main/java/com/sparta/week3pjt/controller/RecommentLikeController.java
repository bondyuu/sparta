package com.sparta.week3pjt.controller;


import com.sparta.week3pjt.model.Recomment;
import com.sparta.week3pjt.model.User;
import com.sparta.week3pjt.repository.RecommentRepository;
import com.sparta.week3pjt.security.UserDetailsImpl;
import com.sparta.week3pjt.service.RecommentLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RecommentLikeController {

    private final RecommentRepository recommentRepository;
    private final RecommentLikeService recommentLikeService;

    @PostMapping("/api/recomment/like/{recommentId}")
    public Recomment recommentLike(@PathVariable Long recommentId,
                                @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Recomment recomment = recommentRepository.findById(recommentId).orElseThrow(
                () -> new IllegalArgumentException("대댓글이 없습니다.")
        );

        User user = userDetails.getUser();

        return recommentLikeService.like(recomment, user);
    }

}
