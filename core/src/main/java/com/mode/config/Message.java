package com.mode.config;

/**
 * Response messages returned to clients.
 *
 * @author Lei
 */
public class Message {
    /* Message code from 0 to 100 are reserved for Mode applications. */
    public static final Message SUCCESS = new Message(0, "Operation succeeded");
    public static final Message FAILURE = new Message(1, "Operation failed");
    public static final Message DATABASE = new Message(2, "A database error has occurred");
    public static final Message DUPLICATE_USER = new Message(3, "This user already exists");
    public static final Message USER_NOT_EXIST = new Message(4, "This user does not exist");
    public static final Message ERROR_CREATE_ACCOUNT = new Message(5, "An error occurred while creating account");
    public static final Message NO_MORE_SINGLE_SELECT = new Message(6, "No more single select");
    public static final Message CATCH = new Message(7, "catch error");

    /* Some http status codes and corresponding descriptions. */
    public static final Message UNAUTHORIZED = new Message(401, "Unauthorized to access");
    public static final Message FORBIDDEN = new Message(403, "Access Forbidden");
    public static final Message NOT_FOUND = new Message(404, "Resource Not Found");
    public static final Message INTERNAL_SERVER_ERROR = new Message(500, "Internal Server Error");

    /* Messages for account security and token */
    public static final Message TOKEN_EPIRED = new Message(1001, "The token has been expired");
    public static final Message ACCOUNT_LOCKED = new Message(1002, "Your account is locked");
    public static final Message ACCOUNT_NOT_ACTIVATED = new Message(1003, "Your account is not activated");

    private int code;
    private String description;

    Message(int code, String description) {
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