package com.korber.myfilter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditingService")
public class MyFilterApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyFilterApplication.class, args);
    }
}
