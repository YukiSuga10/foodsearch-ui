package com.yuksuga.foodsearchui.configuration;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;



@ConfigurationProperties
public class ApiCallConfigurationProperties {

    private final String host = "localhost";

    private final int port = 8080;


    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }
}
