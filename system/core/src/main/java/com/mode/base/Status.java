package com.mode.base;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * State codes for reading/writing to the status columns of database tables.
 *
 * @author chao
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Status {

    /* State code from 0 to 20 are reserved for common settings. */
    NORMAL(0, "Status Normal."),
    SUBMITTED(1, "Status Submitted"),
    Approved(2, "Status Approved"),
    Completed(3, "Status Completed"),
    Closed(4, "Status Closed"),

    /* Status code for user price */
    USER_PRICE_STATUS_NEW(1, "New"),
    USER_PRICE_STATUS_PROCESSING(2, "Processing"),
    USER_PRICE_STATUS_DISPATCHED(3, "Dispatched"),
    USER_PRICE_STATUS_CLOSED(4, "Closed");

    private final int code;
    private final String message;

    private Status(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "{\"code\":\"" + code + "\", \"message\":\"" + message + "\"}";
    }
}