package lv.company.payments.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Schema(description = "Authentication response to login user")
@Builder
@Getter
public class AuthResponse {
    @Schema(description = "Authentication token issued to the login user")
    private String authToken;
}
