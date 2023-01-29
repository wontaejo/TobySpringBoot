package com.study.springboot;

import com.study.springboot.config.MySpringBootApplication;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@MySpringBootApplication
public class TobySpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(TobySpringBootApplication.class, args);
    }

}
