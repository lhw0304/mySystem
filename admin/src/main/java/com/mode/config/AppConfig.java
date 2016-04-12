package com.mode.config;

/**
 * Global configurations for the application.
 *
 * @author chao
 */
public class AppConfig extends BaseConfig {

    // For currency, only 'usd' is used at this moment.
    public static final String UNIT_USD = "usd";

    // The limit for headlines given.
    public static final Integer HEADLINE_LIMIT = 10;

    // The Android App Version
    public static final String ANDROID = "android";

    // The IOS App Version
    public static final String IOS = "ios";

    // The default country code
    public static final String DEFAULT_LANGUAGE = "en";
    public static final String ALL_LANGUAGE = "all";

    // Default limit numbers
    public static final Integer DEFAULT_ADV_LIMIT = 4;
    public static final Integer DEFAULT_WHATS_HOT_LIMIT = 15;
    public static final Integer DEFAULT_WHATS_NEW_LIMIT = 6;
    public static final Integer DEFAULT_FEEDBACK_LIMIT = 10;
    public static final Integer DEFAULT_FAVOR_LIMIT = 10;
    public static final Integer DEFAULT_NOTIFICATION_LIMIT = 5;
    public static final Integer DEFAULT_STYLIST_LIMIT = 10;
    public static final Integer DEFAULT_COMMENT_LIMIT = 10;
    public static final Integer DEFAULT_RECOMMENDED_HEADLINE_LIMIT = 1;
    public static final Integer DEFAULT_ITEM_LIMIT = 10;
    public static final Integer DEFAULT_RECOMMENDED_ARTICLE_LIMIT = 3;


    // Enabled and unenabled
    public static final Integer ENABLED = 1;
    public static final Integer DISENABLED = 0;

    // Default country
    public static final String DEFAULT_COUNTRY = "US";
    public static final String COUNTRY_RU = "RU";

    // Default limit of the item number
    public static final Integer DEFAULT_ITEM_NUMBER = 50;
    public static final Integer DEFAULT_SIMILIAR_ITEM_NUMBER = 5;

    // Menu list: type, occasion, style
    public static final String TYPE = "type";

    // WhatsNew item number
    public static final Integer WHATSNEW_ITEM_NUMBER = 16;

    // Default offset
    public static final Integer DEFAULT_OFFSET = 0;

    // Adv display page
    public static final String ADV_DISPLAY_PAGE_DISCOVER = "discovery";

    // The type of the task
    public static final String TASK_TYPE_DAILY = "daily";
    public static final String TASK_TYPE_ACCUMULATE = "accumulate";
    public static final String TASK_TYPE_SPECIAL = "special";

    // The tab of the task
    public static final String TASK_TAB_DAILY = "daily";
    public static final String TASK_TAB_SPECIAL = "special";

    // The default type of the feedback
    public static final String DEFAULT_FEEDBACK_TYPE = "suggestion";

    // The default category
    public static final String DEFAULT_CATEGORY = "style";
    public static final String DEFAULT_TAG_NAME = "Jeans";

    // The max credit of the lottery draw
    public static final Integer LOTTERY_MAX_CREDITS = 50;

    // Recommendation title
    public static final String RECOMMENDATION_TITLE = "Recommended For You";
    public static final String RECOMMENDATION_AUTHOR = "mode";
    public static final String RECOMMENDATION_DESCRIPTION = "This set of items are carefully " +
            "recommended for you.";

    // Headline sections
    public static final String HEADLINE_SECTION_STARS = "stars";
    public static final String HEADLINE_SECTION_LOOKBOOK = "lookbook";
    public static final String HEADLINE_SECTION_FASHION_SHOW = "fashion show";

    // MAx sort order
    public static final Integer MAX_SORT_ORDER = 100;

    public static final String LUCK_DRAW_TYPE_ITEM = "item";
    public static final String LUCK_DRAW_TYPE_CREDIT = "credit";

    public static final String USER_PRICE_TYPE_LUCKY_DRAW = "lucky draw";
    public static final String USER_PRICE_TYPE_REDEEM = "redeem";

    public static final String REDEEM_TYPE_ITEM = "item";
    public static final String REDEEM_TYPE_USD = "usd";

    // Type of user credit log
    public static final String USER_CREDIT_TYPE_TASK = "task";

    // Top n items
    public static final Integer TOP_N_ITEMS = 64;

    // Type of liked items
    public static final String LIKED_ITEM_TYPE_MACHINE_RECOMMENDATION = "MR";
    public static final String LIKED_ITEM_TYPE_EDITOR_RECOMMENDATION = "ER";
    public static final String LIKED_ITEM_TYPE_CATEGORY_RECOMMENDATION = "CR";

    // Time
    public static final Long ONE_DAY = 24 * 60 * 60 * 1000l;
    public static final Long THREE_DAYS = 3 * 24 * 60 * 60 * 1000l;

    // follow unfollow
    public static final String TYPE_FOLLOW = "follow";
    public static final String TYPE_UNFOLLOW = "unfollow";

    // List stylist scenario
    public static final String SCENARIO_FIRST_SIGN_UP = "signup";
    public static final String SCENARIO_HOT_STYLIST = "hot";
    public static final String SCENARIO_SEARCH_STYLIST = "search";
    public static final String SCENARIO_RECOMMEND_STYLIST = "recommend";

    // Notification push target
    public static final String NOTIFICATION_PUSH_TARGET_ALL = "all";
    public static final String NOTIFICATION_PUSH_TARGET_PARTIAL = "partial";

    // Notification Types
    public static final String NOTIFICATION_TYPE_NOTICE = "notice";

    // Sort type of the comments
    public static final String COMMENTS_SORT_ORDER_CTIME = "ctime";
    public static final String COMMENTS_SORT_ORDER_LIKES = "likes";

    // Stylist or Brand
    public static final String STYLIST = "stylist";
    public static final String BRAND = "brand";

    // Headline type
    public static final String HEADLINE_ARTICLE = "article";

    // Config types
    public static final String CONFIG_TYPE_USERCONFIG = "userConfig";
}