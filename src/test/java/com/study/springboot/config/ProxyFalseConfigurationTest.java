package com.study.springboot.config;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ProxyFalseConfigurationTest {
    @Test
    // Spring 에서 Bean 으로 등록된 Bean1, Bean2 에서 생성되어 응답되는 common 은 서로 다르다
    void springConfiguration() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        ac.register(MyConfig.class);
        ac.refresh();

        Bean1 bean1 = ac.getBean(Bean1.class);
        Bean2 bean2 = ac.getBean(Bean2.class);
        Assertions.assertThat(bean1.common).isNotEqualTo(bean2.common);
    }

    @Configuration(proxyBeanMethods = false)
    static class MyConfig {
        @Bean
        Common common() {
            return new Common();
        }

        @Bean
        Bean1 bean1() {
            return new Bean1(common());
        }  // proxy 가 false 셋팅되어 있을때 common 을 이런식으로 호출되눈건 위험하다 IDEA 에서 알려준다

        @Bean
        Bean2 bean2() {
            return new Bean2(common());
        }
    }

    static class Bean1 {
        private final Common common;

        Bean1(Common common) {
            this.common = common;
        }
    }

    static class Bean2 {
        private final Common common;

        Bean2(Common common) {
            this.common = common;
        }
    }

    private static class Common {
    }
}
