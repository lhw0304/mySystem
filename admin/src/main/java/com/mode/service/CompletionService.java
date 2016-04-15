package com.mode.service;

import com.mode.config.Response;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * Created by �ľ� on 2016/3/11.
 */
public interface CompletionService {

    public Response createCompletion(Integer userId, MultipartHttpServletRequest mRequest);

    public Response deleteCompletion(Integer id);

    public Response getCompletion(Integer id);

    public Response getCompletionList(Integer userId, Integer limit, Integer offset);

    public Response updateCompletion(Integer id, Integer userId, MultipartHttpServletRequest mRequest);
}
