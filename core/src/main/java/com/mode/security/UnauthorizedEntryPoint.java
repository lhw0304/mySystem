package com.mode.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Service;

/**
 * {@link AuthenticationEntryPoint} that rejects all requests with an
 * unauthorized error message.
 */
public class UnauthorizedEntryPoint implements AuthenticationEntryPoint {

    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        response.sendRedirect(request.getContextPath() + "/v2/error?code=401");
    }
}