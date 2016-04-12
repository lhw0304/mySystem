package com.mode.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.mode.base.Message;
import com.mode.base.Response;

/**
 * A global exception handler that takes the advantage of Spring Controller Advice - see AOP for
 * more detailed descriptions.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public Response handleBusinessException(Exception e) {

        if(e instanceof ModeException){
            ModeException me = (ModeException) e;
            return new Response(me.getCode(), me.getMessage());
        } else {
            e.printStackTrace();
            return new Response(Message.INTERNAL_SERVER_ERROR);
        }
    }
}