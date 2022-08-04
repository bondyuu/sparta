//제목 글쓴이 비밀번호 내용

package com.sparta.week32.service;


import com.sparta.week32.model.Posting;
import com.sparta.week32.repository.CommentRepository;
import com.sparta.week32.repository.PostingRepository;
import com.sparta.week32.dto.PostingRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service // 스프링에게 이 클래스는 서비스임을 명시
@RequiredArgsConstructor
public class PostingService {
    private final PostingRepository postingRepository;
    private final CommentRepository commentRepository;

    @Transactional // SQL 쿼리가 일어나야 함을 스프링에게 알려줌
    public Posting update(Long postingID, PostingRequestDto requestDto) {
        Posting posting1 = postingRepository.findById(postingID).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );

        posting1.update(requestDto);
        return posting1;
    }
    @Transactional
    public String deletePosting(Long postingId) {

        //postingId에 맞는 게시글 삭제
        postingRepository.deleteByPostingId(postingId);

        //postingId에 작성된 comment 삭제
        commentRepository.deleteAllByPostingId(postingId);
        return "Deleted Successfully";
    }

}