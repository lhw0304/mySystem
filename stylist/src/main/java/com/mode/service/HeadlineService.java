package com.mode.service;

import com.mode.config.Response;

/**
 * Created by zhaoweiwei on 15/11/19.
 */
public interface HeadlineService {

    public Response updateHeadlineStatus(Integer id, Integer status, Integer userId);

    public Response listHeadlines(Integer stylistId, Integer limit, Integer offset);

    public Response getHeadline(Integer id);
}
