package com.yuksuga.foodsearchui.configuration;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@ConfigurationProperties(prefix = "authentication")
public class AuthenticationConfigurationProperties {

    private final Integer tempPasswordLength;

    private final Integer passwordExpiredDays;

    private final Integer tempPasswordExpiredDays;

    public AuthenticationConfigurationProperties(Integer tempPasswordLength, Integer passwordExpiredDays,
                                                 Integer tempPasswordExpiredDays) {
        this.tempPasswordLength = tempPasswordLength;
        this.passwordExpiredDays = passwordExpiredDays;
        this.tempPasswordExpiredDays = tempPasswordExpiredDays;
    }

    public Integer getTempPasswordLength() {
        return tempPasswordLength;
    }

    public Integer getPasswordExpiredDays() {
        return passwordExpiredDays;
    }

    public Integer getTempPasswordExpiredDays() {
        return tempPasswordExpiredDays;
    }
}
