package com.sparta.week3;

import com.sparta.week3.domian.Posting;
import com.sparta.week3.domian.PostingRepository;
import com.sparta.week3.domian.PostingRequestDto;
import com.sparta.week3.service.PostingService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.converter.json.GsonBuilderUtils;
import java.sql.SQLOutput;
import java.util.List;

@SpringBootApplication
@EnableJpaAuditing
public class Week3Application {

    public static void main(String[] args) {
        SpringApplication.run(Week3Application.class, args);
    }

}



//    // Week02Application.java 의 main 함수 아래에 붙여주세요.
//    @Bean
//    public CommandLineRunner demo(PostingRepository postingRepository, PostingService postingService) {
//        return (args) -> {
//            postingRepository.save(new Posting("프론트엔드의 꽃, 리액트", "임민영"));
//
//            System.out.println("데이터 인쇄");
//            List<Posting> postingList = postingRepository.findAll();
//            for (int i=0; i<postingList.size(); i++) {
//                Posting posting = postingList.get(i);
//                System.out.println(posting.getId());
//                System.out.println(posting.getTitle());
//                System.out.println(posting.getWriter());
//            }
//
//            Posting new_posting = new Posting("웹개발의 봄, Spring", "임민영");
//            PostingRequestDto requestDto = new PostingRequestDto("웹개발의 봄, Spring", "임민영");
//            postingService.update(1L, requestDto);
//            postingList = postingRepository.findAll();
//            for (int i=0; i<postingList.size(); i++) {
//                Posting posting = postingList.get(i);
//                System.out.println(posting.getId());
//                System.out.println(posting.getTitle());
//                System.out.println(posting.getWriter());
//            }
//        };
//    }