package com.study.springboot.tobyspringboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration // 구성 정보를 가지고 있는 class 다.
@ComponentScan // 컴포넌트가 붙은 클레스를 찾아서 Bean 으로 등록해 달라 Scan 패키지 부터 하위 패키지까지 찾아서 빈으로 등록해준다.
public class TobySpringBootApplication {

    public static void main(String[] args) {
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
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

        applicationContext.register(TobySpringBootApplication.class);
        applicationContext.refresh(); // 구성 정보를 이용해 컨테이너를 초기화
    }
}
