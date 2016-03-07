package com.mode.service;

import com.mode.config.Response;

/**
 * Created by Administrator on 2016/3/7.
 */
public interface SingleSelectService {

    public Response createSingleSelect(Integer userId, String content, String a, String b, String c, String d, String answer);

    public Response fetchSingleSelect(Integer id);

    public Response deleteSingleSelect(Integer id);

    public Response getSingleSelectList(Integer userId, Integer limit, Integer offset);

    public Response updateSingleSelect(Integer id, Integer userId, String content, String a, String b, String c, String d, String answer);

}
