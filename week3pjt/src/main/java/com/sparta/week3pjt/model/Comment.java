package com.sparta.week3pjt.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.week3pjt.dto.CommentRequestDto;
import com.sparta.week3pjt.security.UserDetailsImpl;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter @Setter
@Table(name = "comment")
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long commentId;

    @ManyToOne
    @JoinColumn(name = "postingId", nullable = false)
    @JsonIgnore
    private Posting posting;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    private Long cntLike;

    // comment recomment 연관 관계 설정
    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Recomment> recomments = new ArrayList<>();

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<CommentLike> commentLikes = new ArrayList<>();


    public Comment( Posting posting, CommentRequestDto requestDto, UserDetailsImpl userDetails) {
        this.posting = posting;
        this.comment = requestDto.getComment();
        this.username = userDetails.getUsername();
        this.cntLike = 0L;

    }

}
