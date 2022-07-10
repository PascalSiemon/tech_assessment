package ch.psiemo.backend.api.natwest.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class PermissionWrapper {

    @JsonProperty("Permissions")
    private List<String> permissions;

    @JsonProperty("AccountRequestId")
    private String accountRequestId;

}
