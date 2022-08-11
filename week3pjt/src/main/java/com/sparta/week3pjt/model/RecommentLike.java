package com.sparta.week3pjt.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter @Setter
public class RecommentLike {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long recommentlikeId;

    @ManyToOne
    @JoinColumn(name = "recommentId", nullable = false)
    private Recomment recomment;

    private String username;




    public RecommentLike(Recomment recomment, String username) {
        this.recomment = recomment;
        this.username = username;
    }

}
