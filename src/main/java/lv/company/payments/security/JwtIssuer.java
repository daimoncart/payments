package lv.company.payments.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import lv.company.payments.util.JwtUtil;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtIssuer {

    private final JwtUtil util;
    public String issue(long userId, String email, List<String> roles) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        long expMillis = nowMillis + util.getExpirationTime();
        Date exp = new Date(expMillis);

        return JWT.create()
                .withSubject(String.valueOf(userId))
                .withExpiresAt(exp)
                .withClaim("e", email)
                .withClaim("a", roles)
                .sign(Algorithm.HMAC256(util.getSecretKey().toString()));
    }
}
