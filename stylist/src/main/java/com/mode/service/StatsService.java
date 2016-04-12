package com.mode.service;

import java.util.List;

import com.mode.config.Response;

/**
 * Created by zhaoweiwei on 15/11/19.
 */
public interface StatsService {

    public Response listStylistArticleStats(Integer stylistId, Integer limit, Integer offset);

    public void stylistCommissionStats();

    public Response listStylistCommissionStats(Integer stylistId, Integer limit, Integer offset);

    public Response listStylistArticleStatsByDate(Integer articleId, Integer limit);

    public Response listConfig(Integer userId);
}
