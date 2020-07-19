package org.example;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class StartApplication {
    public static void main(String[] args) {
        SpringApplication sa  = new SpringApplication(StartApplication.class);
        sa.run(args);
    }
}
