package com.mode.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.GenericFilterBean;

import com.mode.util.TokenHandler;

/**
 * The entry point of our JWT authentication process; the filter extracts the JWT token from the
 * request headers and delegates authentication to the injected AuthenticationManager.
 * <p>
 * Created by Lei on 3/11/16.
 */
public class JwtAuthenticationFilter extends GenericFilterBean {
    // Please don't change these two constants below!!
    private static final String HEADER_AUTHORIZATION = "X-Access-Token";
    private static final String PARAM_AUTHORIZATION = "accessToken";

    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String accessToken = extractAuthTokenFromRequest(httpRequest);

        if (accessToken != null) {

            // Parse the token and get the username
            TokenClaims tokenClaims = TokenHandler.parseUserFromToken(accessToken);

            // Get the user detail info
            UserDetails user = userDetailsService.loadUserByUsername(tokenClaims.getUsername());

            UsernamePasswordAuthenticationToken authentication = new
                    UsernamePasswordAuthenticationToken(
                    user, null, user.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource()
                    .buildDetails(httpRequest));
            SecurityContextHolder.getContext().setAuthentication(authentication);

        } else {
            throw new JwtTokenMissingException("No JWT token found in request headers");
        }

        chain.doFilter(request, response);
    }

    private String extractAuthTokenFromRequest(HttpServletRequest httpRequest) {
        /* Get token from header */
        String authToken = httpRequest.getHeader(HEADER_AUTHORIZATION);

        /* If token not found get it from request parameter */
        if (authToken == null) {
            authToken = httpRequest.getParameter(PARAM_AUTHORIZATION);
        }

        return authToken;
    }
}