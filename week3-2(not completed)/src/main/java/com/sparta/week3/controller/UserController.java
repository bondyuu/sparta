package com.sparta.week3.controller;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.week3.dto.SignupRequestDto;
import com.sparta.week3.model.Posting;
import com.sparta.week3.model.User;
import com.sparta.week3.model.UserRoleEnum;
import com.sparta.week3.repository.UserRepository;
import com.sparta.week3.security.FormLoginSuccessHandler;
import com.sparta.week3.security.filter.FormLoginFilter;
import com.sparta.week3.security.provider.FormLoginAuthProvider;
import com.sparta.week3.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.function.Supplier;

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
