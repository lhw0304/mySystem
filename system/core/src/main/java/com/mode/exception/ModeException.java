package com.mode.exception;

import com.mode.base.Message;

/**
 * A base exception class for dealing with all business exceptions that happen at runtime.
 *
 * Note: at this moment, we'd like to keep the custom exception as simple as possible. So we make
 * this exception class final. We may want to enhance the exception handling and categorizing then
 * into different sub modules, such like account, payment, system exceptions, etc.
 *
 * Created by chao on 3/10/16.
 */
public final class ModeException extends RuntimeException {

    private int code;

    public ModeException(String msg, int code) {
        super(msg);
        this.code = code;
    }

    public ModeException(Message message){
        super(message.getDescription());
        this.code = message.getCode();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}