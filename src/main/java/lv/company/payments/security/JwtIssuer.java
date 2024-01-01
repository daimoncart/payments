package lv.company.payments.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtIssuer {

    private final JwtProperties jwtProperties;
    public String issue(long userId, String email, List<String> roles) {
        long nowMillis = System.currentTimeMillis();
        long expMillis = nowMillis + jwtProperties.getExpirationTime();
        Date exp = new Date(expMillis);

        return JWT.create()
                .withSubject(String.valueOf(userId))
                .withExpiresAt(exp)
                .withClaim("email", email)
                .withClaim("authorities", roles)
                .sign(Algorithm.HMAC256(jwtProperties.getSecretKey()));
    }
}
