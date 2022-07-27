package com.sparta.week3.domian;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor //기본생성자생성
@Entity //테이블
@ToString
public class Posting extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    @JsonIgnore
    private String password;

    @Column(nullable = false)
    private String contents;



    public Posting(String title, String writer, String password, String contents) {
        this.title = title;
        this.writer = writer;
        this.password = password;
        this.contents = contents;
    }

    public Posting(String title, String writer, String contents) {
        this.title = title;
        this.writer = writer;
        this.contents = contents;
    }

    public void update(PostingRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.writer = requestDto.getWriter();
        this.password = requestDto.getPassword();
        this.contents = requestDto.getContents();
    }

    public Posting(PostingRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.writer = requestDto.getWriter();
        this.password = requestDto.getPassword();
        this.contents = requestDto.getContents();
    }

    public Posting(PasswordRequestDto passwordDto) {
        this.title = "1";
        this.writer = "1";
        this.password = passwordDto.getPassword();
        this.contents = "1";
    }

}
