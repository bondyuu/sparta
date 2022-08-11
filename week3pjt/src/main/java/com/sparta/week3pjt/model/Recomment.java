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
@Table(name = "recomment")
public class Recomment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long recommentId;

    @ManyToOne
    @JoinColumn(name = "commentId", nullable = false)
    @JsonIgnore
    private Comment comment;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String recomment;

    @Column(nullable = false)
    private Long cntLike;

    @OneToMany(mappedBy = "recomment", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<RecommentLike> recommentLikes = new ArrayList<>();



    public Recomment(Comment comment, CommentRequestDto requestDto, UserDetailsImpl userDetails) {
        this.username = userDetails.getUsername();
        this.recomment = requestDto.getComment();
        this.comment = comment;
        this.cntLike = 0L;

    }

}
