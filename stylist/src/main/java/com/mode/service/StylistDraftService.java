package com.mode.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mode.config.Response;

/**
 * Created by zhaoweiwei on 15/11/18.
 */
public interface StylistDraftService {

    public Response createStylistDraft(Integer stylistId, String type, String content,
                                       Integer status);

    public Response updateStylistDraft(Integer id, String type, String content, Integer status,
                                       String comment);

    public Response deleteStylistDraft(Integer id);

    public Response listStylistDraftsByStylistId(Integer stylistId, Integer
            limit, Integer offset);

    public Response listStylistDraftsByStylistIdAndStatus(Integer stylistId, Integer status,
                                                          Integer limit, Integer offset);

    public Response getStylistDraft(Integer id);

    public Response searchItems(String itemName, List<Integer> brandIdList, List<Integer> typeList,
                                List<String> countryCodeList, Integer limit, Integer offset);

    public Response listBrands(Integer limit, Integer offset);

    /**
     * Get taggified image via id
     *
     * @param id
     * @return
     */
    public Response getTaggifiedImage(Integer id);

    /**
     * List taggified images
     *
     * @param stylistId
     * @param limit
     * @param offset
     * @return
     */
    public Response listTaggifiedImages(Integer stylistId, Integer limit, Integer offset);

    /**
     * Store taggified image to db
     *
     * @param taggifiedImage
     * @param stylistId
     * @param draftId
     * @return
     */
    public Response createTaggifiedImage(String taggifiedImage, Integer stylistId, Integer draftId);

    /**
     * Update taggified image
     *
     * @param taggifiedImage
     * @param id
     * @return
     */
    public Response updateTaggifiedImage(String taggifiedImage, Integer id);

    /**
     * Delete tagged images of an article
     *
     * @param draftId
     * @param userId
     */
    public Response deleteTaggedImage(Integer draftId, Integer userId);
}
