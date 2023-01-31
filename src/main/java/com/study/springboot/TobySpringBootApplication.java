package com.study.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class TobySpringBootApplication {
    private final JdbcTemplate jdbcTemplate;

    public TobySpringBootApplication(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    void init() {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS hello(name varchar(50) primary key, count int)");
    }

    public static void main(String[] args) {
        SpringApplication.run(TobySpringBootApplication.class, args);
    }

}
