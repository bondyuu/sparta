package com.sparta.week3pjt.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class PostingLike {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long postinglikeId;

    @ManyToOne
    @JoinColumn(name = "postingId", nullable = false)
    private Posting posting;

    private String username;




    public PostingLike(Posting posting, String username) {
        this.posting = posting;
        this.username = username;
    }

}