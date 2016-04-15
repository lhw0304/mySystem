package com.mode.service;

import com.mode.config.Response;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * Created by �ľ� on 2016/3/11.
 */
public interface ShortAnswerService {

    public Response createShortAnswer(Integer userId, MultipartHttpServletRequest mRequest);

    public Response deleteShortAnswer(Integer id);

    public Response getShortAnswer(Integer id);

    public Response getShortAnswerList(Integer userId, Integer limit, Integer offset);

    public Response updateShortAnswer(Integer id, Integer userId, MultipartHttpServletRequest mRequest);
}
