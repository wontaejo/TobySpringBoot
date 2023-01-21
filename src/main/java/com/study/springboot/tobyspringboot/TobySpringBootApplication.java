package com.study.springboot.tobyspringboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TobySpringBootApplication {

    public static void main(String[] args) {
        // 스프링 부트가 톰캣 서블릿 컨테이너를 내장하여 쉽게 시작할 수 있게 만들어 놓은 도우미 클레스
        // Factory 복잡한 설정 과정을 거쳐서 TomcatServletWebServerFactory 만들어준다
        ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        WebServer webServer = serverFactory.getWebServer(servletContext -> {
           servletContext.addServlet("hello", new HttpServlet() { // 처리하겠다
               @Override
               protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                   resp.setStatus(200);
                   resp.setHeader("Content-Type", "text/plain");
                   resp.getWriter().println("Hello Servlet");
               }
           }).addMapping("/hello"); // uri 로 요청이 오면
        });
        webServer.start(); // 톰캣 서블릿 컨테이너가 실행된다.
    }

}
