package com.yuksuga.foodsearchui.dao;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;
import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
        return (httpResponse.getStatusCode().value() == CLIENT_ERROR.value()
                || httpResponse.getStatusCode().value() == SERVER_ERROR.value());
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse) throws IOException {
        if (httpResponse.getStatusCode().value() == SERVER_ERROR.value()) {
            throw new SystemErrorException(httpResponse.getStatusText());
        } else if (httpResponse.getStatusCode().value() == CLIENT_ERROR.value()) {
            if (httpResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new ResourceNotFoundException(httpResponse.getStatusText());
            } else if (httpResponse.getStatusCode() == HttpStatus.CONFLICT) {
                throw new DuplicateKeyException(httpResponse.getStatusText());
            }
        }
    }
}
