package com.sparta.week32.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SignupRequestDto {
    private String username;
    private String password;
    private String repassword;

}
