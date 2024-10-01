package org.example.haranglogin.jwt;

public class JwtSettingUtil {
    public final static Integer VALIDITY = 1000 * 60 * 5; // 5 minute
    public static final Integer REFRESH_VALIDITY = 1000 * 60 * 60 * 24 * 7; // 7 day
    public static final String JWTTOKENNAME = "jwtToken";
    public static final String JWTREFRESHTOKENNAME = "jwtRefreshToken";
    public final static Integer COOKIEMAXAGE = 60 * 5; // 5 minute

    public static final int REFRESHCOOKIEMAXAGE = 60 * 60 * 24 * 7; // 7 day
}

