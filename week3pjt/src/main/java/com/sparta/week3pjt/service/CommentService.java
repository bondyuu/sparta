package com.sparta.week3pjt.service;


import com.sparta.week3pjt.dto.CommentRequestDto;
import com.sparta.week3pjt.model.Comment;
import com.sparta.week3pjt.model.Posting;
import com.sparta.week3pjt.model.Recomment;
import com.sparta.week3pjt.repository.CommentRepository;
import com.sparta.week3pjt.repository.PostingRepository;
import com.sparta.week3pjt.repository.RecommentRepository;
import com.sparta.week3pjt.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final RecommentRepository recommentRepository;

    private final PostingRepository postingRepository;

    public List<Comment> getComments(Long postingId) {

        Posting posting = postingRepository.findBypostingId(postingId);

        List<Comment> comments = commentRepository.findAllByPosting(posting);

        // 해당 댓글에 작성된 대댓글을 각 comment의 recomments 항목에 넣어주기
        for (Comment comment : comments) {
            List<Recomment> recommentList = recommentRepository.findAllBycomment(comment);
            comment.setRecomments(recommentList);
        }

        return comments;
    }

    public Comment createComment(Long postingId, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        Posting posting = postingRepository.findById(postingId).orElseThrow(
                () -> new NullPointerException("게시글이 없습니다.")
        );
        posting.setCntComment(posting.getCntComment() + 1L);
        Comment comment = new Comment(posting, requestDto, userDetails);

        commentRepository.save(comment);
        return comment;
    }

    @Transactional
    public Comment updateComment(Long commentId, CommentRequestDto requestDto) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("댓글이 없습니다.")
        );

        comment.setComment(requestDto.getComment());
        return comment;
    }



    public void deleteComment(Long postingId, Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("댓글이 없습니다.")
        );
        Posting posting = postingRepository.findById(postingId).orElseThrow(
                () -> new NullPointerException("게시글이 없습니다.")
        );
        posting.setCntComment(posting.getCntComment() - 1L);

        commentRepository.delete(comment);

    }
}
