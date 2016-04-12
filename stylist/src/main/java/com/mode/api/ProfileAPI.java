package com.mode.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mode.config.Response;
import com.mode.service.ProfileService;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * This is the controller for servicing profile restful apis.
 *
 * @author chao
 */
@RestController
@RequestMapping("/v2/profiles")
public class ProfileAPI {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProfileService profileService;

    /**
     * @api {GET} /v2/profiles/users/{userId} Get profile by user id
     * @apiName getProfilebyUserId
     * @apiGroup Profile
     * @apiParam {Number} userId the user id.
     * @apiHeader {String} X-Auth-Token Access token for authentication
     * @apiVersion 2.0.0
     * @apiSuccessExample {json} Success 200:
     * {
     * "message": {
     * "code": 0,
     * "description": "Operation succeeded."
     * },
     * "payload": {
     * "profile": {
     * "profileId": 303,
     * "userId": 300,
     * "nickname": "",
     * "gender": "male",
     * "avatar": "",
     * "birthday": "1970-1-1",
     * "point": 0,
     * "ctime": 1421204885,
     * "utime": 1435557349583,
     * "uuid": "",
     * "level": 0,
     * "rmb": 0,
     * "usd": 0,
     * "isvip": 0,
     * "fbToken": "",
     * "source": "0",
     * "countryCode": "CN",
     * "email": null
     * }
     * }
     * }
     * @apiErrorExample {json} Error:
     * {
     * "message": {
     * "code": 7,
     * "description": "Profile does not exist."
     * },
     * "payload": null
     * }
     */
    @RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
    public Response getProfileByUserId(@PathVariable Integer userId) {
        Response res = profileService.getProfileByUserId(userId);
        logger.info("/v2/profiles/users/{}, {}", userId, res.getMessage());
        return res;
    }

    /**
     * @api {POST} /v2/profiles/avatar/upload Upload user avatar image
     * @apiName uploadAvatar
     * @apiGroup Profile
     * @apiParam {MultiparFile} avatar The avatar image
     * @apiHeader {Number} userId The user id
     * @apiHeader {String} X-Auth-Token Access token for authentication
     * @apiVersion 2.0.0
     * @apiSuccessExample {json} Success 200:
     * { "message":
     * {
     * "code": 0,
     * "description": "Operation succeeded."
     * },
     * "payload": {
     * "imageURL": "whatsmode.oss-us-west-1
     * .aliyuncscom/images/brands/a306c48b2307e33174ce118e117d21c8.jpg"
     * }
     * }
     * @apiErrorExample {json} Error:
     * {
     * "message":
     * {
     * "code": 1,
     * "description":"Operation failed."
     * },
     * "payload": null
     * }
     */
    @RequestMapping(value = "/avatar/upload", method = RequestMethod.POST)
    public Response uploadAvatar(@RequestParam("avatar") MultipartFile avatar,
                                 @RequestHeader("userId") Integer userId) {
        Response res = profileService.uploadAvatar(avatar, userId);
        logger.info("/v2/profiles/avatar/upload, {}, {}", userId, res.getMessage());
        return res;
    }

    /**
     * @api {POST} /v2/profiles/{userId} Edit user profile
     * @apiName updateProfile
     * @apiGroup Profile
     * @apiParam {Number} userId The user id
     * @apiParam {String} [nickname] The nickname of the user
     * @apiParam {String} [description] The self description of the user
     * @apiParam {String} [paypal] The paypal of the user
     * @apiHeader {String} X-Auth-Token Access token for authentication
     * @apiVersion 2.0.0
     * @apiSuccessExample {json} Success 200:
     * { "message":
     * {
     * "code": 0,
     * "description": "Operation succeeded."
     * },
     * "payload": null
     * }
     * @apiErrorExample {json} Error:
     * {
     * "message":
     * {
     * "code": 1,
     * "description":"Operation failed."
     * },
     * "payload": null
     * }
     */
    @RequestMapping(value = "/{userId}", method = RequestMethod.POST)
    public Response updateProfile(@PathVariable("userId") Integer userId,
                                  MultipartHttpServletRequest mRequest) {
        Response res = profileService.updateProfile(userId, mRequest);
        logger.info("/v2/profiles/{}?nickname={}&description={}, {}, {}, {}", userId,
                res.getMessage());
        return res;
    }
}