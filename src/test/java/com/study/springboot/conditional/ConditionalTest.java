package com.study.springboot.conditional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

public class ConditionalTest {
    @Test
    void conditional() {
        // true
        ApplicationContextRunner contextRunner = new ApplicationContextRunner();
        contextRunner.withUserConfiguration(Config1.class)
                .run(context -> Assertions.assertThat(context).hasSingleBean(MyBean.class)); // bean 이 싱글턴으로 생성되었는지

        // false
        new ApplicationContextRunner().withUserConfiguration(Config2.class)
                .run(context -> {
                    Assertions.assertThat(context).doesNotHaveBean(MyBean.class); // bean 이 싱글턴으로 생성 안되었는지
                    Assertions.assertThat(context).doesNotHaveBean(Config2.class);
                });

    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Conditional(BooleanCondition.class)
    @interface BooleanConditional {
        boolean value();
    }

    @Configuration
    @BooleanConditional(true) // bean 으로 등록 해라
    static class Config1 {
        @Bean
        MyBean mybean() {
            return new MyBean();
        }
    }

    @Configuration
    @BooleanConditional(false) // bean 으로 등록하지 마라, 이때 class 뿐 아니라 method 도 bean 으로 등록 안됨
    static class Config2 {
        @Bean
        MyBean mybean() {
            return new MyBean();
        }
    }

    // annotation 의 파라미터를 전달받아 condition 의 여부를 응답
    static class BooleanCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(BooleanConditional.class.getName()); // annotation 의 안의 속성값들을 모두 불러온다
            Boolean value = (Boolean) annotationAttributes.get("value"); // Attribute 값중 value 별도로 정의하지 않으면 value 임
            return value;
        }
    }

    static class MyBean {
    }
}
