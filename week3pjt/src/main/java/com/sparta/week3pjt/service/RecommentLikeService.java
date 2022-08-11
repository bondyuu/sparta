package com.sparta.week3pjt.service;

import com.sparta.week3pjt.model.Recomment;
import com.sparta.week3pjt.model.RecommentLike;
import com.sparta.week3pjt.model.User;
import com.sparta.week3pjt.repository.RecommentLikeRepository;
import com.sparta.week3pjt.repository.RecommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecommentLikeService {

    private final RecommentLikeRepository recommentLikeRepository;
    private final RecommentRepository recommentRepository;

    public Recomment like(Recomment recomment, User user) {

        String username = user.getUsername();

        Optional<RecommentLike> recommentLike =
                recommentLikeRepository.findByRecommentAndUsername(recomment, username);

        if (recommentLike.isEmpty()) {
            recomment.setCntLike(recomment.getCntLike() + 1L);
            RecommentLike newrecommentLike = new RecommentLike(recomment, username);
            recommentLikeRepository.save(newrecommentLike);

        } else {
            recomment.setCntLike(recomment.getCntLike() - 1L);
            recommentLikeRepository.deleteById(recommentLike.get().getRecommentlikeId());

        }
        return recomment;
    }

}
