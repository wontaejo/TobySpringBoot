package com.study.springboot.tobyspringboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

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
            HelloController helloController = new HelloController(); // 서블릿이 초기화될때 한번만 선언이 되면 된다.

           servletContext.addServlet("frontController", new HttpServlet() { // 처리하겠다
               @Override
               protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                   // 인증, 보안, 다국어, 공통 기능

                   // 매핑 : 웹요청에 있는 정보를 활용하여 어떤 요청을 호출할 것인지 선택.
                   if (req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())) {
                       String name = req.getParameter("name");

                       String hello = helloController.hello(name); // 바인딩 : request 정보를 추출하여 처리하는 오브젝트로 파라미터로 넘겨주는것

                       resp.setStatus(HttpStatus.OK.value());
                       resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
                       resp.getWriter().println(hello);
                   } else if (req.getRequestURI().equals("/user")) {
                       //
                   } else {
                       resp.setStatus(HttpStatus.NOT_FOUND.value());
                   }
               }
           }).addMapping("/*"); // uri 로 요청이 오면
        });
        webServer.start(); // 톰캣 서블릿 컨테이너가 실행된다.
    }

}
