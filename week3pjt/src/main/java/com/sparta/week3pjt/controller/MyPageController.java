package com.sparta.week3pjt.controller;


import com.sparta.week3pjt.model.*;
import com.sparta.week3pjt.repository.*;
import com.sparta.week3pjt.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MyPageController {

    private final UserRepository userRepository;
    private final PostingRepository postingRepository;
    private final CommentRepository commentRepository;
    private final RecommentRepository recommentRepository;
    private final PostingLikeRepository postingLikeRepository;
    private final CommentLikeRepository commentLikeRepository;
    private final RecommentLikeRepository recommentLikeRepository;

    @GetMapping("/api/mypage/{userId}")
    public String getMyPage(@PathVariable Long userId,
                            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("회원정보가 없습니다.")
        );

        String username = user.getUsername();

        List<Posting> postings = postingRepository.findAllByUsername(username);
        List<Comment> comments = commentRepository.findAllByUsername(username);
        List<Recomment> recomments = recommentRepository.findAllByUsername(username);
        List<PostingLike> postingLikes = postingLikeRepository.findAllByUsername(username);
        List<CommentLike> commentLikes = commentLikeRepository.findAllByUsername(username);
        List<RecommentLike> recommentLikes = recommentLikeRepository.findAllByUsername(username);

        System.out.println(postings);
        System.out.println(comments);
        System.out.println(recomments);
        System.out.println(postingLikes);
        System.out.println(commentLikes);
        System.out.println(recommentLikes);


        return "mypage";
    }
}
