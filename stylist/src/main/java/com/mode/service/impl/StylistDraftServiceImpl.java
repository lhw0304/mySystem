package com.mode.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mode.config.AppConfig;
import com.mode.config.Message;
import com.mode.config.Response;
import com.mode.config.Status;
import com.mode.dao.ImageTaggifyDAO;
import com.mode.dao.StylistDraftDAO;
import com.mode.dao.BrandDAO;
import com.mode.dao.ItemDAO;
import com.mode.entity.ImageTaggify;
import com.mode.entity.StylistDraft;
import com.mode.entity.Brand;
import com.mode.entity.Item;
import com.mode.service.StylistDraftService;

/**
 * Created by zhaoweiwei on 15/11/18.
 */
@Service
public class StylistDraftServiceImpl implements StylistDraftService {

    @Autowired
    private StylistDraftDAO stylistDraftDAO;

    @Autowired
    private ItemDAO itemDAO;

    @Autowired
    private BrandDAO brandDAO;

    @Autowired
    private ImageTaggifyDAO imageTaggifyDAO;

    @Override
    public Response createStylistDraft(Integer stylistId, String type,
                                       String content,
                                       Integer status) {
        Response res = new Response();
        try {
            long now = System.currentTimeMillis();
            StylistDraft stylistDraft = new StylistDraft();
            stylistDraft.setStylistId(stylistId);
            stylistDraft.setType(type);
            stylistDraft.setStatus(status);
            stylistDraft.setContent(content);
            stylistDraft.setCtime(now);
            stylistDraft.setUtime(now);
            stylistDraftDAO.createStylistDraft(stylistDraft);
            res.setMessage(Message.SUCCESS);
            Map<String, Object> payload = new HashMap<>();
            payload.put("draft", stylistDraft);
            res.setPayload(payload);
        } catch (Exception e) {
            e.printStackTrace();
            res.setMessage(Message.FAILURE);
        }
        return res;
    }

    @Override
    public Response updateStylistDraft(Integer id, String type, String content, Integer status,
                                       String comment) {
        Response res = new Response();
        try {
            long now = System.currentTimeMillis();
            StylistDraft stylistDraft = new StylistDraft();
            stylistDraft.setId(id);
            stylistDraft.setType(type);
            stylistDraft.setStatus(status);
            stylistDraft.setContent(content);
            stylistDraft.setComment(comment);
            stylistDraft.setUtime(now);
            Integer success = stylistDraftDAO.updateStylistDraft(stylistDraft);
            if (success == null || success == 0) {
                res.setMessage(Message.FAILURE);
                return res;
            }
            res.setMessage(Message.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            res.setMessage(Message.FAILURE);
        }
        return res;
    }

    @Override
    public Response deleteStylistDraft(Integer id) {
        Response res = new Response();
        try {
            Integer success = stylistDraftDAO.deleteStylistDraft(id);
            if (success == null || success == 0) {
                res.setMessage(Message.FAILURE);
                return res;
            }
            res.setMessage(Message.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            res.setMessage(Message.FAILURE);
        }
        return res;
    }

    @Override
    public Response listStylistDraftsByStylistId(Integer stylistId, Integer limit,
                                                 Integer offset) {
        Response res = new Response();
        try {
            limit = (limit == null) ? AppConfig.DEFAULT_LIMIT : limit;
            offset = (offset == null) ? AppConfig.DEFAULT_OFFSET : offset;
            List<StylistDraft> list = stylistDraftDAO.listStylistDraftsByStylistId(stylistId,
                    limit, offset);

            if (list == null || list.isEmpty()) {
                res.setMessage(Message.NO_MORE_STYLIST_CONTENT);
                return res;
            }

            Integer total = stylistDraftDAO.getStylistDraftCountByStylistId(stylistId);

            Map<String, Object> payload = new HashMap<String, Object>();
            payload.put("list", list);
            payload.put("total", total);
            res.setMessage(Message.SUCCESS);
            res.setPayload(payload);
        } catch (Exception e) {
            e.printStackTrace();
            res.setMessage(Message.FAILURE);
        }
        return res;
    }

    @Override
    public Response listStylistDraftsByStylistIdAndStatus(Integer stylistId, Integer status,
                                                          Integer limit, Integer offset) {
        Response res = new Response();
        try {
            limit = (limit == null) ? AppConfig.DEFAULT_LIMIT : limit;
            offset = (offset == null) ? AppConfig.DEFAULT_OFFSET : offset;
            List<StylistDraft> list = stylistDraftDAO.listStylistDraftsByStylistIdAndStatus
                    (stylistId, status, limit, offset);

            if (list == null || list.isEmpty()) {
                res.setMessage(Message.NO_MORE_STYLIST_CONTENT);
                return res;
            }

            Integer total = stylistDraftDAO.getStylistDraftCountByStylistIdAndStatus(stylistId,
                    status);

            Map<String, Object> payload = new HashMap<String, Object>();
            payload.put("list", list);
            payload.put("total", total);
            res.setMessage(Message.SUCCESS);
            res.setPayload(payload);
        } catch (Exception e) {
            e.printStackTrace();
            res.setMessage(Message.FAILURE);
        }
        return res;
    }

    @Override
    public Response getStylistDraft(Integer id) {
        Response res = new Response();
        try {
            StylistDraft stylistDraft = stylistDraftDAO.getStylistDraft(id);
            if (stylistDraft == null) {
                res.setMessage(Message.NO_MORE_STYLIST_CONTENT);
                return res;
            }
            Map<String, Object> payload = new HashMap<String, Object>();
            payload.put("stylistDraft", stylistDraft);
            res.setMessage(Message.SUCCESS);
            res.setPayload(payload);
        } catch (Exception e) {
            e.printStackTrace();
            res.setMessage(Message.FAILURE);
        }
        return res;
    }

    @Override
    public Response searchItems(String itemName, List<Integer> brandIdList, List<Integer> typeList,
                                List<String> countryCodeList, Integer limit, Integer offset) {
        Response res = new Response();

        try {
            itemName = (itemName == null) ? itemName : "%" + itemName + "%";
            brandIdList = (brandIdList == null || brandIdList.size() == 0) ? null : brandIdList;
            typeList = (typeList == null || typeList.size() == 0) ? null : typeList;
            countryCodeList = (countryCodeList == null || countryCodeList.size() == 0) ?
                    null : countryCodeList;
            limit = (limit == null) ? AppConfig.DEFAULT_LIMIT : limit;
            offset = (offset == null) ? AppConfig.DEFAULT_OFFSET : offset;

            List<Item> items = itemDAO.searchItems(itemName, brandIdList, typeList,
                    countryCodeList, limit, offset);
            if (items.isEmpty() || items == null) {
                res.setMessage(Message.NO_MORE_RECORD);
                return res;
            }

            Integer total = itemDAO.getItemCount(itemName, brandIdList, typeList, countryCodeList);

            Map<String, Object> payload = new HashMap<String, Object>();
            res.setMessage(Message.SUCCESS);
            payload.put("items", items);
            payload.put("total", total);
            res.setPayload(payload);
        } catch (Exception e) {
            e.printStackTrace();
            res.setMessage(Message.FAILURE);
        }

        return res;
    }

    public Response listBrands(Integer limit, Integer offset) {
        Response res = new Response();

        try {
            List<Brand> brands = new ArrayList<>();
            brands = brandDAO.listBrandsHavingItems();

            if (brands.isEmpty()) {
                res.setMessage(Message.FAILURE);
            } else {
                Map<String, Object> payload = new HashMap<>();
                payload.put("brands", brands);
                res.setPayload(payload);
                res.setMessage(Message.SUCCESS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            res.setMessage(Message.FAILURE);
        }

        return res;
    }

    /**
     * Get taggified image via id
     *
     * @param id
     * @return
     */
    public Response getTaggifiedImage(Integer id) {
        Response res = new Response();

        try {
            ImageTaggify imageTaggify = imageTaggifyDAO.getImageTaggify(id);

            Map<String, Object> payload = new HashMap<>();
            payload.put("taggifiedImage", imageTaggify);
            res.setPayload(payload);
            res.setMessage(Message.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            res.setMessage(Message.FAILURE);
        }

        return res;
    }

    /**
     * Store taggified image to db
     *
     * @param taggifiedImage
     * @param stylistId
     * @param draftId
     * @return
     */
    public Response createTaggifiedImage(String taggifiedImage, Integer stylistId, Integer draftId) {
        Response res = new Response();

        try {
            final Long now = System.currentTimeMillis();

            ImageTaggify imageTaggify = new ImageTaggify();
            imageTaggify.setCtime(now);
            imageTaggify.setUtime(now);
            imageTaggify.setTaggifiedImage(taggifiedImage);
            imageTaggify.setStylistId(stylistId);
            imageTaggify.setDraftId(draftId);

            imageTaggifyDAO.createImageTaggify(imageTaggify);

            Map<String, Object> payload = new HashMap<>();
            payload.put("taggifiedImage", imageTaggify);
            res.setPayload(payload);
            res.setMessage(Message.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            res.setMessage(Message.FAILURE);
        }

        return res;
    }

    /**
     * Update taggified image
     *
     * @param taggifiedImage
     * @param id
     * @return
     */
    public Response updateTaggifiedImage(String taggifiedImage, Integer id) {
        Response res = new Response();

        try {
            final Long now = System.currentTimeMillis();

            ImageTaggify imageTaggify = new ImageTaggify();
            imageTaggify.setUtime(now);
            imageTaggify.setTaggifiedImage(taggifiedImage);
            imageTaggify.setId(id);

            imageTaggifyDAO.updateImageTaggify(imageTaggify);

            res.setMessage(Message.SUCCESS);
        } catch (Exception e){
            e.printStackTrace();
            res.setMessage(Message.FAILURE);
        }

        return res;
    }

    /**
     * List taggified images
     *
     * @param stylistId
     * @param limit
     * @param offset
     * @return
     */
    public Response listTaggifiedImages(Integer stylistId, Integer limit, Integer offset){
        Response res = new Response();

        try {
            List<ImageTaggify> taggifiedImages = imageTaggifyDAO.listImageTaggify(stylistId,
                    null, limit, offset);

            if (taggifiedImages == null || taggifiedImages.isEmpty()) {
                res.setMessage(Message.FAILURE);
            } else {
                Map<String, Object> payload = new HashMap<>();
                payload.put("taggifiedImages", taggifiedImages);
                res.setPayload(payload);
                res.setMessage(Message.SUCCESS);
            }
        } catch (Exception e){
            e.printStackTrace();
            res.setMessage(Message.FAILURE);
        }

        return res;
    }

    /**
     * Delete tagged images of an article
     *
     * @param draftId
     * @param userId
     */
    public Response deleteTaggedImage(Integer draftId, Integer userId){
        Response res = new Response();

        try{
            if(userId != null) {
                imageTaggifyDAO.deleteImageTagged(draftId);
                res.setMessage(Message.SUCCESS);
            }else {
                res.setMessage(Message.FAILURE);
            }
        } catch (Exception e){
            e.printStackTrace();
            res.setMessage(Message.FAILURE);
        }

        return res;
    }
}
