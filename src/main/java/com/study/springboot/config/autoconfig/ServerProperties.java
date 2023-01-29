package com.study.springboot.config.autoconfig;

import com.study.springboot.config.MyConfigurationProperties;

@MyConfigurationProperties(prefix = "server") // 네임스페이스 == 패키지
public class ServerProperties {
    private String contextPath;
    private int port;

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
