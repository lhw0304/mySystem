package com.mode.service;

import com.mode.config.Response;

/**
 * Created by zhaoweiwei on 15/11/30.
 */
public interface SocialService {

    /**
     * Stylist comment an article
     *
     * @param stylistId
     * @param articleId
     * @param content
     * @return
     */
    public Response createStylistComment(Integer stylistId, Integer articleId, String content);


    /**
     * List comments of the article
     *
     * @param headlineId
     * @param limit
     * @param offset
     * @return
     */
    public Response listComments(Integer headlineId, Integer limit, Integer
            offset);

}
