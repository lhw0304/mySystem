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
    public static final Message CATCH = new Message(5, "catch error");
    public static final Message NO_MORE_SINGLE_SELECT = new Message(6, "no more single select");
    public static final Message NO_MORE_COMPLETION = new Message(7, "no more completion");
    public static final Message NOT_MATCH = new Message(8, "not match");
    public static final Message NO_MORE_CHECK = new Message(9, "no more single select");
    public static final Message USER_EXISTS = new Message(11, "the user is exists");
    public static final Message UNAUTHORIZED = new Message(10, "UNAUTHORIZED");
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