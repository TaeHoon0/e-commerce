package kr.hhplus.be.server.user.infrastructure.jwt;

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
public class JwtTokenProvider {

    private final Key key;
    private final long accessTokenExpireTime;
    private final long refreshTokenExpireTime;

    public JwtTokenProvider(
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
     * @param token
     * @return IsValidate
     */
    public boolean isValidToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty", e);
        }
        return false;
    }
}
