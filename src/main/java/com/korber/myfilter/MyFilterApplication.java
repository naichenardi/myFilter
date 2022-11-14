package com.korber.myfilter;

import com.korber.myfilter.listeners.AuditingService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditingService")
public class MyFilterApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyFilterApplication.class, args);
    }

    @Bean
    AuditorAware<String> auditorProvider() {
        return new AuditingService();
    }
}
