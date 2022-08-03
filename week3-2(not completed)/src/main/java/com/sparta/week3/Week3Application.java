package com.sparta.week3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Week3Application {

    public static void main(String[] args) {
        SpringApplication.run(Week3Application.class, args);
    }

}


