package lv.company.payments.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtDecoder {

    private final JwtProperties jwtProperties;
    public DecodedJWT decode(String token) {
        return JWT.require(Algorithm.HMAC256(jwtProperties.getSecretKey()))
                .build()
                .verify(token);
    }

    public UserPrincipal convertJwtToPrincipal(DecodedJWT jwt) {
        System.out.println(); // TODO - remove this later

        return UserPrincipal.builder()
                .userId(Long.valueOf(jwt.getSubject()))
                .email(String.valueOf(jwt.getClaim("email")))
                .authorities(extractAuthoritiesFromClaim(jwt))
                .build();
    }

    private List<SimpleGrantedAuthority> extractAuthoritiesFromClaim(DecodedJWT jwt) {
        var claim = jwt.getClaim("a");
        if (claim.isNull() || claim.isMissing()) return List.of();
        return claim.asList(SimpleGrantedAuthority.class);
    }
}
