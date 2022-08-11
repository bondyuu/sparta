package com.sparta.week3pjt.controller;


import com.sparta.week3pjt.dto.PostingRequestDto;
import com.sparta.week3pjt.model.Posting;
import com.sparta.week3pjt.repository.PostingRepository;
import com.sparta.week3pjt.security.UserDetailsImpl;
import com.sparta.week3pjt.service.PostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostingController {

    private final PostingRepository postingRepository;
    private final PostingService postingService;

    //게시글 전체 조회
    @GetMapping("/api/postings")
    public List<Posting> getPostings() {

        //댓글 갯수(cntComment; 초기값 : 0) 나타내기

        return postingRepository.findAllByOrderByCreatedAtDesc();
    }

    //게시글 조회
    @GetMapping("/api/postings/{postingId}")
    public Posting getPostings(@PathVariable Long postingId) {

        //댓글 갯수(cntComment; 초기값 : 0) 나타내기

        return postingService.postingDetail(postingId);
    }

    //게시글 작성
    @PostMapping("/api/postings")
    public Posting createPosting(@RequestBody PostingRequestDto requestDto,
                                 @AuthenticationPrincipal UserDetailsImpl userDetails) {

        Posting posting = new Posting(requestDto);
        posting.setUsername(userDetails.getUser().getUsername());

        return postingRepository.save(posting);
    }


    //게시글 수정
    @PutMapping("/api/postings/{postingId}")
    public Posting updatePosting (@PathVariable Long postingId, @RequestBody PostingRequestDto requestDto,
                                  @AuthenticationPrincipal UserDetailsImpl userDetails) {

        //posting작성자와 login된 user 일치여부확인
        Posting posting = postingRepository.findById(postingId).orElseThrow(
                () -> new IllegalArgumentException("게시글이 없습니다.."));

        if(!userDetails.getUsername().equals(posting.getUsername())) {
            throw new IllegalArgumentException("작성자만 수정할 수 있습니다.");
        }


        return postingService.update(postingId, requestDto);
    }

    //게시글 삭제
    @DeleteMapping("/api/postings/{postingId}")
    public String deletePosting (@PathVariable Long postingId,
                                 @AuthenticationPrincipal UserDetailsImpl userDetails) {

        //posting작성자와 login된 user 일치여부확인
        Posting posting = postingRepository.findById(postingId).orElseThrow(
                () -> new IllegalArgumentException("게시글이 없습니다.."));

        if(!userDetails.getUsername().equals(posting.getUsername())) {
            throw new IllegalArgumentException("작성자만 삭제할 수 있습니다.");
        }


        return postingService.deletePosting(postingId);
    }

}
