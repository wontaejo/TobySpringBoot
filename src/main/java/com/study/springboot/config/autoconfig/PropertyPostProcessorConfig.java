package com.study.springboot.config.autoconfig;

import com.study.springboot.config.MyAutoConfiguration;
import com.study.springboot.config.MyConfigurationProperties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.env.Environment;

import java.util.Map;

@MyAutoConfiguration
public class PropertyPostProcessorConfig {
    @Bean
    BeanPostProcessor propertyPostProcessor(Environment env) {
        return new BeanPostProcessor() {
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                MyConfigurationProperties annotation = AnnotationUtils.findAnnotation(bean.getClass(), MyConfigurationProperties.class); // 해당 애노테이션이 선언된 빈을 찾음
                if (annotation == null) {
                    return bean; // 후처리 없이 bean return
                }

                Map<String, Object> attrs = AnnotationUtils.getAnnotationAttributes(annotation);
                String prefix = String.valueOf(attrs.get("prefix"));

                return Binder.get(env).bindOrCreate(prefix, bean.getClass()); // MyConfigurationProperties 애노테이션이 붙어있는 경우에는 property 값을 바인딩 한다.
            }
        };
    }
}
