//API 작성
//timestamped, interface, postingService 작성하기
//lombok(@~) 의미 파악하기
package com.sparta.week32.controller;


import com.sparta.week32.model.Posting;
import com.sparta.week32.repository.CommentRepository;
import com.sparta.week32.repository.PostingRepository;
import com.sparta.week32.dto.PostingRequestDto;
import com.sparta.week32.security.UserDetailsImpl;
import com.sparta.week32.service.PostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostingController {

    private final PostingRepository postingRepository;
    private final PostingService postingService;
    private final CommentRepository commentRepository;

    @GetMapping("/api/postings")
    public List<Posting> getPostings() {

        return postingRepository.findAllByOrderByCreatedAtDesc();
    }

    @GetMapping("/api/postings/{postingId}")
    public Posting getPostings(@PathVariable Long postingId) {
        return postingRepository.findById(postingId).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );
    }

    @PostMapping("/api/auth/postings")
    public Posting createPosting(@RequestBody PostingRequestDto requestDto,
                                 @AuthenticationPrincipal UserDetailsImpl userDetails) {

        Posting posting = new Posting(requestDto);
        posting.setWriter(userDetails.getUser().getUsername());
        posting.setPassword(userDetails.getUser().getPassword());
        return postingRepository.save(posting);
    }

    @PutMapping("/api/auth/postings/{postingId}")
    public Posting updatePosting (@PathVariable Long postingId, @RequestBody PostingRequestDto requestDto,
                                  @AuthenticationPrincipal UserDetailsImpl userDetails) {

        //posting작성자와 login된 user 일치여부확인
        Posting posting = postingRepository.findById(postingId).orElseThrow(
                () -> new IllegalArgumentException("게시글이 없습니다.."));

        if(!userDetails.getUser().getUsername().equals(posting.getWriter())) {
            throw new IllegalArgumentException("작성자만 수정할 수 있습니다.");
        }


        return postingService.update(postingId, requestDto);
    }

    @DeleteMapping("/api/auth/postings/{postingId}")
    public String deletePosting (@PathVariable Long postingId,
                                 @AuthenticationPrincipal UserDetailsImpl userDetails) {

        //posting작성자와 login된 user 일치여부확인
        Posting posting = postingRepository.findById(postingId).orElseThrow(
                () -> new IllegalArgumentException("게시글이 없습니다."));

        if(!userDetails.getUser().getUsername().equals(posting.getWriter())) {
            throw new IllegalArgumentException("작성자만 삭제할 수 있습니다.");
        }

        return postingService.deletePosting(postingId);
    }
}
