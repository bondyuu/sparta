//API 작성
//timestamped, interface, postingService 작성하기
//lombok(@~) 의미 파악하기
package com.sparta.week3.controller;


import com.sparta.week3.model.Posting;
import com.sparta.week3.repository.PostingRepository;
import com.sparta.week3.dto.PostingRequestDto;
import com.sparta.week3.security.UserDetailsImpl;
import com.sparta.week3.service.PostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostingController {

    private final PostingRepository postingRepository;
    private final PostingService postingService;

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

//        requestDto.addHeader("Authorization", "BEARER eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJFWFBJUkVEX0RBVEUiOjE2NTk3Nzc0NjAsImlzcyI6InNwYXJ0YSIsIlVTRVJfTkFNRSI6ImtvbyJ9.F1btAhombkbFSSROgzp9ulhgOsSF3xb9PdFWdVY9ZGY");

        Posting posting = new Posting(requestDto);
        posting.setWriter(userDetails.getUser().getUsername());
        posting.setPassword(userDetails.getUser().getPassword());
        return postingRepository.save(posting);
    }

    @PutMapping("/api/auth/postings/{postingId}")
    public Posting updatePosting (@PathVariable Long postingId, @RequestBody PostingRequestDto requestDto) {
        return postingService.update(postingId, requestDto);
    }

    @DeleteMapping("/api/auth/postings/{postingId}")
    public String deletePosting (@PathVariable Long postingId) {
        postingRepository.deleteById(postingId);
        return "Deleted Successfully!";
    }
}
