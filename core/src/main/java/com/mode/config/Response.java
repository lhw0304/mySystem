package com.mode.config;

import java.util.Map;

/**
 * This is the response object that represent the server responses to client
 * requests. Please don't change this class, as it will impact the mobile
 * clients which will require users to upgrade the client app.
 * <p/>
 * The response object consists of the return message {@link Message}, and the
 * payload which is a <K, V> map. For different API calls, the payload may
 * differ.
 *
 * @author chao
 */
public class Response {

    private Message message;
    private Map<String, ?> payload;

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public Map<String, ?> getPayload() {
        return payload;
    }

    public void setPayload(Map<String, ?> payload) {
        this.payload = payload;
    }
}