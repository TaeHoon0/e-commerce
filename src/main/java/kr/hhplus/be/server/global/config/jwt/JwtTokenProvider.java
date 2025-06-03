package kr.hhplus.be.server.global.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.ZonedDateTime;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private final Key key;
    private final long accessTokenExpireTime;
    private final long refreshTokenExpireTime;

    public JwtTokenProvider(
            @Value("${jwt.secret-key}") final String secretKey,
            @Value("${jwt.access-token-expiration-time}") final long accessTokenExpireTime,
            @Value("${jwt.refresh-token-expiration-time}" final long refreshTokenExpireTime
    ) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.accessTokenExpireTime = accessTokenExpireTime;
        this.refreshTokenExpireTime = refreshTokenExpireTime;
    }

    public String createAccessToken(UserDetails userDetails) {}

    /**
     * JWT Token 생성
     */
    private String createToken(long userId, String userType, long expireTime) {
        Claims claims = Jwts.claims();
        claims.put("userId", userId);
        claims.put("userType", userType);

        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime expiresAt = now.plusSeconds(expireTime);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(Date.from(now.toInstant()))
                .setExpiration(Date.from(expiresAt.toInstant()))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }


}
