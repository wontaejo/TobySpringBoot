package com.study.springboot.config;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ConfigurationTest {
    // 기존 스프링 가이드에서는 proxyBeanMethods = true ( default ) 로 왠만하면 놓고 사용해라였는데 요즘에는 Configuration 에서 Bean 이 다른 Bean 을 생성자로 참조하여 생성하지 않는다면
    // proxyBeanMethods = false 로 사용해도 된다.

    @Test
    // Java 에서 Bean1, Bean2 를 생성되어 응답되는 common 은 서로 다르다
    void javaConfiguration() {
        MyConfig myConfig = new MyConfig();
        Bean1 bean1 = myConfig.bean1();
        Bean2 bean2 = myConfig.bean2();

        Assertions.assertThat(bean1.common).isNotEqualTo(bean2.common);
    }

    @Test
    // Spring 에서 Bean 으로 등록된 Bean1, Bean2 에서 생성되어 응답되는 common 은 서로 같다
    void springConfiguration() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        ac.register(MyConfig.class);
        ac.refresh();

        Bean1 bean1 = ac.getBean(Bean1.class);
        Bean2 bean2 = ac.getBean(Bean2.class);
        Assertions.assertThat(bean1.common).isEqualTo(bean2.common);
    }

    @Test
    // Spring 내부적에서의 동작 방식을 보면, MyConfig Proxy 가 생성되고 앞단에서 Common 이 이미 생성되어 있을 경우 다시 생성하지 않고, 기존 객체를 넘겨준다. 그러므로 객체는 한번만 생성되고 둘다 동일한 객체가 응답된다.
    void proxyCommonMethod() {
        MyConfigProxy myConfigProxy = new MyConfigProxy();
        Bean1 bean1 = myConfigProxy.bean1();
        Bean2 bean2 = myConfigProxy.bean2();

        Assertions.assertThat(bean1.common).isEqualTo(bean2.common);
    }

    static class MyConfigProxy extends MyConfig {
        private Common common;

        @Override
        Common common() {
            if (this.common == null) {
                this.common = super.common();
            }

            return common;
        }
    }

    @Configuration
    static class MyConfig {
        @Bean
        Common common() {
            return new Common();
        }

        @Bean
        Bean1 bean1() {
            return new Bean1(common());
        }

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
