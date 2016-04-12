package com.mode.api;

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
import com.mode.base.Message;
import com.mode.base.Response;
import com.mode.dao.AccountDAO;
import com.mode.dao.ProfileDAO;
import com.mode.dto.Registry;
import com.mode.entity.Account;
import com.mode.entity.Profile;
import com.mode.entity.View;
import com.mode.exception.ModeException;
import com.mode.security.AuthenticationUser;
import com.mode.service.UserService;

/**
 * Created by Lei on 3/14/16.
 */
@RestController
@RequestMapping("/v3")
public class UserAPI {

    @Autowired
    private UserService userService;

    /**
     * @api {POST} /v3/login Login
     * @apiName login
     * @apiGroup User
     * @apiVersion 3.0.0
     * @apiDescription This API is used for user to login via his/her username and password
     *
     * @apiPermission All
     *
     * @apiParam {String} username                  User's unique username
     * @apiParam {String} password                  User's password.The password is encrypted by md5 algorithm
     * @apiParam {String} source                    The source where the user comes from
     * @apiParam {Object} userLoginLog                The device info of the user
     * @apiParam {String} userLoginLog.deviceType     The device type
     * @apiParam {String} userLoginLog.deviceToken    The device token
     * @apiParam {String} userLoginLog.uuid           The uuid of the device the user use
     * @apiParam {String} userLoginLog.phoneNumber    The phone number of the device
     * @apiParam {String} userLoginLog.imsi           The imsi of the device
     * @apiParam {String} userLoginLog.macAddress     The mac address of the device
     * @apiParam {String} userLoginLog.pixel          The pixel of the device
     * @apiParam {String} userLoginLog.model          The model of the device
     * @apiParam {String} userLoginLog.simserialnum   The simserialnum of the device
     * @apiParam {String} userLoginLog.action         'login' or 'signup'
     * @apiParam {String} userLoginLog.systemVersion  The system version of the device
     * @apiParam {String} userLoginLog.appVersion     The version of the app installed in the device
     * @apiParam {Number} userLoginLog.latitude       The latitude of the device
     * @apiParam {Number} userLoginLog.longitude      The longitude of the device
     * @apiParam {String} userLoginLog.language       The language of the device
     * @apiParam {String} userLoginLog.country        The country of the device
     * @apiParam {String} userLoginLog.timeZone       The time zone of the device
     *
     * @apiExample Example usage:
     * POST
     * http://api.whatsmode.com/v3/login
     * -d "
     * {
     *      "username": "ray",
     *      "password": "8b769edc39e348930f49d3733f92e3d4",
     *      "userLoginLog": {
     *          "deviceType": "Iphone",
     *          "deviceToken": "eyJ1c2VySWQiOjAsInVzZXJuYW1lIjoiNTMyNzQ5NTUzQH",
     *          "uuid": "eyJ1c2VySWQiOjsInVzZXJuYW1lIjNTMyNzQ5NTUzQH",
     *          "phoneNumber": "1367548972839",
     *          "imsi": "eyJ1c2VySWQiOjsInVzZXJuYW1lIjNTMyNzQ5NTUzQH",
     *          "osType": "IOS",
     *          "macAddress": "MC",
     *          "pixel": "600 * 800",
     *          "model": "SUNSUMG",
     *          "simserialnum": "12342123",
     *          "action": "login",
     *          "systemVersion": "IOS 9.0",
     *          "appVersion": "3.1.0",
     *          "latitude": "12.9872",
     *          "longitude": "-45.12",
     *          "language": "en",
     *          "country": "US",
     *          "timeZone": "8.0"
     *      }
     *  }
     * "
     *
     * @apiSuccess {Number} code                The code of the message
     * @apiSuccess {String} message             The message content of the response
     * @apiSuccess {Object} payload             The data of the response
     * @apiSuccess {String} payload.accessToken The token of the user for accessing the api
     * @apiSuccessExample {json} Success 200 (Response Example):
     * {
     *      "code": 0
     *      "message": "Operation Successful",
     *      "payload": {
     *          "accessToken": "eyJ1c2VySWQiOj.AsInVzZXJuYW1lIj.oiNTMyNzQ5NTUzQH"
     *      }
     * }
     *
     * @apiError {Number} code      The code of the message
     * @apiError {String} message   The error message content of the response
     * @apiError {Object} payload   The data of the response
     *
     *
     * @apiErrorExample {json} Error 4xx (Response Example):
     * {
     *      "code": 404,
     *      "message": "Not Found",
     *      "payload": null
     * }
     *
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response login(@RequestBody Registry registry) {
        Response res = userService.login(registry);
        return res;
    }

    /**
     * @api {POST} /v3/signup Sign up
     * @apiName signup
     * @apiGroup User
     * @apiVersion 3.0.0
     * @apiDescription This API is used for user to signup via his/her username and password
     *
     * @apiPermission All
     *
     * @apiParam {String} username User's           unique username
     * @apiParam {String} password                  User's password.The password is encrypted by md5 algorithm
     * @apiParam {Object} userLoginLog                The device info of the user
     * @apiParam {String} userLoginLog.deviceType     The device type
     * @apiParam {String} userLoginLog.deviceToken    The device token
     * @apiParam {String} userLoginLog.uuid           The uuid of the device the user use
     * @apiParam {String} userLoginLog.phoneNumber    The phone number of the device
     * @apiParam {String} userLoginLog.imsi           The imsi of the device
     * @apiParam {String} userLoginLog.macAddress     The mac address of the device
     * @apiParam {String} userLoginLog.pixel          The pixel of the device
     * @apiParam {String} userLoginLog.model          The model of the device
     * @apiParam {String} userLoginLog.simserialnum   The simserialnum of the device
     * @apiParam {String} userLoginLog.action         'login' or 'signup'
     * @apiParam {String} userLoginLog.systemVersion  The system version of the device
     * @apiParam {String} userLoginLog.appVersion     The version of the app installed in the device
     * @apiParam {Number} userLoginLog.latitude       The latitude of the device
     * @apiParam {Number} userLoginLog.longitude      The longitude of the device
     * @apiParam {String} userLoginLog.language       The language of the device
     * @apiParam {String} userLoginLog.country        The country of the device
     * @apiParam {String} userLoginLog.timeZone       The time zone of the device
     *
     * @apiExample Example usage:
     * POST
     * http://api.whatsmode.com/v3/signup
     * -d "
     * {
     *      "username": "ray",
     *      "password": "8b769edc39e348930f49d3733f92e3d4",
     *      "userLoginLog": {
     *          "deviceType": "Iphone",
     *          "deviceToken": "eyJ1c2VySWQiOjAsInVzZXJuYW1lIjoiNTMyNzQ5NTUzQH",
     *          "uuid": "eyJ1c2VySWQiOjsInVzZXJuYW1lIjNTMyNzQ5NTUzQH",
     *          "phoneNumber": "1367548972839",
     *          "imsi": "eyJ1c2VySWQiOjsInVzZXJuYW1lIjNTMyNzQ5NTUzQH",
     *          "osType": "IOS",
     *          "macAddress": "MC",
     *          "pixel": "600 * 800",
     *          "model": "SUNSUMG",
     *          "simserialnum": "12342123",
     *          "action": "login",
     *          "systemVersion": "IOS 9.0",
     *          "appVersion": "3.1.0",
     *          "latitude": "12.9872",
     *          "longitude": "-45.12",
     *          "language": "en",
     *          "country": "US",
     *          "timeZone": "8.0"
     *      }
     *  }
     * "
     *
     * @apiSuccess {Number} code                The code of the message
     * @apiSuccess {String} message             The message content of the response
     * @apiSuccess {Object} payload             The data of the response
     * @apiSuccess {String} payload.accessToken   The token of the user for accessing the api
     * @apiSuccessExample {json} Success 200 (Response Example):
     * {
     *      "code": 0
     *      "message": "Operation Successful",
     *      "payload": {
     *          "accessToken": "eyJ1c2VySWQiOj.AsInVzZXJuYW1lIj.oiNTMyNzQ5NTUzQH"
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
     */
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public Response signup(@RequestBody Registry registry) {
        Response res = userService.signup(registry);
        return res;
    }

    /**
     * @api {GET} /v3/users/self Get the info of the user self
     * @apiName userSelf
     * @apiGroup User
     * @apiVersion 3.0.0
     * @apiDescription This api will return the info of the user self
     *
     * @apiPermission USER
     *
     * @apiHeader {String} X-Access-Token     The unique access token of the user
     *
     * @apiExample Example usage:
     * GET "http://api.whatsmode.com/v3/users/self"
     * -h "Authorization:Bearer eyJ1c2VySWQiOj.AsInVzZXJuYW1lIj.oiNTMyNzQ5NTUzQH"
     *
     * @apiSuccess {Number} code                The code of the message
     * @apiSuccess {String} message             The message content of the response
     * @apiSuccess {Object} payload             The data of the response
     * @apiSuccess {Number} payload.userId      The unique user id
     * @apiSuccess {String} payload.username    The username of the user
     * @apiSuccess {String} payload.email       The email of the user
     * @apiSuccess {String} payload.gender      The gender of the user
     * @apiSuccess {String} payload.nickname    The nickname of the user
     * @apiSuccess {String} payload.avatar      The avatar of the user
     * @apiSuccess {String} payload.coverImage  The cover image of the user
     * @apiSuccess {String} payload.description The self description of the user
     * @apiSuccess {String} payload.countryCode The country code of the user
     * @apiSuccess {String} payload.source      Where the user comes from
     * @apiSuccess {Number} payload.credits     How many credits the user has
     * @apiSuccess {Number} payload.ctime       When the user created the account
     * @apiSuccess {Number} payload.usd         The usd number
     * @apiSuccess {Number} payload.saves      The number of feeds the user saved
     * @apiSuccess {Number} payload.followers   The number of followers the user has
     * @apiSuccess {Number} payload.articles    The number of articles the user posted
     *
     * @apiSuccessExample {json} Success 200 (Response Example):
     * {
     *      "code": 0
     *      "message": "Operation Successful",
     *      "payload": {
     *          "username" : "ray",
     *          "email": "ray@whatsmode.com",
     *          "gender": "female",
     *          "nickname": "Alice",
     *          "avatar": "http://img.whatsmode.com/123.png",
     *          "covreImage": "http://img.whatsmode.com/123.png",
     *          "description": "self description",
     *          "countryCode": "US",
     *          "source": "jolly",
     *          "credits": 34,
     *          "ctime": 1234567890123,
     *          "usd": 178,
     *          "saves": 12,
     *          "followers": 13,
     *          "articles": 14
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
    @JsonView(View.Detail.class)
    @RequestMapping(value = "/users/self", method = RequestMethod.GET)
    public Response userSelf(@AuthenticationPrincipal AuthenticationUser user){

        Response res = userService.userSelf(user.getUserId());
        return res;
    }

    /**
     * @api {GET} /v3/users/self/feeds List feeds for the user
     * @apiName userSelfFeeds
     * @apiGroup User
     * @apiVersion 3.0.0
     * @apiDescription This API is used to get the feeds of the user self
     *
     * @apiPermission USER
     *
     * @apiParam {Number} [limit=10]       The limit number of the feeds
     * @apiParam {Number} [offset=0]       The offset of the feeds response
     * @apiParam {String} [country=US]     The country code of the results
     *
     * @apiHeader {String} X-Access-Token        The unique auth token of the user
     *
     * @apiExample Example API usage:
     * GET "http://api.whatsmode.com/v3/users/self/feeds"
     * -h "X-Access-Token:eyJ1c2VySWQiOj.AsInVzZXJuYW1lIj.oiNTMyNzQ5NTUzQH;"
     *
     * @apiSuccess {Number} code                    The code of the message
     * @apiSuccess {String} message                 The message content of the response
     * @apiSuccess {Object[]} payload                 The data of the response
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
     *      "payload": [
     *          {
     *              "id": 1,
     *              "authorId": 13240,
     *              "ctime": 1234567890123,
     *              "utime": 1234567890123,
     *              "coverImage": "http://img.cdn.whatsmodecom/images/image_4.png",
     *              "title": "100% off, Come on",
     *              "description": "Just Today, you can get everything for free.",
     *              "content": {
     *                  "coverImage": "http://...jpg",
     *                  "author": "Ray",
     *                  "title": "Collection",
     *                  "content": [
     *                      {
     *                          "type": "text",
     *                          "content": "Nice article."
     *                      },
     *                  ]
     *              },
     *              "author": {
     *                  "authorId": 1,
     *                  "avatar": "http://img.whatsmode.com/images/1.jpg",
     *                  "nickname": "ray"
     *              }
     *          }
     *      ]
     * }
     *
     * @apiError {Number} code      The code of the message
     * @apiError {String} message   The error message content of the response
     * @apiError {Object} payload   The data of the response
     * @apiErrorExample {json} Error 4xx (Response Example):
     * {
     *      "code": 404
     *      "message": "Not Found",
     *      "payload": null
     * }
     *
     */
    @RequestMapping(value = "/users/self/feeds", method = RequestMethod.GET)
    public Response userSelfFeeds(@AuthenticationPrincipal AuthenticationUser user,
                                  @RequestParam(value = "limit", required = false, defaultValue =
                                          "10") Integer limit,
                                  @RequestParam(value = "offset", required = false, defaultValue
                                          = "0") Integer offset,
                                  @RequestParam(value = "country", required = false, defaultValue
                                          = "US") String country) {

        Response res = userService.userSelfFeeds(user.getUserId(), limit, offset, country);
        return res;
    }

    /**
     * @api {GET} /v3/users/self/saves List the what the user saved
     * @apiName userSelfSaves
     * @apiGroup User
     * @apiVersion 3.0.0
     * @apiDescription This Api is used to list what the user saved in the app
     *
     * @apiPermission USER
     *
     * @apiHeader {String} X-Access-Token   The unique auth token of the user
     * @apiParam {Number} [limit=10]        The limit number of the feeds
     * @apiParam {Number} [offset=0]        The offset of the feeds response
     * @apiParam {String} [country=US]      The country code of the results
     * @apiParam {String} [type=null]       The type of the objects the user saved
     *
     * @apiExample Example usage:
     * GET "http://api.whatsmode.com/v3/users/favors"
     * -h "X-Access-Token:eyJ1c2VySWQiOj.AsInVzZXJuYW1lIj.oiNTMyNzQ5NTUzQH;"
     *
     * @apiSuccess {Number} code                The code of the message
     * @apiSuccess {String} message             The message content of the response
     * @apiSuccess {Object} payload             The data of the response
     *
     * @apiSuccessExample {json} Success 200 (Response Example):
     * {
     *      "code": 0
     *      "message": "Operation Successful",
     *      "payload": {
     *
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
    @RequestMapping(value = "/users/self/saves", method = RequestMethod.GET)
    public Response userSelfSaves(@AuthenticationPrincipal AuthenticationUser user,
                                  @RequestParam(value = "type", required = false) String type,
                                  @RequestParam(value = "country", required = false, defaultValue
                                          = "US") String country,
                                  @RequestParam(value = "limit", required = false, defaultValue =
                                          "10") Integer limit,
                                  @RequestParam(value = "offset", required = false, defaultValue
                                          = "0") Integer offset) {
        Response res = userService.userSelfSaves(user.getUserId(), type, country, limit, offset);
        return res;
    }

    /**
     * @api {GET} /v3/users/self/engagements List what the user liked
     * @apiName userSelfEngagements
     * @apiGroup User
     * @apiVersion 3.0.0
     * @apiDescription This Api is used to list what the user liked in app
     *
     * @apiPermission USER
     *
     * @apiHeader {String} X-Access-Token        The unique auth token of the user
     *
     * @apiExample Example usage:
     * GET "http://api.whatsmode.com/v3/users/likes"
     * -h "X-Access-Token:eyJ1c2VySWQiOj.AsInVzZXJuYW1lIj.oiNTMyNzQ5NTUzQH;"
     *
     * @apiSuccess {Number} code                The code of the message
     * @apiSuccess {String} message             The message content of the response
     * @apiSuccess {Object[]} payload           The data of the response
     * @apiSuccess {String} payload.objectType  The type of the object the user liked
     * @apiSuccess {Number} payload.objectId    The id of the object the user liked
     *
     * @apiSuccessExample {json} Success 200 (Response Example):
     * {
     *      "code": 0
     *      "message": "Operation Successful",
     *      "payload": [
     *          {
     *              "objectType": "article",
     *              "objectId": 1
     *          },
     *          {
     *              "objectType": "item",
     *              "objectId": 2
     *          }
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
    @RequestMapping(value = "/users/self/engagements", method = RequestMethod.GET)
    public Response userSelfEngagements(@AuthenticationPrincipal AuthenticationUser user){
        Response res = userService.userSelfEngagements(user.getUserId());
        return res;
    }

    /**
     * @api {GET} /v3/users/self/tasks List tasks for the user
     * @apiName userSelfTasks
     * @apiGroup User
     * @apiVersion 3.0.0
     * @apiDescription This Api is used to list tasks for the user
     *
     * @apiPermission USER
     *
     * @apiHeader {String} X-Access-Token   The unique auth token of the user
     * @apiParam {Number} [limit=10]        The limit number of the feeds
     * @apiParam {Number} [offset=0]        The offset of the feeds response
     * @apiParam {String} [country=US]      The country code of the results
     *
     * @apiExample Example usage:
     * curl -i "http://api.whatsmode.com/v3/users/tasks"
     * -h "
     * Authorization:Bearer eyJ1c2VySWQiOj.AsInVzZXJuYW1lIj.oiNTMyNzQ5NTUzQH;
     * Filter: {
     *     "limit" : 10,
     *     "offset": 0,
     *     "ordering": "DESC",
     *     "orderBy": "id",
     *     "countryCode" : "US",
     * }
     * "
     *
     * @apiSuccess {Number} code                The code of the message
     * @apiSuccess {String} message             The message content of the response
     * @apiSuccess {Object} payload             The data of the response
     *
     * @apiSuccessExample {json} Success 200 (Response Example):
     * {
     *      "code": 0
     *      "message": "Operation Successful",
     *      "payload": {
     *
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
    @RequestMapping(value = "/users/self/tasks", method = RequestMethod.GET)
    public Response userSelfTasks(@AuthenticationPrincipal AuthenticationUser user){
        Response res = userService.userSelfTasks(user.getUserId());
        return res;
    }

    /**
     * @api {GET} /v3/users/self/feedbacks List feedbacks for the user
     * @apiName userSelfFeedbacks
     * @apiGroup User
     * @apiVersion 3.0.0
     * @apiDescription This Api is used to list feedbacks for the user
     *
     * @apiPermission USER
     *
     * @apiHeader {String} X-Access-Token   The unique auth token of the user
     * @apiParam {Number} [limit=10]        The limit number of the feeds
     * @apiParam {Number} [offset=0]        The offset of the feeds response
     * @apiParam {String} [type=null]       The type of the feedback
     *
     * @apiExample Example API usage:
     * curl -i "http://api.whatsmode.com/v3/users/feedbacks"
     * -h "
     * Authorization:Bearer eyJ1c2VySWQiOj.AsInVzZXJuYW1lIj.oiNTMyNzQ5NTUzQH;
     * Filter: {
     *     "limit" : 10,
     *     "offset": 0,
     *     "ordering": "DESC",
     *     "orderBy": "id",
     *     "countryCode" : "US",
     * }
     * "
     *
     * @apiSuccess {Number} code                The code of the message
     * @apiSuccess {String} message             The message content of the response
     * @apiSuccess {Object} payload             The data of the response
     *
     * @apiSuccessExample {json} Success 200 (Response Example):
     * {
     *      "code": 0
     *      "message": "Operation Successful",
     *      "payload": {
     *
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
    @RequestMapping(value = "/users/self/feedbacks", method = RequestMethod.GET)
    public Response userSelfFeedbacks(@AuthenticationPrincipal AuthenticationUser user,
                                      @RequestParam(value = "limit", required = false) Integer limit,
                                      @RequestParam(value = "offset", required = false) Integer
                                                  offset,
                                      @RequestParam(value = "country", required = false) String
                                                  country){
        Response res = userService.userSelfFeedbacks(user.getUserId(), limit, offset, country);
        return res;
    }

    /**
     * @api {GET} /v3/users/self/notifications List notifications for the user
     * @apiName userSelfNotifications
     * @apiGroup User
     * @apiVersion 3.0.0
     * @apiDescription This Api is used to list notifications for the user
     *
     * @apiPermission USER
     *
     * @apiHeader {String} X-Access-Token   The unique auth token of the user
     * @apiParam {Number} [limit=10]        The limit number of the feeds
     * @apiParam {Number} [offset=0]        The offset of the feeds response
     * @apiParam {String} [country=US]      The country code of the results
     *
     * @apiExample Example usage:
     * GET "http://api.whatsmode.com/v3/users/notifications"
     * -h "X-Access-Token :eyJ1c2VySWQiOj.AsInVzZXJuYW1lIj.oiNTMyNzQ5NTUzQH;"
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
     *
     * @apiSuccessExample {json} Success 200 (Response):
     * {
     *      "code": 0
     *      "message": "Operation Successful",
     *      "payload": [
     *          {
     *              "id": 1,
     *              "authorId": 13240,
     *              "ctime": 1234567890123,
     *              "utime": 1234567890123,
     *              "coverImage": "http://img.cdn.whatsmodecom/images/image_4.png",
     *              "title": "100% off, Come on",
     *              "description": "Just Today, you can get everything for free.",
     *              "content": {
     *                  "coverImage": "http://...jpg",
     *                  "author": "Ray",
     *                  "title": "Collection",
     *                  "content": [
     *                      {
     *                          "type": "text",
     *                          "content": "Nice article."
     *                      },
     *                  ]
     *              },
     *              "author": {
     *                  "authorId": 1,
     *                  "avatar": "http://img.whatsmode.com/images/1.jpg",
     *                  "nickname": "ray"
     *              }
     *          }
     *      ]
     * }
     *
     * @apiError {Number} code      The code of the message
     * @apiError {String} message   The error message content of the response
     * @apiError {Object} payload   The data of the response
     *
     * @apiErrorExample {json} Error 4xx (Response):
     * {
     *      "code": 404
     *      "message": "Not Found",
     *      "payload": null
     * }
     *
     */
    @RequestMapping(value = "/users/self/notifications", method = RequestMethod.GET)
    public Response userSelfNotifications(@AuthenticationPrincipal AuthenticationUser user,
                                          @RequestParam(value = "limit", required = false,
                                                  defaultValue = "10") Integer limit,
                                          @RequestParam(value = "offset", required = false,
                                                  defaultValue = "0") Integer offset,
                                          @RequestParam(value = "country", required = false,
                                                  defaultValue = "US") String country){
        Response res = userService.userSelfNotifications(user.getUserId(), limit, offset, country);
        return res;
    }

    /**
     * @api {GET} /v3/users/{user-id} Get info of some user
     * @apiName getUserInfo
     * @apiGroup User
     * @apiVersion 3.0.0
     * @apiDescription This Api is used to get the info of some user
     *
     * @apiPermission USER
     *
     * @apiHeader {String} X-Access-Token    The unique auth token of the user
     *
     * @apiExample Example usage:
     * GET "http://api.whatsmode.com/v3/users/1"
     * -h "X-Access-Token:eyJ1c2VySWQiOj.AsInVzZXJuYW1lIj.oiNTMyNzQ5NTUzQH"
     *
     * @apiSuccess {Number} code                The code of the message
     * @apiSuccess {String} message             The message content of the response
     * @apiSuccess {Object} payload             The data of the response
     * @apiSuccess {Number} payload.userId      The unique user id
     * @apiSuccess {String} payload.username    The username of the user
     * @apiSuccess {String} payload.email       The email of the user
     * @apiSuccess {String} payload.gender      The gender of the user
     * @apiSuccess {String} payload.nickname    The nickname of the user
     * @apiSuccess {String} payload.avatar      The avatar of the user
     * @apiSuccess {String} payload.coverImage  The cover image of the user
     * @apiSuccess {String} payload.description The self description of the user
     * @apiSuccess {String} payload.countryCode The country code of the user
     * @apiSuccess {String} payload.source      Where the user comes from
     * @apiSuccess {Number} payload.credits     How many credits the user has
     * @apiSuccess {Number} payload.ctime       When the user created the account
     * @apiSuccess {Number} payload.usd         The usd number
     * @apiSuccess {Number} payload.saves      The number of feeds the user saved
     * @apiSuccess {Number} payload.followers   The number of followers the user has
     * @apiSuccess {Number} payload.articles    The number of articles the user posted
     *
     * @apiSuccessExample {json} Success 200 (Response):
     * {
     *      "code": 0
     *      "message": "Operation Successful",
     *      "payload": {
     *          "username" : "ray",
     *          "email": "ray@whatsmode.com",
     *          "gender": "female",
     *          "nickname": "Alice",
     *          "avatar": "http://img.whatsmode.com/123.png",
     *          "covreImage": "http://img.whatsmode.com/123.png",
     *          "description": "self description",
     *          "countryCode": "US",
     *          "source": "jolly",
     *          "credits": 34,
     *          "ctime": 1234567890123,
     *          "usd": 178,
     *          "saves": 12,
     *          "followers": 13,
     *          "articles": 14
     *      }
     * }
     *
     * @apiError {Number} code      The code of the message
     * @apiError {String} message   The error message content of the response
     * @apiError {Object} payload   The data of the response
     *
     * @apiErrorExample {json} Error 4xx (Response):
     * {
     *      "code": 404
     *      "message": "Not Found",
     *      "payload": null
     * }
     *
     */
    @RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
    public Response getUserInfo(@PathVariable int userId){
        // the user-id must in the stylist or merchant role
        Response res = userService.getUserInfo(userId);
        return res;
    }

    /**
     * @api {GET} /v3/users List users' info
     * @apiName listUserInfo
     * @apiGroup User
     * @apiVersion 3.0.0
     * @apiDescription This Api is used to list users' info
     *
     * @apiPermission USER
     *
     * @apiHeader {String} X-Auth-Token The unique auth token of the user
     * @apiParam {Number} [limit=null]  The limit number of the feeds
     * @apiParam {Number} [offset=0]    The offset of the feeds response
     * @apiParam {String} [country=US]  The country code of the results
     *
     * @apiExample Example usage:
     * GET "http://api.whatsmode.com/v3/users"
     * -h "X-Access-Token:eyJ1c2VySWQiOj.AsInVzZXJuYW1lIj.oiNTMyNzQ5NTUzQH;"
     *
     * @apiSuccess {Number} code                The code of the message
     * @apiSuccess {String} message             The message content of the response
     * @apiSuccess {Object[]} payload             The data of the response
     * @apiSuccess {Number} payload.userId      The unique user id
     * @apiSuccess {String} payload.username    The username of the user
     * @apiSuccess {String} payload.email       The email of the user
     * @apiSuccess {String} payload.gender      The gender of the user
     * @apiSuccess {String} payload.nickname    The nickname of the user
     * @apiSuccess {String} payload.avatar      The avatar of the user
     * @apiSuccess {String} payload.coverImage  The cover image of the user
     * @apiSuccess {String} payload.description The self description of the user
     * @apiSuccess {String} payload.countryCode The country code of the user
     * @apiSuccess {String} payload.source      Where the user comes from
     * @apiSuccess {Number} payload.credits     How many credits the user has
     * @apiSuccess {Number} payload.ctime       When the user created the account
     * @apiSuccess {Number} payload.usd         The usd number
     * @apiSuccess {Number} payload.saves      The number of feeds the user saved
     * @apiSuccess {Number} payload.followers   The number of followers the user has
     * @apiSuccess {Number} payload.articles    The number of articles the user posted
     *
     * @apiSuccessExample {json} Success 200 (Response):
     * {
     *      "code": 0
     *      "message": "Operation Successful",
     *      "payload": [
     *          {
     *              "username" : "ray",
     *              "email": "ray@whatsmode.com",
     *              "gender": "female",
     *              "nickname": "Alice",
     *              "avatar": "http://img.whatsmode.com/123.png",
     *              "covreImage": "http://img.whatsmode.com/123.png",
     *              "description": "self description",
     *              "countryCode": "US",
     *              "source": "jolly",
     *              "credits": 34,
     *              "ctime": 1234567890123,
     *              "usd": 178,
     *              "saves": 12,
     *              "followers": 13,
     *              "articles": 14
     *          },
     *          .....
     *      ]
     * }
     *
     * @apiError {Number} code      The code of the message
     * @apiError {String} message   The error message content of the response
     * @apiError {Object} payload   The data of the response
     *
     * @apiErrorExample {json} Error 4xx (Response):
     * {
     *      "code": 404
     *      "message": "Not Found",
     *      "payload": null
     * }
     *
     */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public Response listUserInfo(@RequestParam(value = "limit", required = false) Integer limit,
                                 @RequestParam(value = "offset", required = false, defaultValue =
                                         "0") Integer offset,
                                 @RequestParam(value = "country", required = false, defaultValue
                                         = "US") String country) {
        // These user info is stylists or brands or merchants
        // Filter the user info. Remove password, token or other sensitive info.
        Response res = userService.listUserInfo(limit, offset, country);
        return res;
    }
}
