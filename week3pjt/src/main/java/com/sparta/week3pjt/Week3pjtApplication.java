package com.sparta.week3pjt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableJpaAuditing
public class Week3pjtApplication {

    public static void main(String[] args) {
        SpringApplication.run(Week3pjtApplication.class, args);
    }

}
