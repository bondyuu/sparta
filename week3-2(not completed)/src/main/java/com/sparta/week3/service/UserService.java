package com.sparta.week3.service;

import com.sparta.week3.dto.LoginRequestDto;
import com.sparta.week3.dto.SignupRequestDto;
import com.sparta.week3.model.User;
import com.sparta.week3.model.UserRoleEnum;
import com.sparta.week3.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    private static final String TOKEN = "AAABnv/xRVklrnYxKZ0aHgTBcXukeZygoC";


    public User registerUser(SignupRequestDto requestDto) {

        //id중복확인
        String username = requestDto.getUsername();
        Optional<User> found = userRepository.findByUsername(username);

        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 ID입니다.");
        }

        //password, repassword 일치 확인 후 암호화
        String password = null;
        if (requestDto.getPassword().equals(requestDto.getRepassword())) {
            password = passwordEncoder.encode(requestDto.getPassword());
        }

        UserRoleEnum role = UserRoleEnum.USER;

        //user 정보 db저장
        User user = new User(username, password, role);

        userRepository.save(user);
        return user;
    }


}
