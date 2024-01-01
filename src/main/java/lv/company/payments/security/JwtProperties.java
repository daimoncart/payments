package lv.company.payments.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("security.jwt")
@Data
public class JwtProperties {
    private String secretKey;
    private long expirationTime;

    public Long getExpiryMinutes() {
        return expirationTime/60000;
    }
}
