package com.jay.chat.util;

import com.jay.chat.model.User;

import java.util.Date;

public class TokenUtil {

    private static final Long expireTime = 1800000l;

    public static String createToken(User user){
        Long timestamp = new Date().getTime();
        return user.getUserId() + ":" + timestamp;
    }

    public static String[] parseToken(String token){
        String[] tokenContent = token.split(":");
        return tokenContent;
    }

    public static boolean isTokenExpired(String token){
        String[] tokenContent = token.split(":");
        Long timestamp = Long.parseLong(tokenContent[1]);
        Long currentTime = new Date().getTime();
        return currentTime - timestamp > expireTime;
    }

    public static Long getUserId(String token){
        return Long.valueOf(parseToken(token)[0]);
    }
}
