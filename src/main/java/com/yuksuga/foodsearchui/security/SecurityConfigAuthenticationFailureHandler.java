package com.yuksuga.foodsearchui.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.security.core.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.web.authentication.ExceptionMappingAuthenticationFailureHandler;

public class SecurityConfigAuthenticationFailureHandler extends ExceptionMappingAuthenticationFailureHandler {

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfigAuthenticationFailureHandler.class);

    private HttpServletRequest request;

    private HttpServletResponse response;

    private AuthenticationException exception;

    public SecurityConfigAuthenticationFailureHandler() {
        this.setDefaultFailureUrl("/");
        this.setExceptionMappings(getFailureUrlMap());
    }

    private Map<String, String> getFailureUrlMap() {
        Map<String, String> map = new HashMap<>();
        map.put(InternalAuthenticationServiceException.class.getName(), "/error/auth");
        return map;
    }

    @Override
    public void onAuthenticationFailure(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
        this.request = request;
        this.response = response;
        this.exception = exception;
        logger.error("Error : " + exception);
        super.onAuthenticationFailure(request, response, exception);
    }

}
