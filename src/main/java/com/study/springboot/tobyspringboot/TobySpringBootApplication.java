package com.study.springboot.tobyspringboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class TobySpringBootApplication {

    public static void main(String[] args) {
        GenericWebApplicationContext applicationContext = new GenericWebApplicationContext() {
            @Override
            protected void onRefresh() {
                super.onRefresh();

                // 스프링 부트가 톰캣 서블릿 컨테이너를 내장하여 쉽게 시작할 수 있게 만들어 놓은 도우미 클레스
                // Factory 복잡한 설정 과정을 거쳐서 TomcatServletWebServerFactory 만들어준다
                ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
                WebServer webServer = serverFactory.getWebServer(servletContext -> {
                    servletContext.addServlet("dispatcherServlet", new DispatcherServlet(this))
                            .addMapping("/*"); // uri 로 요청이 오면
                });
                webServer.start(); // 톰캣 서블릿 컨테이너가 실행된다.

            }
        };

        applicationContext.registerBean(HelloController.class); // bean 등록 - 스프링 컨테이너가 빈이 어떻게 구성되어 지는가 어떤 클레스로 빈을 만들 것인가
        applicationContext.registerBean(SimpleHelloService.class); // 스프링에 구성정보를 정확하게 어떤 클레스를 가지고 만들지 명시해줘야함
        applicationContext.refresh(); // 구성 정보를 이용해 컨테이너를 초기화
    }
}
