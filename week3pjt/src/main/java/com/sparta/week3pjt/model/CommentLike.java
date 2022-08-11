package com.sparta.week3pjt.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class CommentLike {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long CommentlikeId;

    @ManyToOne
    @JoinColumn(name = "commentId", nullable = false)
    private Comment comment;

    private String username;




    public CommentLike(Comment comment, String username) {
        this.comment = comment;
        this.username = username;
    }

}