package com.study.springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // API 로서 응답 처리를 하기 위애 @ResponseBody ( // @RestController 를 붙으면 자동으로 붙음, 이 애노테이션이 없을 경우 String 리턴의 경우 DispatcherServlet 이 View 이름으로 인식하기 때문에 view 를 정의하지 않는다면 404. ) 까지 정의
public class HelloController {
    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping ("/hello")// DispatcherServlet 이 ApplicationContext 받은 Bean 정보를 다 뒤져서 웹요청을 처리할 수 있는 매핑 정보 ( @GetMapping ) 를 다 찾아서 매핑 테이블에 패스와 메소드를 매핑해놓는다.
    public String hello(String name) {
        if (name == null || name.trim().length() == 0) {
            throw new IllegalArgumentException();
        }

        return helloService.sayHello(name);
    }
}
