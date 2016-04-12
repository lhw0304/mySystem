package com.mode.service;

import com.mode.config.Response;

/**
 * Created by нд╬Щ on 2016/3/11.
 */
public interface ShortAnswerService {

    public Response createShortAnswer(Integer userId, String content, String answer);

    public Response deleteShortAnswer(Integer id);

    public Response getShortAnswer(Integer id);

    public Response getShortAnswerList(Integer userId, Integer limit, Integer offset);

    public Response updateShortAnswer(Integer id, Integer userId, String content, String answer);
}
