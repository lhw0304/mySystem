package com.mode.config;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Status Codes
 *
 * @author Lei
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Status {
    /* Status code from 0 to 20 are reserved for common settings. */
    NEW_RELEASE(0, "New Release."),
    NORMAL(1, "Normal."),
    SOLD_OUT(2, "Sold out."),
    EXPIRED(3, "Expired."),
    CANCELED(4, "Canceled"),
    WAIT_FOR_CHECK(5, "Wait for check."),
    WAIT_FOR_EVALUATION(6, "Wait for evaluation."),
    LOCKED(7, "Locked."),

    /* Status for item*/
    ITEM_STATUS_NORMAL(1, "The item has been approved and listed in marketplace."),
    ITEM_STATUS_DOWN(4, "The item is removed in marketplace."),
    ITEM_STATUS_REVIEW(6, "The item is under review."),

    /* Status for brand*/
    BRAND_NORMA(1, "The brand can be used."),
    BRAND_REJECTED(4, "The brand is rejected."),
    BRAND_UNDER_REVIEW(6, "The brand is under review."),
    BRAND_DELETED(8, "The brand is deleted."),

    /* Status for event */
    EVENT_NORMAL(1, "The event is passed."),
    EVENT_REJECTED(4, "The event is rejected."),
    EVENT_UNDER_REVIEW(6, "The event is under review."),
    EVENT_FINISHED(8, "The event is finished."),

    /* Status for headline */
    HEADLINE_ON(1, "The headline can be showed."),
    HEADLINE_OFF(2, "The headline can't be showed."),

    /* Status code from 101 to 199 are reserved for passbook status. */
    PASS_RECEIVED(101, "Pass received."),
    PASS_EXPIRED(102, "Pass expired."),
    PASS_PURCHASED(103, "Pass purchased."),
    PASS_CONFIRMED(104, "Pass confirmed."),
    PASS_FINISHED(105, "Pass finished."),

    /* Status code from 201 to 299 are reserved for transaction */
    CASH_TRANSACTION_SUBMITTED(201, "Submitted"),
    CASH_TRANSACTION_REJECTED(202, "Rejected"),
    CASH_TRANSACTION_APPROVED(203, "Approved"),
    CASH_TRANSACTION_FINISHED(204, "Finished"),

    /* Status code from 301 to 399 are reserved for campaign */
    MODELOOK_APPLIED(301, "Modelook applied."),
    MODELOOK_WAIT_FOR_EVALUATION(302, "Modelook wait for evaluation."),
    MODELOOK_PASSED_EVALUATION(303, "Modelook passed evaluation."),
    MODELOOK_FINISHED(304, "Modelook finished."),
    MODELOOK_100CREDITS(305, "Modelook 100 credits."),
    MODELOOK_CANCELED(306, "Modelook canceled."),

    /* Status code from 401 to 499 are reserved for task */
    TASK_ACCEPTED(701, "Task accepted."),
    TASK_REWARD_IS_WAITING_TO_BE_TAKEN(702, "The task reward is waiting to be taken."),
    TASK_COMPLETED(704, "Task completed."),

    /* Status code from 1001 to 1099 are reserved for global settings. */
    ERROR(1001, "Encounter ERROR."),
    DELETED(1002, "Deleted."),

    USER_PRICE_STATUS_NEW(1, "New"),
    USER_PRICE_STATUS_PROCESSING(2, "Processing"),
    USER_PRICE_STATUS_DISPATCHED(3, "Dispatched"),
    USER_PRICE_STATUS_CLOSED(4, "Closed"),

    STYLIST_DRAFT_STATUS_DRAFT(1, "DRAFT"),
    STYLIST_DRAFT_STATUS_SUBMITTED(2, "SUBMITTED"),
    STYLIST_DRAFT_STATUS_APPROVED(3, "APPROVED"),
    STYLIST_DRAFT_STATUS_EXPIRED(4, "EXPIRED"),

    /* Stylist Order status */
    ORDER_SUBMITTED(1, "SUBMITTED"),
    ORDER_REJECTED(2, "REJECTED"),
    ORDER_APPROVED(3, "APPROVED"),
    ORDER_SENT(4, "SENT"),
    ORDER_CLOSED(5, "CLOSED");


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