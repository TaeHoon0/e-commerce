package kr.hhplus.be.server.global.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import kr.hhplus.be.server.user.domain.UserType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.ZonedDateTime;
import java.util.Date;

@Slf4j
@Component
public class JwtUtil {

    private final Key key;
    private final long accessTokenExpireTime;
    private final long refreshTokenExpireTime;

    public JwtUtil(
            @Value("${jwt.secret-key}") final String secretKey,
            @Value("${jwt.access-token-expiration-time}") final long accessTokenExpireTime,
            @Value("${jwt.refresh-token-expiration-time}") final long refreshTokenExpireTime
    ) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.accessTokenExpireTime = accessTokenExpireTime;
        this.refreshTokenExpireTime = refreshTokenExpireTime;
    }

    public String createAccessToken(Long userId, UserType userType) {

        return createToken(userId, userType.name(), accessTokenExpireTime);
    }

    public String createRefreshToken(Long userId, UserType userType) {

        return createToken(userId, userType.name(), refreshTokenExpireTime);
    }

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

    /**
     * JWT 검증
     */
    public boolean isValidToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);

            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT : {}", token);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT : {}", token);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT: {}", token);
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty : {}", token);
        }

        return false;
    }

    public Long getUserId(String accessToken) {
        return parseClaims(accessToken).get("userId", Long.class);
    }

    public String getUserType(String accessToken) {
        return parseClaims(accessToken).get("userType", String.class);
    }


    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(accessToken)
                    .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
}
