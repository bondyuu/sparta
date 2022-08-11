package com.sparta.week3pjt.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.week3pjt.dto.PostingRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name = "posting")
public class Posting extends Timestamped{


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long postingId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private Long cntLike;

    @Column(nullable = false)
    private Long cntComment;

    @Column
    private String imageUrl;

    @OneToMany(mappedBy = "posting", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "posting", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<PostingLike> postingLikes = new ArrayList<>();


    public Posting(PostingRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.imageUrl = requestDto.getImageUrl();
        this.cntComment = 0L;
        this.cntLike = 0L;
    }

    public void update(PostingRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }
}
