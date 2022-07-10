package ch.psiemo.backend.api.natwest.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class NatWestAccountResponse {

    @JsonProperty("Data")
    private AccountWrapper accountWrapper;

}
