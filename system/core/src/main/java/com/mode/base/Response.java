package com.mode.base;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.mode.entity.View;

/**
 * This is the response object that represents the server responses to client
 * requests. Please don't change this class, as it will impact the mobile
 * clients which will require users to upgrade the client app.
 * <p/>
 * The response object consists of the return code and message, and the payload. For different
 * API calls, the payload may differ.
 *
 * @author chao
 */
public final class Response<T> {

    @JsonView(View.Summary.class)
    private int code;

    @JsonView(View.Summary.class)
    private String message;

    @JsonView(View.Summary.class)
    private T payload;

    public Response(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Response(Message m) {
        this.code = m.getCode();
        this.message = m.getDescription();
    }

    @JsonCreator
    public Response(@JsonProperty("code") int code,
                    @JsonProperty("message") String message,
                    @JsonProperty("payload") T payload){
        this.code = code;
        this.message = message;
        this.payload = payload;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }
}