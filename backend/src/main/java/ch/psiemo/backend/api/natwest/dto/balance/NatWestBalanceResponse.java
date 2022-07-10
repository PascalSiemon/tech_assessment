package ch.psiemo.backend.api.natwest.dto.balance;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class NatWestBalanceResponse {

    @JsonProperty("Data")
    private BalanceWrapper balanceWrapper;

}
