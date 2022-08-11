package com.sparta.week3pjt.service;

import com.sparta.week3pjt.model.Posting;
import com.sparta.week3pjt.model.PostingLike;
import com.sparta.week3pjt.model.User;
import com.sparta.week3pjt.repository.PostingLikeRepository;
import com.sparta.week3pjt.repository.PostingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostingLikeService {

    private final PostingLikeRepository postingLikeRepository;
    private final PostingRepository postingRepository;

    public Posting like(Posting posting, User user) {

        String username = user.getUsername();

        Optional<PostingLike> postingLike =
                postingLikeRepository.findByPostingAndUsername(posting, username);

        if (postingLike.isEmpty()) {
            posting.setCntLike(posting.getCntLike() + 1L);
            PostingLike newpostingLike = new PostingLike(posting, username);
            postingLikeRepository.save(newpostingLike);

        } else {
            posting.setCntLike(posting.getCntLike() - 1L);
            postingLikeRepository.deleteById(postingLike.get().getPostinglikeId());

        }
        return posting;
    }

}