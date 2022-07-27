//제목 글쓴이 비밀번호 내용

package com.sparta.week3.service;

import com.sparta.week3.domian.PasswordRequestDto;
import com.sparta.week3.domian.Posting;
import com.sparta.week3.domian.PostingRepository;
import com.sparta.week3.domian.PostingRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service // 스프링에게 이 클래스는 서비스임을 명시
@RequiredArgsConstructor
public class PostingService {
    private final PostingRepository postingRepository;

    @Transactional // SQL 쿼리가 일어나야 함을 스프링에게 알려줌
    public Posting update(Long id, PostingRequestDto requestDto) {
        Posting posting1 = postingRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );
        posting1.update(requestDto);
        return posting1;
    }

    public boolean check(long id, PasswordRequestDto passwordDto) {

        Posting posting1 = postingRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("해당 아이디가 존재하지 않습니다.")
        );

        Posting posting2 = new Posting(passwordDto);

        boolean result;
        if (posting1.getPassword().equals(posting2.getPassword())) {
            result = true;
        } else {
            result = false;
        }

        return result;

    }
}