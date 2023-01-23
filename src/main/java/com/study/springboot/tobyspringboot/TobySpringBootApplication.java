package com.study.springboot.tobyspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration // 구성 정보를 가지고 있는 class 다.
@ComponentScan // 컴포넌트가 붙은 클레스를 찾아서 Bean 으로 등록해 달라 Scan 패키지 부터 하위 패키지까지 찾아서 빈으로 등록해준다.
public class TobySpringBootApplication {
    @Bean
    public ServletWebServerFactory servletWebServerFactory() {
        return new TomcatServletWebServerFactory();
    }

    @Bean
    public DispatcherServlet dispatcherServlet() {
        return new DispatcherServlet();
    }

    public static void main(String[] args) {
        SpringApplication.run(TobySpringBootApplication.class, args);
    }

}
