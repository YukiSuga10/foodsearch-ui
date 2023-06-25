package com.yuksuga.foodsearchui.configuration;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.yuksuga.foodsearchui.dao.RestTemplateResponseErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({ApiCallConfigurationProperties.class, AuthenticationConfigurationProperties.class})
public class UiConfiguration {

    private final ApiCallConfigurationProperties properties;

    private final AuthenticationConfigurationProperties authenticationConfigurationProperties;

    public UiConfiguration(ApiCallConfigurationProperties properties,
                           AuthenticationConfigurationProperties authenticationConfigurationProperties) {
        this.properties = properties;
        this.authenticationConfigurationProperties = authenticationConfigurationProperties;
    }

    @Bean
    @Autowired
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder){
        return restTemplateBuilder
                .requestFactory(HttpComponentsClientHttpRequestFactory.class)
                .errorHandler(new RestTemplateResponseErrorHandler()).build();
    }
}
