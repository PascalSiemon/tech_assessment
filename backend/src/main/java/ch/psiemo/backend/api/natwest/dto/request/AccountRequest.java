package ch.psiemo.backend.api.natwest.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountRequest {

    @JsonProperty("Data")
    private PermissionWrapper permissionWrapper;

    @JsonProperty("Risk")
    private Object risk;

}
