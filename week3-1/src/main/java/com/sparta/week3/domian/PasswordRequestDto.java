package com.sparta.week3.domian;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class PasswordRequestDto {
    private String title;
    private String writer;
    private String password;
    private String contents;

}