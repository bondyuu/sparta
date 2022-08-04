package com.sparta.week32.controller;


import com.sparta.week32.dto.SignupRequestDto;
import com.sparta.week32.model.User;
import com.sparta.week32.repository.UserRepository;
import com.sparta.week32.security.FormLoginSuccessHandler;
import com.sparta.week32.security.filter.FormLoginFilter;
import com.sparta.week32.security.provider.FormLoginAuthProvider;
import com.sparta.week32.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final FormLoginFilter formLoginFilter;
    private final FormLoginAuthProvider formLoginAuthProvider;
    private final FormLoginSuccessHandler formLoginSuccessHandler;
    private final UserRepository userRepository;


    //회원가입 요청
    @PostMapping("/api/member/signup")
    public User registerUser(@RequestBody SignupRequestDto requestDto) {


        return userService.registerUser(requestDto);
    }

    @PostMapping("/user/login")
    public void userLogin(@RequestBody HttpServletRequest request) {

    }

//    //로그인 후 성공하면 쿠키 설정하기
//    @PostMapping("/user/login")
//    public User userLogin(@RequestBody HttpServletRequest request, HttpServletResponse response) {
//
//        //formloginfilter통과
//        Authentication authentication = formLoginFilter.attemptAuthentication(request, response);
//
//        //formloginprovider통과
//        Authentication authentication1 = formLoginAuthProvider.authenticate(authentication);
//
//        //formloginsuccesshandler통과
//        formLoginSuccessHandler.onAuthenticationSuccess(request, response, authentication1);
//
//
//        //username을 통해 DB에서 user를 찾아 리턴
//        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
//
//        String username = token.getName();
//
//        return userRepository.findByUsername(username).orElseThrow(
//                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다."));
//
//    }
}
