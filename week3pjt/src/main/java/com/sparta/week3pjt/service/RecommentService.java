package com.sparta.week3pjt.service;


import com.sparta.week3pjt.dto.CommentRequestDto;
import com.sparta.week3pjt.model.Comment;
import com.sparta.week3pjt.model.Recomment;
import com.sparta.week3pjt.repository.RecommentRepository;
import com.sparta.week3pjt.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecommentService {

    private final RecommentRepository recommentRepository;


    public Recomment createRecomment(Comment comment, CommentRequestDto requestDto, UserDetailsImpl userDetails) {
        Recomment recomment = new Recomment(comment, requestDto, userDetails);

        recommentRepository.save(recomment);
        return recomment;
    }
    public Recomment updateRecomment(Long recommentId, CommentRequestDto requestDto) {
        Recomment recomment = recommentRepository.findById(recommentId).orElseThrow(
                () -> new IllegalArgumentException("대댓글이 없습니다.")
        );


        recomment.setRecomment(requestDto.getComment());

        return recomment;
    }
    public void deleteRecomment(Long recommentId) {
        Recomment recomment = recommentRepository.findById(recommentId).orElseThrow(
                () -> new IllegalArgumentException("대댓글이 없습니다.")
        );

        recommentRepository.delete(recomment);
    }




}
