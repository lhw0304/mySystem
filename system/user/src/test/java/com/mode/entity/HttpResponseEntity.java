package com.mode.entity;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

/**
 * Created by Lei on 3/21/16.
 */
public class HttpResponseEntity {

    private String body;
    private HttpHeaders headers;
    private HttpStatus status;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public HttpHeaders getHeaders() {
        return headers;
    }

    public void setHeaders(HttpHeaders headers) {
        this.headers = headers;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "{"
                + "                        \"body\":\"" + body + "\""
                + ",                         \"headers\":" + headers
                + ",                         \"status\":\"" + status + "\""
                + "}";
    }
}
