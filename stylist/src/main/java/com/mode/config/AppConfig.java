package com.mode.config;

/**
 * Created by zhaoweiwei on 15/11/17.
 */
public class AppConfig extends BaseConfig {

    public static final String CONFIG_STYLIST_COMMISSION = "stylistCommission";

    public static final String CONFIG_STYLIST_COMMISSION_TYPE_TOTAL = "total";

    public static final String CONFIG_STYLIST_COMMISSION_TYPE_DISTINCT = "distinct";

    public static final String STYLIST_COMMISSION_TYPE_WITHDRAW = "withdraw";

    public static final String STYLIST_COMMISSION_TYPE_SETTLEMENT = "settlement";

    public static final Integer STYLIST_COMMISSION_STATS_DEFAULT_COUNT = 30;

    // Sort type of the comments
    public static final String COMMENTS_SORT_ORDER_CTIME = "ctime";
    public static final String COMMENTS_SORT_ORDER_LIKES = "likes";

    public static final Integer DEFAULT_LIMIT = 10;

    // Default offset
    public static final Integer DEFAULT_OFFSET = 0;
}
