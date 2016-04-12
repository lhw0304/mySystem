package com.mode.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.GenericFilterBean;

import com.mode.config.BaseConfig;
import com.mode.dao.AccountDAO;
import com.mode.entity.Account;
import com.mode.util.TokenHandler;

public class AuthenticationTokenProcessingFilter extends GenericFilterBean {

    private final UserDetailsService userService;


    private String uri;

    public AuthenticationTokenProcessingFilter(UserDetailsService userService) {
        this.userService = userService;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = getAsHttpRequest(request);
        HttpServletResponse httpResponse = getAsHttpResponse(response);

        String authToken = extractAuthTokenFromRequest(httpRequest);

        if (authToken != null) {
            /* Decode login user information from token */
            LoginUser loginUser = TokenHandler.getUserFromToken(authToken);
            String username = loginUser.getUsername();
            UserDetails userDetails = userService.loadUserByUsername(username);

            /* Check if the token is valid or expired*/
            if (TokenHandler.validateToken(authToken)) {

                /* Check if the account is locked*/
                if (userDetails.isAccountNonLocked()) {
                     /* Check if the username and password is valid */
                    UsernamePasswordAuthenticationToken authentication = new
                            UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource()
                            .buildDetails(httpRequest));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    if(!httpRequest.getRequestURI().contains(BaseConfig.ERROR_URL_TEMPLATE)) {
                        /* If token is not presented, return unauthorized error */
                        httpResponse.sendRedirect(httpRequest.getContextPath() + BaseConfig.ERROR_1002);
                        return;
                    }
                }
            } else {
                if (!httpRequest.getRequestURI().contains(BaseConfig.ERROR_URL_TEMPLATE)) {
                    /* If token is not presented, return unauthorized error */
                    httpResponse.sendRedirect(httpRequest.getContextPath() + BaseConfig.ERROR_1001);
                    return;
                }
            }
        }
        chain.doFilter(request, response);

    }

    private HttpServletRequest getAsHttpRequest(ServletRequest request) {
        if (!(request instanceof HttpServletRequest)) {
            throw new RuntimeException("Expecting an HTTP request");
        }

        return (HttpServletRequest) request;
    }

    private HttpServletResponse getAsHttpResponse(ServletResponse response) {
        if (!(response instanceof HttpServletResponse)) {
            throw new RuntimeException("Expecting an Http response");
        }
        return (HttpServletResponse) response;
    }

    private String extractAuthTokenFromRequest(HttpServletRequest httpRequest) {
        /* Get token from header */
        String authToken = httpRequest.getHeader("X-Auth-Token");

        /* If token not found get it from request parameter */
        if (authToken == null) {
            authToken = httpRequest.getParameter("token");
        }

        return authToken;
    }

}