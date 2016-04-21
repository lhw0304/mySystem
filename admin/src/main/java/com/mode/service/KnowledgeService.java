package com.mode.service;

import com.mode.config.Response;

/**
 * Created by Administrator on 2016/4/21.
 */
public interface KnowledgeService {

    public Response createKnowledge(Integer userId, String knowledge);

    public Response deleteKnowledge(Integer id);

    public Response getKnowledge(Integer id);

    public Response getKnowledgeList(Integer userId, Integer limit, Integer offset);

    public Response updateKnowledge(Integer id, String knowledge);
}
