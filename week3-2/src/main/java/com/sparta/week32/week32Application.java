package com.sparta.week32;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class week32Application {

    public static void main(String[] args) {
        SpringApplication.run(week32Application.class, args);
    }

}


