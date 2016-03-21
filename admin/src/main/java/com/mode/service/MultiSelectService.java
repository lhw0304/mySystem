package com.mode.service;

import com.mode.config.Response;

/**
 * Created by Administrator on 2016/3/17.
 */
public interface MultiSelectService {

    public Response createMultiSelect(Integer userId, String content, String a, String b, String c, String d, String answer);

    public Response deleteMultiSelect(Integer id);

    public Response fetchMultiSelect(Integer id);

    public Response getMultiSelectList(Integer userId, Integer limit, Integer offset);

    public Response updateMultiSelect(Integer id, Integer userId, String content, String a, String b,
                                      String c, String d, String answer);
}
