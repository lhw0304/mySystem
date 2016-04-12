package com.mode.api;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.mode.base.BaseConfig;
import com.mode.base.Response;
import com.mode.entity.UserComment;
import com.mode.entity.View;
import com.mode.security.AuthenticationUser;
import com.mode.service.FeedService;

/**
 * Created by Lei on 3/14/16.
 */
@RestController
@RequestMapping("/v3")
public class FeedAPI {

    @Autowired
    private FeedService feedService;

    /**
     * @api {GET} /v3/feeds/{feedId} Get a feed
     * @apiName getFeed
     * @apiGroup Feed
     * @apiVersion 3.0.0
     * @apiDescription This api is used to get a feed content
     *
     * @apiPermission USER
     *
     * @apiHeader {String} X-Access-Token    The unique auth token of the user
     *
     * @apiExample Example usage:
     * GET "http://api.whatsmode.com/v3/feeds/1"
     * -h "X-Access-Token:eyJ1c2VySWQiOj.AsInVzZXJuYW1lIj.oiNTMyNzQ5NTUzQH;"
     *
     * @apiSuccess {Number} code                The code of the message
     * @apiSuccess {String} message             The message content of the response
     * @apiSuccess {Object} payload             The data of the response
     * @apiSuccess {Number} payload.id          The unique id of the feed
     * @apiSuccess {Number} payload.ctime       The create time of the feed
     * @apiSuccess {Number} payload.status      The status of the feed
     * @apiSuccess {String} payload.type        The type of the feed
     * @apiSuccess {String} payload.section     The section of the feed
     * @apiSuccess {String} payload.title       The title of the feed
     * @apiSuccess {String} payload.coverImage  The cover image of the feed
     * @apiSuccess {Number} payload.views       How many people viewed the feed
     * @apiSuccess {Number} payload.saves       How many people saved the feed
     * @apiSUccess {Number} payload.comments    How many people made comment for the feed
     * @apiSuccess {Object[]} payload.content       The content elements of the feed
     * @apiSuccess {String} payload.content.type    The type of the content element
     * @apiSuccess {String} payload.content.content The content of the element
     * @apiSuccess {String} payload.content.defaultImage The image url
     * @apiSuccess {String} payload.content.widthHeightRatio The image w-h ratio
     * @apiSuccess {String} payload.content.items   The item id list split by ','
     * @apiSuccess {String} payload.content.title   The title of url element
     * @apiSuccess {String} payload.content.description The description of url element
     * @apiSuccess {String} payload.content.url     The url of the url element
     * @apiSuccess {Object} payload.author              The author object
     * @apiSuccess {Number} payload.author.authorId     The unique author id
     * @apiSuccess {String} payload.author.avatar       The avatar of the author
     * @apiSuccess {String} payload.author.nickname     The nickname of the author
     *
     * @apiSuccessExample {json} Success 200 (Response Example):
     * {
     *      "code": 0
     *      "message": "Operation Successful",
     *      "payload": {
     *          "id": 1,
     *          "type": "article",
     *          "status": 0,
     *          "ctime": 1234567890123,
     *          "utime": 1234567890123,
     *          "coverImage": "http://img.cdn.whatsmodecom/images/image_4.png",
     *          "title": "100% off, Come on",
     *          "description": "Just Today, you can get everything for free.",
     *          "content": [
     *              {
     *                  "type": "text",
     *                  "content": " MODE now is opening up a special feature—all!",
     *                  "bold": ""
     *              },
     *              {
     *                  "type": "image",
     *                  "defaultImage": "http://img.cdn.whatsmode.com/images/9e62bd97a346.gif",
     *                  "widthHeightRatio": "1:1.42",
     *                  "url": "",
     *                  "items": "",
     *                  "points": []
     *              }
     *          ],
     *          "author": {
     *              "authorId": 1,
     *              "avatar": "http://img.whatsmode.com/images/1.jpg",
     *              "nickname": "ray"
     *          }
     *      }
     * }
     *
     * @apiError {Number} code      The code of the message
     * @apiError {String} message   The error message content of the response
     * @apiError {Object} payload   The data of the response
     *
     * @apiErrorExample {json} Error 4xx (Response Example):
     * {
     *      "code": 404
     *      "message": "Not Found",
     *      "payload": null
     * }
     *
     */
    @JsonView(View.Summary.class)
    @RequestMapping(value = "/feeds/{feedId}", method = RequestMethod.GET)
    public Response getFeed(@PathVariable Integer feedId) throws Exception{
        Response res = feedService.getFeed(feedId);
        return res;
    }

    /**
     * @api {GET} /v3/feeds/{feedId}/comments?limit=10&offset=0 Get a list of feed comments
     * @apiName listFeedComments
     * @apiGroup Feed
     * @apiVersion 3.0.0
     * @apiDescription This api is used to  get a list of feed comments
     *
     * @apiPermission USER,ANONYMOUS
     *
     * @apiParam {Number} feed-id       The unique feed id
     * @apiParam {Number} [limit=0]     The limit of the comments
     * @apiParam {Number} [offset=0]    The offset of the comments
     *
     * @apiHeader {String} X-Access-Token        The unique auth token of the user
     *
     * @apiExample Example usage:
     * GET "http://api.whatsmode.com/v3/feeds/1/comments?limit=10&offset=0"
     * -h "X-Access-Token:eyJ1c2VySWQiOj.AsInVzZXJuYW1lIj.oiNTMyNzQ5NTUzQH;"
     *
     * @apiSuccess {Number} code                The code of the message
     * @apiSuccess {String} message             The message content of the response
     * @apiSuccess {Object[]} payload           The data of the response
     * @apiSuccess {Number} payload.id          The id of the comment
     * @apiSuccess {String} objectType          The type of the object the comment belongs to
     * @apiSuccess {String} objectId            The id of the object the comment belongs to
     * @apiSuccess {String} payload.content     The content of the comment
     * @apiSuccess {Number} payload.likes       How many people like this comment
     * @apiSuccess {String} payload.avatar      The avatar of the user
     * @apiSuccess {String} payload.name        The name of the user
     * @apiSuccess {Number} payload.userId      The user id
     *
     * @apiSuccessExample {json} Success 200 (Response Example):
     * {
     *      "code": 0
     *      "message": "Operation Successful",
     *      "payload": [
     *          {
     *              "id"; 1,
     *              "userId": 12,
     *              "avatar": "http://img.whatsmode.com/avatar/1.jpg",
     *              "name": "ray",
     *              "objectType": "article",
     *              "objectId": 4,
     *              "content": "Nice article",
     *              "likes": 5,
     *          },
     *          ......
     *      ]
     * }
     *
     * @apiError {Number} code      The code of the message
     * @apiError {String} message   The error message content of the response
     * @apiError {Object} payload   The data of the response
     *
     * @apiErrorExample {json} Error 4xx (Response Example):
     * {
     *      "code": 404
     *      "message": "Not Found",
     *      "payload": null
     * }
     *
     */
    @JsonView(View.Summary.class)
    @RequestMapping(value = "/feeds/{feedId}/comments", method = RequestMethod.GET)
    public Response listFeedComments(@PathVariable Integer feedId,
                                     @RequestParam(value = "limit", required = false,
                                             defaultValue = "10") Integer limit,
                                     @RequestParam(value = "offset", required = false,
                                             defaultValue = "0") Integer offset ){
        Response res = feedService.listFeedComments(feedId, limit, offset);
        return res;
    }

    /**
     * @api {POST} /v3/feeds/{feedId}/comments Create a new comment for some feed
     * @apiName createAFeedComment
     * @apiGroup Feed
     * @apiVersion 3.0.0
     * @apiDescription This api is used to create a new feed comment
     *
     * @apiPermission USER
     *
     * @apiParam {Number} feedId        The unique feed id
     * @apiParam {String} objectType    The type of the feed to comment
     * @apiParam {String} content       The content of the comment
     * @apiParam {Number} [ctime]       When the comment created
     *
     * @apiHeader {String} X-Access-Token    The unique auth token of the user
     *
     * @apiExample Example usage:
     * POST "http://api.whatsmode.com/v3/feeds/1/comments"
     * -h "Authorization:Bearer eyJ1c2VySWQiOj.AsInVzZXJuYW1lIj.oiNTMyNzQ5NTUzQH"
     * -d "
     * {
     *      "objectType": "article",
     *      "content": "Fantastic Dress.",
     *      "ctime": 1234567890123
     *  }
     * "
     *
     * @apiSuccess {Number} code                The code of the message
     * @apiSuccess {String} message             The message content of the response
     * @apiSuccess {Object} payload             The data of the response
     * @apiSuccess {Number} payload.id          The id of the comment
     * @apiSuccess {Number} payload.userId      The user who do the comment
     * @apiSuccess {String} payload.objectType  The feed type
     * @apiSuccess {Number} payload.objectId    The feed id
     * @apiSuccess {String} payload.content     The content of the comment
     * @apiSuccess {Number} payload.likes       How many people like the comment
     * @apiSuccess {Number} payload.ctime       When the comment is created
     *
     * @apiSuccessExample {json} Success 200 (Response Example):
     * {
     *      "code": 0
     *      "message": "Operation Successful",
     *      "payload": {
     *          "id": 23,
     *          "userId": 12,
     *          "objectType": "article",
     *          "objectId": 12,
     *          "content": "Fantastic Dress.",
     *          "likes": 0,
     *          "ctime": 1234567890123
     *      }
     * }
     *
     * @apiError {Number} code      The code of the message
     * @apiError {String} message   The error message content of the response
     * @apiError {Object} payload   The data of the response
     *
     * @apiErrorExample {json} Error 4xx (Response Example):
     * {
     *      "code": 404
     *      "message": "Not Found",
     *      "payload": null
     * }
     *
     */
    @RequestMapping(value = "/feeds/{feedId}/comments", method = RequestMethod.POST)
    public Response createAFeedComment(@PathVariable Integer feedId,
                                       @RequestBody UserComment comment,
                                       @AuthenticationPrincipal AuthenticationUser user) {
        Response res = feedService.createAFeedComment(feedId, user.getUserId(), comment);
        return res;
    }
}
