package com.study.springboot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import org.assertj.core.api.Assertions;

@JdbcTest
public class JdbcTemplateTest {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    void init() {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS hello(name varchar(50) primary key, count int)");
    }

    @Test
    void insertAndQuery() {
        jdbcTemplate.update("insert into hello values(?, ?)", "wontaejo", 3);
        jdbcTemplate.update("insert into hello values(?, ?)", "spring", 1);

        Long count = jdbcTemplate.queryForObject("select count(*) from hello  ", Long.class);
        Assertions.assertThat(count).isEqualTo(2);
    }
}
