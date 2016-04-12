package com.mode.service;

import org.hamcrest.CoreMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mode.base.Message;
import com.mode.base.Response;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Base service defines the basic common test logic
 *
 * Created by Lei on 3/21/16.
 */
@Service
public class BaseService {

    @Autowired
    protected ObjectMapper om;

    /**
     * Basic Successful Response Status Code test logic
     *
     * @param status
     */
    protected static void successResponseStatusCodeCheck(HttpStatus status) {
        assertThat(status, CoreMatchers.equalTo(HttpStatus.OK));
    }

    protected static void successResponseMessageCheck(int code, String des){
        assertThat(code, CoreMatchers.equalTo(Message.SUCCESS.getCode()));
        assertThat(des, CoreMatchers.equalTo(Message.SUCCESS.getDescription()));
    }


    protected Response parseResponseBody(String body){
        Response res = null;
        try {
            res = om.readValue(body, Response.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }
}
