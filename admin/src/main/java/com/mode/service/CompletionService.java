package com.mode.service;

import com.mode.config.Response;

/**
 * Created by kang on 2016/3/11.
 */
public interface CompletionService {

    public Response createCompletion(Integer userId, String content, String answer);

    public Response deleteCompletion(Integer id);

    public Response getCompletion(Integer id);

    public Response getCompletionList(Integer userId, Integer limit, Integer offset);

    public Response updateCompletion(Integer id, Integer userId, String content, String answer);
}
