package com.study.springboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@TobySpringBootTest
public class HelloRepositoryTest {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    HelloRepository helloRepository;

    @Test
    void findHelloFailed() {
        Assertions.assertThat(helloRepository.findHello("wontaejo")).isNull();
    }

    @Test
    void increaseCount() {
        Assertions.assertThat(helloRepository.countOf("wontaejo")).isEqualTo(0);
        helloRepository.increaseCount("wontaejo");
        Assertions.assertThat(helloRepository.countOf("wontaejo")).isEqualTo(1);
        helloRepository.increaseCount("wontaejo");
        Assertions.assertThat(helloRepository.countOf("wontaejo")).isEqualTo(2);
    }
}
