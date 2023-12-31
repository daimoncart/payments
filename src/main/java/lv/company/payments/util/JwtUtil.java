package lv.company.payments.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Configuration
@ConfigurationProperties("security.jwt")
@Data
public class JwtUtil {
    private String secretKey;
    private long expirationTime;

//    public String createJwtToken(String username) {
//        long nowMillis = System.currentTimeMillis();
//        Date now = new Date(nowMillis);
//        long expMillis = nowMillis + expirationTime;
//        Date exp = new Date(expMillis);
//
//        return Jwts.builder()
//                .setSubject(username)
//                .setIssuedAt(now)
//                .setExpiration(exp)
//                .signWith(SignatureAlgorithm.HS256, secretKey)
//                .compact();
//    }

//    private Claims extractClaims(String token) {
//
//        var secreKeyObject = getSecretKey();
//
//        return Jwts.parser()
//                .verifyWith(secreKeyObject)
//                .build()
//                .parseSignedClaims(token)
//                .getBody();
//    }

//    public String extractUsername(String token) {
//        return extractClaims(token).getSubject();
//    }
//
//    public boolean isTokenExpired(String token) {
//        Date expiration = extractClaims(token).getExpiration();
//        return expiration.before(new Date());
//    }

//    public SecretKey getSecretKey() {
//        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
//        return new SecretKeySpec(keyBytes, "HmacSHA256");
//    }

    public Long getExpiryMinutes() {
        return expirationTime/60000;
    }
}
