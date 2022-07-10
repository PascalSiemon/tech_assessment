package ch.psiemo.backend.api.natwest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ConsentApproval {

    @JsonProperty("redirectUri")
    private String redirectUri;

}
