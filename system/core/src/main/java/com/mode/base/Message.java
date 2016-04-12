package com.mode.base;

/**
 * A global response message definition. To support globalization, please note that the message code
 * is the only identifier for mapping the English description to its corresponding
 * descriptions in other languages. Descriptions in non-English languages are defined in the
 * database md_config table.
 *
 * Created by Lei on 3/23/16.
 */
public class Message {

    /* Message code from 0 to 100 are reserved for Mode applications. */
    public static final Message SUCCESS = new Message(0, "Operation succeeded");
    public static final Message FAILURE = new Message(1, "Operation failed");
    public static final Message DATABASE_ERROR = new Message(2, "A database error has occurred");
    public static final Message DUPLICATE_USER = new Message(3, "This user already exists");
    public static final Message USER_NOT_EXIST = new Message(4, "This user does not exist");
    public static final Message ERROR_CREATE_ACCOUNT = new Message(5, "An error occurred while creating account");
    public static final Message NOT_ENOUGH_CASH = new Message(6, "Not enough cash");
    public static final Message PROFILE_NOT_EXIST = new Message(7, "Profile does not exist");
    public static final Message BRAND_NOT_EXIST = new Message(8, "Brand does not exist");
    public static final Message USER_BRAND_NOT_EXIST = new Message(9, "You don't have any favorite brands");
    public static final Message COLLECTION_NOT_EXIST = new Message(10, "You don't have any favorite items");
    public static final Message RUNWAY_NOT_EXIST = new Message(11, "You don't have any runways");
    public static final Message RUNWAY_ITEMS_NOT_EXIST = new Message(12, "No items for this runway");
    public static final Message NO_AVAILABLE_RUNWAY_ITEMS = new Message(13, "No available runway items. Try later or choose a different category");
    public static final Message ITEM_NOT_EXIST = new Message(14, "Item does not exist");
    public static final Message TRANSACTION_PENDING = new Message(15, "Please wait until your previous transaction finishes");
    public static final Message INVALID_COLLECTION_SIZE = new Message(16, "Invalid collection size");
    public static final Message ARTICLE_NOT_EXIST = new Message(17, "Article does not exist");
    public static final Message NO_MORE_HEADLINE = new Message(18, "No more feeds right now");
    public static final Message NO_AVAILABLE_MODELOOK = new Message(19,"No available modelooks");
    public static final Message DUPLICATE_MODELOOK = new Message(20, "This modelook already exists");
    public static final Message BRAND_HAS_BEEN_FOLLOWED = new Message(21, "You have followed this brand already");
    public static final Message ACCOUNT_IS_LOCKED = new Message(22, "Your account is locked");
    public static final Message HAS_BEEN_CHECKED_IN = new Message(23, "You have been checked in today");
    public static final Message CAN_CHECK_IN = new Message(24, "You can check in today");
    public static final Message HAS_BEEN_SAVED = new Message(24, "This object has been saved");
    public static final Message LOG_FAILURE = new Message(25, "Log failed");
    public static final Message NO_MORE_ADV = new Message(26, "No more advertisements");
    public static final Message NO_MORE_COLLECTION = new Message(27, "No more collections");
    public static final Message NO_MORE_NOTIFICATION = new Message(28, "No more notifications");
    public static final Message NO_MORE_FAVORS = new Message(29, "No more favors");
    public static final Message NO_MORE_WHATSNEW = new Message(30, "No more new collections");
    public static final Message NO_MORE_TASK = new Message(31, "No more tasks");
    public static final Message NO_MORE_MESSAGE = new Message(32, "No more messages");
    public static final Message REWARD_HAS_BEEN_TAKEN = new Message(33, "Credits taken, please try again tomorrow");
    public static final Message NO_MORE_FEEDBACK = new Message(34, "No more feedbacks");
    public static final Message REDEEM_OUT_OF_DATE = new Message(35, "This Redeem item is out of date");
    public static final Message REDEEM_CREDIT_NOT_ENOUGH = new Message(36, "Your credits are not enough");
    public static final Message CAN_NOT_BE_INVITED_AGAIN = new Message(37, "You can not be invited twice");
    public static final Message NO_PRIZE = new Message(38, "Thanks for your participation");
    public static final Message NO_MORE_STYLIST_CONTENT = new Message(39, "No more stylist contents");
    public static final Message NO_MORE_RECORD = new Message(40, "No more records");
    public static final Message NO_MORE_STYLIST = new Message(41, "No more stylists");
    public static final Message ARTICLE_HAS_BEEN_LIKED = new Message(42, "You have liked this article");
    public static final Message NO_MORE_BLANCE = new Message(43, "No more balance");
    public static final Message NO_MORE_ITEMS = new Message(44, "No more items");
    public static final Message SUCCESS_CREATE_EXISTS_ACCOUNT = new Message(45, "The account is already exists, add role success!");
    public static final Message NOT_VALID_USERNAME = new Message(46, "Not valid username.");
    public static final Message INVALID_PARAMETER = new Message(46, "invalid parameter");


    /* Some http status codes and corresponding descriptions. */
    public static final Message UNAUTHORIZED = new Message(401, "Unauthorized to access");
    public static final Message FORBIDDEN = new Message(403, "Access Forbidden");
    public static final Message NOT_FOUND = new Message(404, "Resource Not Found");
    public static final Message INTERNAL_SERVER_ERROR = new Message(500, "Internal Server Error");

    /* Messages for account security and token */
    public static final Message TOKEN_EXPIRED = new Message(1001, "The token has been expired");
    public static final Message ACCOUNT_LOCKED = new Message(1002, "Your account is locked");
    public static final Message ACCOUNT_NOT_ACTIVATED = new Message(1003, "Your account is not activated");

    private int code;
    private String description;

    public Message(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "{\"code\":\"" + code + "\", \"description\":\"" + description + "\"}";
    }
}
