package com.mode.service;

import com.mode.config.Response;

/**
 * Created by Administrator on 2016/3/10.
 */
public interface CheckService {

    public Response createCheck(Integer userId, String content, Integer answer);

    public Response deleteCheck(Integer id);

    public Response getCheck(Integer id);

    public Response getCheckList(Integer userId, Integer limit, Integer offset);

    public Response updateCheck(Integer id, Integer userId, String content, Integer answer);
}
