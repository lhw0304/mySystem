package com.mode.security;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by Lei on 3/11/16.
 */
public class JwtTokenMissingException extends AuthenticationException {

    public JwtTokenMissingException(String msg, Throwable t) {
        super(msg, t);
    }

    public JwtTokenMissingException(String msg) {
        super(msg);
    }
}
