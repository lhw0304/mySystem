package com.mode.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.mode.base.BaseConfig;
import com.mode.security.TokenClaims;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * A utility class for dealing with creating and parsing JWT access tokens.
 */
public final class TokenHandler {

    /**
     * Signing Key for JWT token encryption.
     */
    private static final String JWT_SIGNING_KEY = "hello, this is mode fashion 2016 spring.";

    /**
     * Generate an access token using the given signing key from this user.
     *
     * @param username
     * @return
     */
    public static String createTokenForUser(String username, String source) {
        final long currentTimeMillis = System.currentTimeMillis();
        final long expireTimeMillis = currentTimeMillis + BaseConfig.TOKEN_EXPIRE_WINDOW;

        Map<String, Object> claims = new HashMap<>();
        claims.put("username", username);
        claims.put("source", source);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(currentTimeMillis))
                .setExpiration(new Date(expireTimeMillis))
                .signWith(SignatureAlgorithm.HS512, JWT_SIGNING_KEY)
                .compact();
    }

    /**
     * Decode the given access token and return the username.
     *
     * @param token
     * @return
     */

    public static TokenClaims parseUserFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_SIGNING_KEY)
                .parseClaimsJws(token)
                .getBody();
        String username = String.valueOf(claims.get("username"));

        String source = String.valueOf(claims.get("source"));

        TokenClaims tokenClaims = new TokenClaims();
        tokenClaims.setUsername(username);
        tokenClaims.setSource(source);

        return tokenClaims;
    }
}