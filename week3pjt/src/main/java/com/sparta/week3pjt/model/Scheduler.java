package com.sparta.week3pjt.model;


import com.sparta.week3pjt.repository.PostingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class Scheduler {


    private final PostingRepository postingRepository;

    // 초, 분, 시, 일, 월, 주 순서
    @Scheduled(cron = "0 0 1 * * *")
    public void deleteNoComment() {

        List<Posting> postings = postingRepository.findAllByCntComment(0L);

        for (Posting posting : postings) {
            postingRepository.deleteById(posting.getPostingId());
            log.info("게시물 <" + posting.getTitle() + ">이 삭제되었습니다.");
        }


    }
}
