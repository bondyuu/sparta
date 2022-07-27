package com.sparta.week3.domian;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostingRequestDto {
    private final String title;
    private final String writer;
    private final String password;
    private final String contents;

}