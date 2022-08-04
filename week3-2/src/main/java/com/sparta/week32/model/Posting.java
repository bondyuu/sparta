package com.sparta.week32.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.week32.dto.PostingRequestDto;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor //기본생성자생성
@Entity //테이블
public class Posting extends  Timestamped {





    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long postingId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    @JsonIgnore
    private String password;

    @Column(nullable = false)
    private String contents;



    public void update(PostingRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.writer = requestDto.getWriter();
        this.password = requestDto.getPassword();
        this.contents = requestDto.getContents();
    }

    public Posting(PostingRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.writer = null;
        this.password = null;
        this.contents = requestDto.getContents();
    }


}
