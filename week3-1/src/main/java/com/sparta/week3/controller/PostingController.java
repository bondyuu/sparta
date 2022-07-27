//API 작성
//timestamped, interface, postingService 작성하기
//lombok(@~) 의미 파악하기
package com.sparta.week3.controller;

import com.sparta.week3.domian.PasswordRequestDto;
import com.sparta.week3.domian.Posting;
import com.sparta.week3.domian.PostingRepository;
import com.sparta.week3.domian.PostingRequestDto;
import com.sparta.week3.service.PostingService;
import com.sparta.week3.service.PostingService;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("/api/postings/{id}")
    public Posting getPostings(@PathVariable Long id) {
        return postingRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );
    }

    @PostMapping("/api/postings")
    public Posting createPosting(@RequestBody PostingRequestDto requestDto) {
        Posting posting = new Posting(requestDto);
        return postingRepository.save(posting);
    }

    @PostMapping("/api/postings/{id}")//requestDto에 final을 빼니까 된다
    public boolean checkPosting(@PathVariable long id,@RequestBody PasswordRequestDto passwordDto) {

        return postingService.check(id, passwordDto);
    }

    @PutMapping("/api/postings/{id}")
    public Posting updatePosting (@PathVariable Long id, @RequestBody PostingRequestDto requestDto) {
        return postingService.update(id, requestDto);
    }

    @DeleteMapping("/api/postings/{id}")
    public boolean deletePosting (@PathVariable Long id) {
        postingRepository.deleteById(id);
        return true;
    }
}
