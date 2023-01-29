package com.study.springboot.config.autoconfig;

import com.study.springboot.config.MyAutoConfiguration;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@MyAutoConfiguration
public class ServerPropertiesConfig {

    @Bean
    public ServerProperties serverProperties(Environment environment) {
        return Binder.get(environment).bind("", ServerProperties.class).get(); // Property 소스의 Property 이름과 Property Class 의 Property 이름이 같은것을 자동으로 바인딩 해줌 추가되는 별도의 필드들을 따로 정의하지 않고 사용 가능
    }
}
