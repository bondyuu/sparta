package com.sparta.week3.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.week3.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long commentId;

    @Column(nullable = false)
    private Long postingId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    @JsonIgnore
    private String password;


    public Comment(CommentRequestDto requestDto) {

        this.comment = requestDto.getComment();
    }
}
