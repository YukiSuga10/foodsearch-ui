package com.yuksuga.foodsearchui.dao;

import com.yuksuga.foodsearchui.configuration.ApiCallConfigurationProperties;
import com.yuksuga.foodsearchui.domain.User;
import com.yuksuga.foodsearchui.domain.UserList;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class UserDaoImpl implements UserDao, InitializingBean {

    private final ApiCallConfigurationProperties properties;

    private final RestOperations restOperations;

    private String apiUrlPrefix;

    public UserDaoImpl(ApiCallConfigurationProperties properties, RestOperations restOperations) {
        this.properties = properties;
        this.restOperations = restOperations;
    }

    @Override
    public UserList find(String email) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(this.apiUrlPrefix);
        if (email != null){
            builder.queryParam("email", email);
        }
        return this.restOperations.getForObject(builder.build().toString(), UserList.class);
    }

    @Override
    public void add(User user) {

    }

    @Override
    public User findOne(Long Id) {
        return null;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.apiUrlPrefix = "http://" + this.properties.getHost() + ":" + this.properties.getPort() + "/services/users";
    }
}
