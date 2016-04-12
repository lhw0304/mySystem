package com.mode.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mode.config.Response;
import com.mode.service.SocialService;

/**
 * Created by zhaoweiwei on 15/11/30.
 */
@RestController
@RequestMapping("/v2/stylists")
public class SocialAPI {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SocialService socialService;

    /**
     * @api {POST} /v2/stylists/{stylistId}/comment/articles/{articleId} User comment an article
     * @apiName createStylistComment
     * @apiGroup Social
     * @apiParam {Number} stylistId Stylist's unique id
     * @apiParam {Number} articleId It is the headline id that the type of the headline is article
     * @apiParam {String} content Put comment content into body!!
     * @apiHeader {String} X-Auth-Token Access token for authentication
     * @apiVersion 3.1.0
     * @apiDescription This API is used to make comments for article by stylists
     * @apiSuccessExample {json} Success 200:
     * {
     * "message": {
     * "code": 0,
     * "description": "Operation succeeded"
     * },
     * "payload": {
     * "comment": {
     * "id": 2,
     * "userId": 30000,
     * "objectType": "article",
     * "objectId": 1,
     * "content": "hello你好",
     * "ctime": 1447816454610
     * }
     * }
     * }
     * @apiErrorExample {json} Error:
     * {
     * "message": {
     * "code": 1,
     * "description": "Operation Failed"
     * },
     * "payload": null
     * }
     */
    @RequestMapping(value = "/{stylistId}/comment/articles/{articleId}", method = RequestMethod.POST)
    public Response createStylistComment(@PathVariable("stylistId") Integer stylistId,
                                         @PathVariable("articleId") Integer articleId,
                                         @RequestBody String content) {
        Response res = socialService.createStylistComment(stylistId, articleId, content);
        logger.info("/v2/stylists/{}/comment/articles/{}, {}, {}", stylistId, articleId, content,
                res.getMessage());
        return res;
    }

    /**
     * @api {GET} /v2/stylists/articles/{articleId}/comments List comments of an article
     * @apiName listComments
     * @apiGroup Social
     * @apiParam {Number} articleId It is the headline id that the type of the headline is article
     * @apiParam {String} sortType The sort type of the comment: 'ctime' or 'likes'
     * @apiParam {Number} [limit] The limit number to show the user comments. Default value = 10
     * @apiParam {Number} [offset] The offset of the user comment list. Default value = 0
     * @apiHeader {String} X-Auth-Token Access token for authentication
     * @apiVersion 3.1.0
     * @apiDescription This API is used to List the comments of the article. The order of the
     * comments is order by ctime desc.
     * @apiSuccessExample {json} Success 200:
     * {
     * "message": {
     * "code": 0,
     * "description": "Operation succeeded"
     * },
     * "payload": {
     * "userComments": [
     * {
     * "id": 2,
     * "userId": 30000,
     * "objectType": "article",
     * "objectId": 1,
     * "content": "hello你好",
     * "ctime": 1447816454610
     * },
     * ......
     * ]
     * }
     * }
     * @apiErrorExample {json} Error:
     * {
     * "message": {
     * "code": 1,
     * "description": "Operation Failed"
     * },
     * "payload": null
     * }
     */
    @RequestMapping(value = "/articles/{articleId}/comments", method = RequestMethod.GET)
    public Response listComments(@PathVariable("articleId") Integer articleId,
                                 @RequestParam(value = "limit", required = false) Integer limit,
                                 @RequestParam(value = "offset", required = false) Integer offset) {
        Response res = socialService.listComments(articleId, limit, offset);
        logger.info("/v2/stylists/articles/{}/comments?limit={}&offset={}, {}",
                articleId, limit, offset, res.getMessage());
        return res;
    }


}
