package ch.psiemo.backend.api.natwest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ApiAccessToken {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("id_token")
    private String idToken;

    @JsonProperty("token_type")
    private String tokenType;

    @JsonProperty("expires_in")
    private Long expiresIn;

    @JsonProperty("scope")
    private String scope;

}
