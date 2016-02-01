package com.mode.util;

import java.io.ByteArrayInputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mode.config.BaseConfig;
import com.mode.security.LoginUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;

public class TokenHandler {

    /* Token Handler for old token */
    public static LoginUser getUserFromToken(String token) {
        return (token != null) ? fromJSON(fromBase64(token)) : null;
    }

    private static LoginUser fromJSON(final byte[] userBytes) {
        try {
            return new ObjectMapper().readValue(new ByteArrayInputStream(userBytes),
                    LoginUser.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException(e);
        }
    }

    private static byte[] toJSON(LoginUser user) {
        try {
            return new ObjectMapper().writeValueAsBytes(user);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(e);
        }
    }

    private static String toBase64(byte[] content) {
        return DatatypeConverter.printBase64Binary(content);
    }

    private static byte[] fromBase64(String content) {
        return DatatypeConverter.parseBase64Binary(content);
    }

    public static boolean validateToken(String authToken) {
        LoginUser user = getUserFromToken(authToken);
        long expires = user.getExpires();

        if ((user != null) && (expires > System.currentTimeMillis())) {
            return true;
        }

        return false;
    }

    /* Token Handler for new token */
    public static String createToken(String username) {
        long now = System.currentTimeMillis();
        long expires = now + BaseConfig.EXPIRE_IN_TWO_WEEKS;

        Date nowDate = new Date(now);
        Date expireDate = new Date(expires);

        Map<String, Object> claims = new HashMap<>();
        claims.put("username", username);

        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)
                .setExpiration(expireDate)
                .setIssuedAt(nowDate)
                .signWith(BaseConfig.SIGNATURE_ALGORITHM, KeyGenerator.getKey());

        return builder.compact();
    }

    public static String parseToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(KeyGenerator.getKey())
                .parseClaimsJws(token).getBody();

        return String.valueOf(claims.get("username"));
    }
}