package com.dawden;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@ComponentScan(basePackages = "com.dawden")
@EnableKafka
public class SpringKafkaPocApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringKafkaPocApplication.class, args);
    }
}
