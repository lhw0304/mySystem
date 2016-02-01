package com.mode.config;

import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Base configurations for the backend systems.
 *
 * @author chao
 *
 */
public class BaseConfig {

    public static final Integer ACCOUNT_NORMAL = 0;

    public static final Integer ACCOUNT_LOCKED = 1;

    // Currently we have the following roles defined for the backend system users.
    public static final String ROLE_USER = "USER";
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_MERCHANT = "MERCHANT";
    public static final String ROLE_STYLIST = "STYLIST";
    public static final String ROLE_MODE = "MODE";
    public static final String ROLE_BRAND = "BRAND";

    // The default expire window for an access token. User will need to input the login username
    // and credentials again to refresh the token when it expires.
    public static final Long EXPIRE_IN_TWO_WEEKS = 1000l * 60 * 60 * 24 * 100;


    // For uploading images only, the folders of different image types.
    public static final String FOLDER_ITEM = "items";
    public static final String FOLDER_BRAND = "brands";
    public static final String FOLDER_COLLECTION = "collections";
    public static final String FOLDER_ARTICLE = "articles";
    public static final String FOLDER_HEADLINE = "headlines";
    public static final String FOLDER_MODELOOK = "modelooks";
    public static final String FOLDER_AVATAR = "avatars";
    public static final String FOLDER_STYLIST_AVATAR = "stylists";
    public static final String FOLDER_COVER_IMAGE = "coverImage";

    public static final String FOLDER_REDEEM = "redeems";
    public static final String FOLDER_LUCKYDRAW = "luckydraws";
    public static final String FOLDER_NOTIFICATION = "notifications";
    public static final String FOLDER_ADV = "advs";

    // For 3x3 grid image, there're 9 items
    public static final Integer COLLECTION_ITEMS = 9;

    // The number of runway items
    public static final Integer RUNWAY_ITEMS = 16;

    // The number of most similar items
    public static final Integer SIMILAR_ITEMS = 3;

    public static final String LOG_TYPE_APPSFLYER = "appsflyer";
    public static final String LOG_TYPE_USER_LOGIN = "userLogin";
    public static final String LOG_TYPE_USER_PROD_LINK = "userProdLink";
    public static final String LOG_TYPE_USER_ACTION = "userAction";

    // One-off task: only effective when first actived or applied.
    public static final String RULE_TYPE_ONE_OFF = "O";
    // Repeat task: given every XXX times, we add bonus for them.
    public static final String RULE_TYPE_REPEAT = "R";
    // Continuous: open-ended task that has many different levels/thresholds.
    public static final String RULE_TYPE_CONTINUOUS = "C";

    // Error message uri
    public static final String ERROR_URL_TEMPLATE = "v2/error";
    public static final String ERROR_1001 = "/v2/error?code=1001";
    public static final String ERROR_1002 = "/v2/error?code=1002";
    public static final String ERROR_401 = "/v2/error?code=401";

    // Key encryption algorithm
    public static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;
}
