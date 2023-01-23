package com.study.springboot.tobyspringboot;

import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class MySpringApplication {

    public static void run(Class<?> applicationClass, String... args) {
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
            @Override
            protected void onRefresh() {
                super.onRefresh();

                // 스프링 부트가 톰캣 서블릿 컨테이너를 내장하여 쉽게 시작할 수 있게 만들어 놓은 도우미 클레스
                // Factory 복잡한 설정 과정을 거쳐서 TomcatServletWebServerFactory 만들어준다
                ServletWebServerFactory serverFactory = this.getBean(ServletWebServerFactory.class);
                DispatcherServlet dispatcherServlet = this.getBean(DispatcherServlet.class);
                // dispatcherServlet.setApplicationContext(this);

                WebServer webServer = serverFactory.getWebServer(servletContext -> {
                    servletContext.addServlet("dispatcherServlet", dispatcherServlet)
                            .addMapping("/*"); // uri 로 요청이 오면
                });
                webServer.start(); // 톰캣 서블릿 컨테이너가 실행된다.

            }
        };

        applicationContext.register(applicationClass);
        applicationContext.refresh(); // 구성 정보를 이용해 컨테이너를 초기화
    }
}

