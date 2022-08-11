package com.sparta.week3pjt.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostingRequestDto {
    private final String title;
    private final String contents;
    private final String imageUrl;

}