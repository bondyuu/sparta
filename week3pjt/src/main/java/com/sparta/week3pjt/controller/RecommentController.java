package com.sparta.week3pjt.controller;

import com.sparta.week3pjt.dto.CommentRequestDto;
import com.sparta.week3pjt.model.Comment;
import com.sparta.week3pjt.model.Recomment;
import com.sparta.week3pjt.repository.CommentRepository;
import com.sparta.week3pjt.repository.RecommentRepository;
import com.sparta.week3pjt.security.UserDetailsImpl;
import com.sparta.week3pjt.service.RecommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RecommentController {

    private final RecommentService recommentService;
    private final RecommentRepository recommentRepository;
    private final CommentRepository commentRepository;

    @GetMapping("/api/recomments/{commentId}")
    public List<Recomment> getRecomments(@PathVariable Long commentId) {

        Comment comment = commentRepository.findByCommentId(commentId);

        return recommentRepository.findAllBycomment(comment);
    }

    @PostMapping("/api/recomment/{commentId}")
    public Recomment createRecomment(@PathVariable Long commentId, @RequestBody CommentRequestDto requestDto,
                                 @AuthenticationPrincipal UserDetailsImpl userDetails) {

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("댓글이 없습니다.")
        );

        return recommentService.createRecomment(comment, requestDto, userDetails);
    }

    @PutMapping("/api/recomment/{commentId}/{recommentId}")
    public Recomment updateRecomment(@PathVariable Long recommentId, @RequestBody CommentRequestDto requestDto,
                                 @AuthenticationPrincipal UserDetailsImpl userDetails) {

        //대댓글 작성자와 로그인유저 일치 확인
        Recomment recomment = recommentRepository.findByRecommentId(recommentId);
        if(!userDetails.getUser().getUsername().equals(recomment.getUsername())) {
            throw new IllegalArgumentException("작성자만 수정할 수 있습니다.");
        }

        return recommentService.updateRecomment(recommentId, requestDto);
    }

    @DeleteMapping("/api/recomment/{commentId}/{recommentId}")
    public String deleteComment(@PathVariable Long recommentId,
                                @AuthenticationPrincipal UserDetailsImpl userDetails) {

        //대댓글 작성자와 로그인유저 일치 확인
        Recomment recomment = recommentRepository.findByRecommentId(recommentId);
        if(!userDetails.getUser().getUsername().equals(recomment.getUsername())) {
            throw new IllegalArgumentException("작성자만 수정할 수 있습니다.");
        }

        recommentService.deleteRecomment(recommentId);
        return "Deleted Successfully";
    }
}
