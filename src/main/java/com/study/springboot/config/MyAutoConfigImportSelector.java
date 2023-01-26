package com.study.springboot.config;

import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyAutoConfigImportSelector implements DeferredImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[] {
                "com.study.springboot.config.autoconfig.DispatcherServletConfig",
                "com.study.springboot.config.autoconfig.TomcatWebServerConfig",
        };
    }
}
