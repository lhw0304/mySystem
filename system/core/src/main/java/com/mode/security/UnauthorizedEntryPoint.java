package com.mode.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * {@link AuthenticationEntryPoint} that rejects all requests with an
 * unauthorized error message.
 */
@Component
public class UnauthorizedEntryPoint implements AuthenticationEntryPoint {

    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException)
            throws IOException, ServletException {

        // This is invoked when user tries to access a secured REST resource without supplying
        // any credentials. We should just send a 401 Unauthorized response because there is no
        // 'login page' to redirect to.
        response.sendRedirect(request.getContextPath() + "/v3/error?code=401");
    }
}