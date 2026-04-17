package com.example.ch3scheduleappdevelop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Ch3ScheduleAppDevelopApplication {

    public static void main(String[] args) {
        SpringApplication.run(Ch3ScheduleAppDevelopApplication.class, args);
    }

}
