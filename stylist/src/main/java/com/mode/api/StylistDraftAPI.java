package com.mode.api;

import com.mode.config.Message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mode.config.AppConfig;
import com.mode.config.Response;
import com.mode.service.StylistDraftService;
import com.mode.util.ImageHandler;

/**
 * Created by zhaoweiwei on 15/11/18.
 */
@RestController
@RequestMapping("/v2/stylistDrafts")
public class StylistDraftAPI {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StylistDraftService stylistDraftService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Response createStylistDraft(@RequestHeader("stylistId") Integer stylistId,
                                       @RequestHeader(value = "type", required = false) String
                                               type,
                                       @RequestBody(required = false) String content,
                                       @RequestHeader(value = "status", required = false) Integer
                                               status) {
        Response res = stylistDraftService.createStylistDraft(stylistId, type, content, status);
        logger.info("/v2/stylistDrafts, {}, {}, {}, {}, {}", stylistId, type, content, status, res
                .getMessage());
        return res;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public Response updateStylistDraft(@PathVariable Integer id,
                                       @RequestHeader(value = "type", required = false) String
                                               type,
                                       @RequestBody(required = false) String content,
                                       @RequestHeader(value = "status", required = false) Integer
                                               status,
                                       @RequestHeader(value = "comment", required = false) String
                                                   comment) {
        Response res = stylistDraftService.updateStylistDraft(id, type, content, status, comment);
        logger.info("/v2/stylistDrafts/{}, {}, {}, {}, {}, {}", id, type, content, status,
                comment, res.getMessage());
        return res;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Response deleteStylistDraft(@PathVariable Integer id) {
        Response res = stylistDraftService.deleteStylistDraft(id);
        logger.info("/v2/stylistDrafts/{}, {}", id, res.getMessage());
        return res;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Response getStylistDraft(@PathVariable Integer id) {
        Response res = stylistDraftService.getStylistDraft(id);
        logger.info("/v2/stylistDrafts/{}, {}", id, res.getMessage());
        return res;
    }

    @RequestMapping(value = "/listTotal", method = RequestMethod.GET)
    public Response listStylistDraftsByStylistId(@RequestParam(value = "stylistId")
                                                 Integer stylistId,
                                                 @RequestParam(value = "limit", required = false)
                                                 Integer limit,
                                                 @RequestParam(value = "offset", required = false)
                                                 Integer offset) {
        Response res = stylistDraftService.listStylistDraftsByStylistId(stylistId, limit, offset);
        logger.info("/v2/stylistDrafts/listTotal, {}, {}, {}, {}", stylistId, limit, offset, res
                .getMessage
                        ());
        return res;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Response listStylistDraftsByStylistIdAndStatus(@RequestParam(value = "stylistId")
                                                          Integer stylistId,
                                                          @RequestParam(value = "status")
                                                          Integer status,
                                                          @RequestParam(value = "limit", required
                                                                  = false)
                                                          Integer limit,
                                                          @RequestParam(value = "offset",
                                                                  required = false)
                                                          Integer offset) {
        Response res = stylistDraftService.listStylistDraftsByStylistIdAndStatus(stylistId,
                status, limit, offset);
        logger.info("/v2/stylistDrafts, {}, {}, {}, {}, {}", stylistId, status, limit, offset,
                res.getMessage());
        return res;
    }

    @RequestMapping(value = "/imageURL", method = RequestMethod.POST)
    public Response getImageURL(@RequestParam(value = "image", required = false)
                                MultipartFile image) {
        ImageHandler imageHandler = new ImageHandler();
        String url = imageHandler.getImageURL(image, AppConfig.FOLDER_STYLIST_AVATAR, 0,
                0, 0, 0);
        logger.info("/v2/stylistDrafts/imageURL, {}", url);
        Response res = new Response();
        Map<String, Object> payload = new HashMap<>();
        payload.put("url", url);
        res.setPayload(payload);
        res.setMessage(Message.SUCCESS);
        return res;
    }

    @RequestMapping(value = "/coverImage/imageURL", method = RequestMethod.POST)
    public Response getCoverImageURL(@RequestParam(value = "image", required = false)
                                MultipartFile image,
                                     @RequestParam(value = "cropperString") String cropperString) {
        ImageHandler imageHandler = new ImageHandler();
        String[] cropperData = cropperString.split("-");
        Integer x = Integer.parseInt(cropperData[0]);
        Integer y = Integer.parseInt(cropperData[1]);
        Integer width = Integer.parseInt(cropperData[2]);
        Integer height = Integer.parseInt(cropperData[3]);
        String url = imageHandler.getImageURL(image, AppConfig.FOLDER_ARTICLE_COVER_IMAGE, x,
                y, width, height);
        logger.info("/v2/stylistDrafts/imageURL, {}", url);
        Response res = new Response();
        Map<String, Object> payload = new HashMap<>();
        payload.put("url", url);
        res.setPayload(payload);
        res.setMessage(Message.SUCCESS);
        return res;
    }

    @RequestMapping(value = "/searchItems", method = RequestMethod.GET)
    public Response listItems(@RequestParam(value = "itemName", required = false) String itemName,
                              @RequestParam(value = "brandIdList", required = false)
                              List<Integer> brandIdList,
                              @RequestParam(value = "typeList", required = false)
                              List<Integer> typeList,
                              @RequestParam(value = "countryCodeList", required = false)
                              List<String> countryCodeList,
                              @RequestParam(value = "limit", required = false) Integer limit,
                              @RequestParam(value = "offset", required = false) Integer offset) {
        Response res = stylistDraftService.searchItems(itemName, brandIdList, typeList,
                countryCodeList, limit, offset);
        logger.info("/v2/stylistDrafts/searchItems?itemName={}&limit={}&offset={}, {}, {}, {}, {}",
                itemName, limit, offset, brandIdList, typeList, countryCodeList, res.getMessage());
        return res;
    }

    @RequestMapping(value = "brands", method = RequestMethod.GET)
    public Response listBrands(@RequestParam(value = "limit", required = false) Integer limit,
                               @RequestParam(value = "offset", required = false) Integer offset) {
        Response res = stylistDraftService.listBrands(limit, offset);
        logger.info("/v2/stylistDrafts/brands?limit={}&offset={}, {}", limit, offset, res
                .getMessage());
        return res;
    }

    @RequestMapping(value = "/taggifiedImages/{id}", method = RequestMethod.GET)
    public Response getTaggifiedImage(@PathVariable("id") Integer id) {
        Response res = stylistDraftService.getTaggifiedImage(id);
        logger.info("/v2/stylistDrafts/taggifiedImages/{}, {}", id, res.getMessage());
        return res;
    }

    @RequestMapping(value = "/taggifiedImages/stylists/{stylistId}", method = RequestMethod.GET)
    public Response listTaggifiedImages(@PathVariable("stylistId") Integer stylistId,
                                        @RequestParam(value = "limit", required = false) Integer
                                                limit,
                                        @RequestParam(value = "offset", required = false) Integer
                                                    offset){
        Response res = stylistDraftService.listTaggifiedImages(stylistId, limit, offset);
        logger.info("/v2/stylistDrafts/taggifiedImages/stylists/{}?limit={}&offset={}, {}",
                stylistId, limit, offset, res.getMessage());
        return res;
    }

    @RequestMapping(value = "/taggifiedImages", method = RequestMethod.POST)
    public Response createTaggifiedImage(@RequestBody String taggifiedImage,
                                         @RequestHeader(value = "stylistId", required = false)
                                         Integer stylistId,
                                         @RequestHeader(value = "draftId", required = false)
                                         Integer draftId) {
        Response res = stylistDraftService.createTaggifiedImage(taggifiedImage, stylistId, draftId);
        logger.info("/v2/stylistDrafts/taggifiedImages, {}, {}, {}, {}", stylistId, draftId,
                taggifiedImage, res.getMessage());
        return res;
    }

    @RequestMapping(value = "/taggifiedImages/draft/{draftId}", method = RequestMethod.POST)
    public Response deleteTaggifiedImages(@PathVariable("draftId") Integer draftId,
                                          @RequestHeader(value = "userId", required = false) Integer
                                                  userId){
        Response res = stylistDraftService.deleteTaggedImage(draftId, userId);
        logger.info("/v2/stylistDrafts/taggifiedImages/draft/{}, {}, {}", draftId, userId,
                res.getMessage());
        return res;
    }

    @RequestMapping(value = "/taggifiedImages/{id}", method = RequestMethod.POST)
    public Response updateTaggifiedImage(@PathVariable("id") Integer id,
                                         @RequestBody String taggifiedImage) {
        Response res = stylistDraftService.updateTaggifiedImage(taggifiedImage, id);
        logger.info("/v2/stylistDrafts/taggifiedImages/{}, {}, {}", id, taggifiedImage, res
                .getMessage());
        return res;
    }
}
