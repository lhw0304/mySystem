package com.mode.service;

import com.mode.config.Response;

/**
 * Created by Administrator on 2016/3/17.
 */
public interface EssayService {

    public Response createEssay(Integer userId, String content, String answer);

    public Response deleteEssay(Integer id);

    public Response getEssay(Integer id);

    public Response getEssayList(Integer userId, Integer limit, Integer offset);

    public Response updateEssay(Integer id, Integer userId, String content, String answer);
}
