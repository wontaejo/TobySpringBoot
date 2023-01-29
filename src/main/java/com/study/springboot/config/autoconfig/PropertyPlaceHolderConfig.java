package com.study.springboot.config.autoconfig;

import com.study.springboot.config.MyAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@MyAutoConfiguration
public class PropertyPlaceHolderConfig {
    @Bean
    PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer(); // Environment 로 추상화된 각종 프로퍼티 소스들로 부터 placeHolder 에 값을 지정해준다
    }
}
