package com.mode.service;

import com.mode.config.Response;

/**
 * Created by kang on 2016/3/25.
 */
public interface GroupService {

    public Response createGroup(Integer userId, Integer checkCount,Integer completionCount,
                                Integer singleSelectCount, Integer multiSelectCount, Integer
                                        shortAnswerCount);
}
