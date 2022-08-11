package com.sparta.week3pjt.controller;


import com.sparta.week3pjt.dto.LoginRequestDto;
import com.sparta.week3pjt.dto.SignupRequestDto;
import com.sparta.week3pjt.model.User;
import com.sparta.week3pjt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원 가입 요청 처리
    @PostMapping("/api/member/signup")
    public User registerUser(@RequestBody SignupRequestDto requestDto) {

        return userService.registerUser(requestDto);
    }

    //로그인
    @PostMapping("user/login")
    public String login(@RequestBody LoginRequestDto requestDto) {

        return "Login successfully";
    }
}
